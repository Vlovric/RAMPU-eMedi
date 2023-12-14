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

    @Query("SELECT * FROM appointment WHERE user_id = :userId")
    fun getAppointmentsForUser(userId: Int): List<Appointment>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAppointment(vararg appointment: Appointment): List<Long>

    @Query("SELECT * FROM appointment WHERE doctor_id = :doctorId")
    fun getAppointmentsForDoctor(doctorId: Int): List<Appointment>
}