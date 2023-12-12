import hr.foi.rampu.emedi.database.AppDatabase
import hr.foi.rampu.emedi.entities.Doctor

object MockDataDoctor {
    val doctorList = arrayOf<Doctor>(
        Doctor(
            1,
            "Ivo",
            "Ivić",
            "Ginekolog",
            10,
            "Ginekolog",
            "Klinika1",
            "Adresa1",
            "iivic@",
            "012340087",
        ),
        Doctor(
            2,
            "Marija",
            "Marić",
            "Kardiolog",
            15,
            "Bavi se srcima idk",
            "Klinika2",
            "Adresa2",
            "email2",
            "telefon2",
        )
    )

    fun loadDoctors() {
        val doctorsDAO = AppDatabase.getInstance().getDoctorsDao()

        if (doctorsDAO.getAllDoctors().isEmpty()) {
            doctorsDAO.insertDoctor(*doctorList)
        }
    }

    fun getDemoData(): List<Doctor> {
        val doctors = AppDatabase.getInstance().getDoctorsDao().getAllDoctors()
        return doctors.toList()
    }
}
