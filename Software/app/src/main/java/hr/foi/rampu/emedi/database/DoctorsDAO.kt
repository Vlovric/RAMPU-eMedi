package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.User

@Dao
interface DoctorsDAO {
    @Query("SELECT * FROM doctors WHERE id = :id")
    fun getDoctor(id: Int): Doctor

    @Query("SELECT * FROM doctors")
    fun getAllDoctors(): List<Doctor>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDoctor(vararg doctor: Doctor): List<Long>
}