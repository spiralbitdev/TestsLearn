package com.appsdev.testslearn

object RegistrationUtil {

    private val existingUsers = listOf("Peter", "Carl")

    /*
        input is not valid if the username is empty or password is empty
        the username is already taken
        the password is not the same as confirm password
        the password contains less than 2 digits
     */

    fun validateRegistrationInput(userName: String, password: String, confirmPassword: String): Boolean {
        if (userName.isEmpty() or password.isEmpty()) return false
        if (userName in existingUsers) return false
        if (confirmPassword != password) return false
        if (password.count { it.isDigit() } < 2) return false
        return true
    }
}