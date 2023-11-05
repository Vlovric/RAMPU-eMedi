package hr.foi.rampu.emedi.entities

import java.util.Date

/*
Ime(string)
Prezime(string)
Datum rođenja(date)
e-mail(string)
broj telefona (double)
Adresa (Ulica, kućni broj, grad)
Korisničko ime(string)
Lozinka (string)
posjete_lječniku(List<Posjeta>) - KASNIJE
RECENZIJE - KASNIJE
 */

data class User(
    val name: String,
    val birthDate: Date,
    val email: String,
    val telephoneNumber: String,
    val address: String,
    val username: String,
    val password: String
)