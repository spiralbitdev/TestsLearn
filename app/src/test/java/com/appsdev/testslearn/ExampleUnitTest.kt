package com.appsdev.testslearn

import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class ExampleUnitTest {

    private lateinit var calculator: Calculator
    @Mock private var mockedList: MutableList<String>? = null

    @Before
    fun setUp() {
        calculator = Calculator()
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun plusNumber_returnTrue() {
        assertTrue(calculator.add(2, 2) == 4)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun arithmeticFunction() {

    }

    @Test
    fun verifyMockedList() {
        mockedList?.let { nonNullableMockedList ->
            nonNullableMockedList.add("Useless")
            nonNullableMockedList.clear()

            verify(nonNullableMockedList).add("Useless")
            verify(nonNullableMockedList).clear()
        }
    }

    @Test(expected = NullPointerException::class)
    fun expectedNPE_returnException() {
        val nullString: String? = null
        assertTrue(nullString!!.isEmpty())
    }

    @Test(timeout = 1000)
    fun requestTest() {
        Thread.sleep(2000)
    }

    @Test
    fun operations() {
        assertEquals(2, calculator.divide(10, 0))
        assertEquals(2, calculator.times(2, 1))
        assertEquals(3, calculator.minus(4, 1))
        assertEquals(5, calculator.add(2, 3))
    }
}