package com.joniaranguri.platzi.android.core.testutils

import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.unmockkAll
import org.junit.After
import org.junit.Before
import org.junit.Rule

open class BaseUnitTest {

    open fun onCreate() {}

    open fun onDestroy() {}

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        onCreate()
    }

    @After
    fun tearDown() {
        onDestroy()
        unmockkAll()
        clearAllMocks()
    }
}
