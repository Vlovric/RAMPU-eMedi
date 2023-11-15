package hr.foi.rampu.emedi.helpers

import hr.foi.rampu.emedi.entities.Doctor

object MockDataDoctor {
    fun getDemoData(): List<Doctor> = listOf(
        Doctor("Ivo",
            "Ivić",
            "Ginekolog",
            10,
            "Ginekolog",
            "Klinika1",
            "Adresa1",
            "email1",
            "telefon1"),
        Doctor("Marija",
            "Marić",
            "Kardiolog",
            15,
            "Bavi se srcima idk",
            "Klinika2",
            "Adresa2",
            "email2",
            "telefon2")
    )
}