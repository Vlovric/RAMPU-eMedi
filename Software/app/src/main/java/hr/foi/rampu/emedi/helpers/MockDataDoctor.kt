import hr.foi.rampu.emedi.entities.Doctor

object MockDataDoctor {
    fun getDemoData(): List<Doctor> {
        val doctor1 = Doctor(
            1,
            "Ivo",
            "Ivić",
            "Ginekolog",
            10,
            "Ginekolog",
            "Klinika1",
            "Adresa1",
            "email1",
            "telefon1",
        )

        val doctor2 = Doctor(
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

        return listOf(doctor1, doctor2)
    }
}
