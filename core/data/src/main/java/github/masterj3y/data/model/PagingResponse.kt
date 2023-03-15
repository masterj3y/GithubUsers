package github.masterj3y.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PagingResponse<T>(
    @SerializedName("total_count")
    val totalCount: Int,
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean,
    @SerializedName("items")
    val items: List<T>
)