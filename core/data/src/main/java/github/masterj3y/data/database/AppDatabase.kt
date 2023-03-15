package github.masterj3y.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import github.masterj3y.data.database.dao.UserDao
import github.masterj3y.data.model.UserProfile

@Database(
    entities = [UserProfile::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}