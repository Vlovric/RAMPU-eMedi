package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import hr.foi.rampu.emedi.entities.Appointment
import hr.foi.rampu.emedi.entities.Doctor

@Dao
interface AppointmentsDAO {
    @Query("SELECT * FROM appointment WHERE booking_reason_id = :bookingId")
    fun getAppointmentForBooking(bookingId: Int): List<Appointment>

    @Query("SELECT * FROM appointment")
    fun getAllAppointments(): List<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppointment(vararg appointment: Appointment): List<Long>
}