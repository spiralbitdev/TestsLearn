package com.appsdev.testslearn

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class ResourceCompareTest {

    private lateinit var resourceCompare: ResourceCompare

    @Before
    fun setUp() {
        resourceCompare = ResourceCompare()
    }

    @After
    fun tearDown() {
        // destroy object, but gc make that by itself, if we are test room db, here we will close it.
    }

    // 1. NPE
    // 2. Error while mapper
    // 3.

    @Test
    fun mapGiDBToGifUI_returnFalse() {

    }

    @Test
    fun mapGiDBToGifUI_returnNPE() {

    }

    @Test
    fun stringResourceSameAsGivenString_returnsTrue() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "TestsLearn")
        assertThat(result).isTrue()
    }

    @Test
    fun stringResourceDifferentAsGivenString_returnsFalse() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceCompare.isEqual(context, R.string.app_name, "Tests")
        assertThat(result).isFalse()
    }
}