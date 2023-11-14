package hr.foi.rampu.emedi.helpers

object InputCheckHelper {
    fun checkFirstname(text: String): String {
        if (text.isBlank()) {
            return "Firstname can't be blank!"
        }
        return ""
    }
    fun checkLastname(text: String): String {
        if (text.isBlank()) {
            return "Lastname can't be blank!"
        }
        return ""
    }
    fun checkAddress(text: String): String {
        if (text.isBlank()) {
            return "Address can't be blank!"
        }
        return ""
    }
    fun emailAddressCheck(text: String): String {
        if (text.isBlank()) {
            return "Email address can't be blank!"
        }
        if (!Regex("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}\$").matches(text)) {
            return "Not a valid email address!"
        }
        return ""
    }

    fun telephoneNumberCheck(text: String): String {
        if (text.isBlank()) {
            return "Telephone number can't be blank!"
        }
        if (text.length < 3) {
            return "Telephone number is too short!"
        }
        if (text.length > 20) {
            return "Telephone number is too long!"
        }
        if (!Regex("^\\d+\$").matches(text)) {
            return "Not a telephone number!"
        }
        return ""
    }
    fun checkPassword(text: String): String {
        if (text.isBlank()) {
            return "Password can't be blank!"
        }
        if (text.length < 8) {
            return "Password must be longer than 8 characters!"
        }
        if (text.length > 20) {
            return "Password is too long!"
        }
        if (!Regex("^(?=.*\\d)(?=.*[#$%]).+\$").matches(text)) {
            return "Not a telephone number!"
        }

        return ""
    }

    fun checkUsername(text: String): String {
        if (text.isBlank()) {
            return "Username can't be blank!"
        }
        if (text.length < 2) {
            return "Username is too short!"
        }
        if (text.length > 20) {
            return "Username is too long!"
        }
        return ""
    }
}
