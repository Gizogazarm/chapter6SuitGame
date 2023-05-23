package id.rich.challengech5.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.rich.challengech5.model.User

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User) : Long

    @Query("SELECT * FROM user")
    suspend fun getAllUser(): List<User>

    @Query("SELECT * FROM user WHERE username= :username AND password= :password")
    suspend fun getUserByUsername(username: String, password: String): List<User>

    @Query("SELECT * FROM user WHERE username= :username")
    suspend fun findUserByUsername(username: String): User
}