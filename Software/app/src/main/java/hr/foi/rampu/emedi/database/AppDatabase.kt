package hr.foi.rampu.emedi.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hr.foi.rampu.emedi.entities.User

@Database(
    version=1,
    entities=[User::class],
    exportSchema=false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUsersDao(): UsersDAO

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