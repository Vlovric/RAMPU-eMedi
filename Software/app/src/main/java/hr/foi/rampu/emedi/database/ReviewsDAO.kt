package hr.foi.rampu.emedi.database

import androidx.room.Dao
import androidx.room.Query
import hr.foi.rampu.emedi.entities.Review

@Dao
interface ReviewsDAO {
    @Query("SELECT * FROM reviews WHERE doctor_id = :doctorId")
    fun getReviewsForDoctor(doctorId: Int) : List<Review>
}