package hr.foi.rampu.emedi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.rampu.emedi.entities.Doctor
import hr.foi.rampu.emedi.entities.Review
import hr.foi.rampu.emedi.entities.User

@Database(
    version=2,
    entities=[User::class, Doctor::class, Review::class],
    exportSchema=false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDAO
    abstract fun getDoctorsDao(): DoctorsDAO
    abstract fun getReviewsDao(): ReviewsDAO

    companion object {
        @Volatile
        private var implementedInstance: AppDatabase? = null

        fun getInstance(): AppDatabase {
            if (implementedInstance == null) {
                throw NullPointerException("Database instance has not yet been created!")
            }
            return implementedInstance!!
        }

        fun buildInstance(context: Context) {
            if (implementedInstance == null) {
                val instanceBuilder = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "appdatabase.db"
                )
                instanceBuilder.fallbackToDestructiveMigration()
                instanceBuilder.allowMainThreadQueries()
                instanceBuilder.build()

                implementedInstance = instanceBuilder.build()
            }
        }
    }
}