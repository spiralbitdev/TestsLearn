package com.appsdev.testslearn

import com.google.common.truth.Truth.assertThat
import org.junit.Test

//  Cases:

// empty username
// empty password
// confirm passwords
// password repeated incorrectly
// password contains less than 2 digits

class RegistrationUtilTest {

    @Test
    fun addition_emptyUsername() {
        val result = RegistrationUtil.validateRegistrationInput("", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun addition_validUsernameAndRepeatedPasswords() {
        val result = RegistrationUtil.validateRegistrationInput("Max D'Kot", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun addition_alreadyExistUsername() {
        val result = RegistrationUtil.validateRegistrationInput("Peter", "123", "123")
        assertThat(result).isFalse()
    }

    @Test
    fun addition_emptyPassword() {
        val result = RegistrationUtil.validateRegistrationInput("Max D'Kot", "", "")
        assertThat(result).isFalse()
    }

    @Test
    fun addition_incorrectlySubmitPasswords() {
        val result = RegistrationUtil.validateRegistrationInput("Max D'Kot", "13", "12")
        assertThat(result).isFalse()
    }

    @Test
    fun addition_veryShortPasswordLength() {
        val result = RegistrationUtil.validateRegistrationInput("Max D'Kot", "1", "1")
        assertThat(result).isTrue()
    }
}