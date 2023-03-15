package github.masterj3y.data.repository

import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.getOrNull
import github.masterj3y.data.database.AppDatabase
import github.masterj3y.data.model.BasicUser
import github.masterj3y.data.model.PagingResponse
import github.masterj3y.data.model.UserProfile
import github.masterj3y.data.service.UserService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

interface UserRepository {

    val favoriteUsers: Flow<List<UserProfile>>

    suspend fun searchUsers(
        userName: String,
        page: Int
    ): ApiResponse<PagingResponse<BasicUser>>

    fun getUserFollowers(userName: String): Flow<List<BasicUser>?>

    fun getUserFollowing(userName: String): Flow<List<BasicUser>?>

    fun getUserProfile(userName: String): Flow<UserProfile?>

    fun isUserProfileSaved(userName: String): Flow<Boolean>

    suspend fun saveUserProfile(userProfile: UserProfile)

    suspend fun deleteUserProfile(userProfile: UserProfile)
}

class UserRepositoryImpl @Inject constructor(
    appDatabase: AppDatabase,
    private val service: UserService
) : UserRepository {

    private val userDao = appDatabase.userDao()

    override val favoriteUsers: Flow<List<UserProfile>> by lazy { userDao.findAllFavoriteUsers() }

    override suspend fun searchUsers(
        userName: String,
        page: Int
    ): ApiResponse<PagingResponse<BasicUser>> =
        service.searchUsers(userName, page)

    override fun getUserFollowers(userName: String): Flow<List<BasicUser>?> = flow {
        service.getUserFollowers(userName).getOrNull()?.let {
            emit(it)
        } ?: emit(null)
    }

    override fun getUserFollowing(userName: String): Flow<List<BasicUser>?> = flow {
        service.getUserFollowing(userName).getOrNull()?.let {
            emit(it)
        } ?: emit(null)
    }

    override fun getUserProfile(userName: String): Flow<UserProfile?> = flow {
        service.getUserProfile(userName).getOrNull()?.let {
            emit(it)
        } ?: emit(null)
    }

    override fun isUserProfileSaved(userName: String): Flow<Boolean> =
        userDao.doesUserProfileExist(userName)

    override suspend fun saveUserProfile(userProfile: UserProfile) {
        userDao.insert(userProfile)
    }

    override suspend fun deleteUserProfile(userProfile: UserProfile) {
        userDao.deleteUserProfile(userProfile)
    }
}