package github.masterj3y.data.database.dao

import androidx.room.*
import github.masterj3y.data.model.BasicUser
import github.masterj3y.data.model.UserProfile
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile WHERE id = :userId LIMIT 1")
    fun findUserById(userId: Int): Flow<UserProfile?>

    @Query("SELECT EXISTS(SELECT * FROM UserProfile WHERE login = :userName)")
    fun doesUserProfileExist(userName: String) : Flow<Boolean>

    @Delete
    suspend fun deleteUserProfile(userProfile: UserProfile)

    @Query("SELECT * FROM UserProfile")
    fun findAllFavoriteUsers(): Flow<List<UserProfile>>
}