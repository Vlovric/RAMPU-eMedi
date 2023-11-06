package hr.foi.rampu.emedi.entities

import java.util.Date

/*
*DODATI:
* List<Posjeta> - KASNIJE
* List<Recenzija> RECENZIJE - KASNIJE
* */
data class User(
    val name: String,
    val surname: String,
    val birthDate: Date,
    val emali: String,
    val telephoneNumber: String,
    val address: String,
    val username: String,
    val password: String
)
