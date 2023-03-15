package github.masterj3y.data.service

import com.skydoves.sandwich.ApiResponse
import github.masterj3y.data.model.PagingResponse
import github.masterj3y.data.model.BasicUser
import github.masterj3y.data.model.UserProfile
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {

    @GET("users/{userName}")
    suspend fun getUserProfile(
        @Path("userName") userName: String
    ): ApiResponse<UserProfile>

    @GET("users/{userName}/followers")
    suspend fun getUserFollowers(
        @Path("userName") userName: String
    ): ApiResponse<List<BasicUser>>

    @GET("users/{userName}/following")
    suspend fun getUserFollowing(
        @Path("userName") userName: String
    ): ApiResponse<List<BasicUser>>

    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") userName: String,
        @Query("page") page: Int
    ): ApiResponse<PagingResponse<BasicUser>>
}