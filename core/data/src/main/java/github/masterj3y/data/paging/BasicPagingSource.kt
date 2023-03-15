package github.masterj3y.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import github.masterj3y.data.model.PagingResponse
import java.io.IOException

abstract class BasicPagingSource<T : Any>(private val request: suspend (page: Int) -> ApiResponse<PagingResponse<T>>) :
    PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? = state.anchorPosition

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val nextPage: Int = params.key?.let {
            if (it < FIRST_PAGE) FIRST_PAGE else it
        } ?: FIRST_PAGE

        return try {

            val response = request(nextPage).getOrNull()

            val items = response?.items

            if (items != null) {
                LoadResult.Page(
                    data = items,
                    prevKey = when {
                        nextPage > FIRST_PAGE -> nextPage - 1
                        else -> null
                    },
                    nextKey = if (items.isEmpty() || items.size < (PAGE_SIZE)) null else nextPage + 1
                )
            } else {
                LoadResult.Error(Throwable("Unknown error occurred while fetching pages"))
            }
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE = 1
        const val PAGE_SIZE = 30
    }
}