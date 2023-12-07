package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import hr.foi.rampu.emedi.entities.User

@Dao
interface UsersDAO {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUser(id: Int): User

    @Query("SELECT * FROM users")
    fun getAllUsers(): List<User>

    @Insert(onConflict = REPLACE)
    fun insertUser(vararg user: User): List<Long>
}