package hr.foi.rampu.emedi.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.Doctor

@Entity(
    tableName="reviews",
    foreignKeys = [
        ForeignKey(
            entity = Doctor::class,
            parentColumns = ["id"],
            childColumns = ["doctor_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ]
)
data class Review(
    @PrimaryKey(autoGenerate=true) val id: Int,
    val grade: Int,
    val description: String,
    @ColumnInfo(name="doctor_id", index=true) val doctorId: Int
    //var doctor: Doctor? = null
) {
    @delegate:Ignore
    val doctor: Doctor by lazy {
        AppDatabase.getInstance().getDoctorsDao().getDoctor(doctorId)
    }

    companion object {
        private val reviews: MutableList<Review> = mutableListOf()

        fun addReview(review: Review) {
            reviews.add(review)
        }

        fun getReviewsForDoctor(doctor: Doctor): List<Review> {
            return AppDatabase.getInstance().getReviewsDao().getReviewsForDoctor(doctor.id)
        }

        /*fun getReviewsForDoctor(doctor: Doctor): List<Review> {
            return reviews.filter { it.doctor == doctor }
        }*/
    }
}
