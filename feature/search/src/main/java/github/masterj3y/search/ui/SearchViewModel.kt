package github.masterj3y.search.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import github.masterj3y.coroutines.flow.WhileSubscribedOrRetained
import github.masterj3y.data.paging.BasicPagingSource
import github.masterj3y.data.repository.UserRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    repository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val userName = savedStateHandle.getStateFlow(USER_NAME_KEY, "")
        .stateIn(
            scope = viewModelScope,
            started = WhileSubscribedOrRetained,
            initialValue = ""
        )

    @OptIn(FlowPreview::class)
    val searchUserResultPages = userName
        .debounce(1000L)
        .filter { it.isNotBlank() }
        .flatMapLatest { username ->
            Pager(PagingConfig(BasicPagingSource.PAGE_SIZE)) {
                SearchPagingSource(repository, username)
            }.flow.stateIn(
                scope = viewModelScope,
                started = WhileSubscribedOrRetained,
                initialValue = PagingData.empty()
            )
        }

    fun search(userName: String) = savedStateHandle.set(USER_NAME_KEY, userName)

    companion object {
        private const val USER_NAME_KEY = "user-name"
    }
}