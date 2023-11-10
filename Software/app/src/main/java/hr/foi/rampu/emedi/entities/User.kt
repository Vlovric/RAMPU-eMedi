package hr.foi.rampu.emedi.entities

import java.util.Date

/*
*DODATI:
* List<Posjeta> - KASNIJE
* List<Recenzija> RECENZIJE - KASNIJE
* */
data class User(
    var name: String,
    var surname: String,
    var birthDate: Date,
    var emali: String,
    var telephoneNumber: String,
    var address: String,
    var username: String,
    var password: String
)
