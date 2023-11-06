package hr.foi.rampu.emedi.helpers

import hr.foi.rampu.emedi.entities.User
import java.util.Date

object MockDataUser {
    fun getDemoData(): List<User> = listOf(
        User("Pero",
            "Perić",
            Date(1990, 5, 15),
            "email1@example.com",
            "1234567890",
            "Adresa 1",
            "ja",
            "ti"),
        User("Vedran",
            "Galić",
            Date(1985, 8, 20),
            "email2@example.com",
            "0987654321",
            "Adresa 2",
            "korisnik2",
            "lozinka2")
    )
}