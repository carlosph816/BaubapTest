package com.caperezh.baubaptest.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.caperezh.baubaptest.common.BaseError
import com.caperezh.baubaptest.common.DataState
import com.caperezh.baubaptest.data.model.UserModel
import com.caperezh.baubaptest.domain.LoginCaseImpl
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private val loginCase: LoginCaseImpl = mock()
    private lateinit var viewModel: LoginViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(loginCase)
    }

    @Test
    fun `login success sets showDialog to true`() = runBlocking {
        // Arrange
        val response = flowOf(
            DataState.Success(
                UserModel(
                    name = "Melani Echeverria",
                    email = "melani@baubap.com"
                )
            )
        )
        whenever(loginCase.invoke()).thenReturn(response)

        // Act
        viewModel.login()

        // Assert
        assertEquals(false, viewModel.showDialog)
        verify(loginCase).invoke()
        assertFalse(viewModel.showLoader)

        val captor = argumentCaptor<LoginState.Success>()
        assertEquals(captor.capture(), viewModel.loginState.first())
        assertEquals(LoginState.Success::class, captor.firstValue::class)
        assertEquals(true, viewModel.showDialog)
    }

    @Test
    fun `login error sets loginState to Error`() = runBlocking {
        // Arrange
        val errorMessage = BaseError("Login error")
        val response = flowOf(
            DataState.Error(
                errorMessage
            )
        )
        whenever(loginCase.invoke()).thenReturn(response)

        // Act
        viewModel.login()

        // Assert
        verify(loginCase).invoke()
        assertFalse(viewModel.showLoader)

        val captor = argumentCaptor<LoginState.Error>()
        assertEquals(captor.capture(), viewModel.loginState.first())
        assertEquals(LoginState.Error::class, captor.firstValue::class)
        assertEquals(errorMessage, captor.firstValue.message)
    }

    @Test
    fun `dismissDialog sets showDialog to false`() {
        // Act
        viewModel.dismissDialog()

        // Assert
        assertFalse(viewModel.showDialog)
    }
}
