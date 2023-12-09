package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Query
import hr.foi.rampu.emedi.entities.Doctor

@Dao
interface DoctorsDAO {
    @Query("SELECT * FROM doctors WHERE id = :id")
    fun getDoctor(id: Int): Doctor
}