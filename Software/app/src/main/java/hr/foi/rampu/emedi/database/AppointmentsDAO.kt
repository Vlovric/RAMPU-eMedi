package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Query
import hr.foi.rampu.emedi.entities.Appointment

@Dao
interface AppointmentsDAO {
    @Query("SELECT * FROM appointment WHERE booking_reason_id = :bookingId")
    fun getAppointmentForBooking(bookingId: Int): List<Appointment>
}