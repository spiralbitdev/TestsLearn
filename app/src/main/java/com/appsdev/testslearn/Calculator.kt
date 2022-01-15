package com.appsdev.testslearn

import android.util.Log

class Calculator {

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun minus(a: Int, b: Int): Int {
        return a - b
    }

    fun times(a: Int, b: Int): Int {
        return a * b
    }

    fun divide(a: Int, b: Int): Int {
        return if (b != 0) {
            a / b
        } else {
            0
        }
    }
}