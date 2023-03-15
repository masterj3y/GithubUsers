package github.masterj3y.data.model

import androidx.annotation.Keep
import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Keep
@Immutable
@Entity
data class UserProfile(
    @SerializedName("login")
    val login: String,
    @PrimaryKey
    @SerializedName("id")
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("blog")
    val blog: String?,
    @SerializedName("location")
    val location: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("twitter_username")
    val twitterUsername: String?,
    @SerializedName("followers")
    val followers: Int,
    @SerializedName("following")
    val following: Int,
    @SerializedName("public_repos")
    val publicRepositories: Int,
)