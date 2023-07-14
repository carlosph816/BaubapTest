package com.caperezh.baubaptest.presentation.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caperezh.baubaptest.presentation.viewmodel.LoginState
import com.caperezh.baubaptest.presentation.viewmodel.LoginViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class LoginViewInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun loginState_Error_ShowSnackBar() {
        val errorMessage = "Login error message"
        var snackBarMessage: String? = null
        composeTestRule.setContent {
            LoginView(
                showSnackBar = { message -> snackBarMessage = message },
                viewModel = mockk(relaxed = true)
            )
        }

        composeTestRule.onNodeWithText(errorMessage).assertDoesNotExist()
        composeTestRule.runOnIdle {
            snackBarMessage?.let { message ->
                snackBarMessage = null
            }
        }
        Assert.assertEquals(snackBarMessage,errorMessage )
    }

    @Test
    fun loginState_Success_ShowDialog() {
        val mockViewModel = mockk<LoginViewModel>(relaxed = true)
        every { mockViewModel.loginState } returns MutableStateFlow(LoginState.Success)
        every { mockViewModel.showDialog } returns true
        composeTestRule.setContent {
            LoginView(
                showSnackBar = mockk(relaxed = true),
                viewModel = mockViewModel
            )
        }
        composeTestRule.onNodeWithText("Baubap").assertIsDisplayed()
        composeTestRule.onNodeWithText("Se ha logueado correctamente.").assertIsDisplayed()
        composeTestRule.onNodeWithText("Aceptar").performClick()
        verify { mockViewModel.dismissDialog() }
    }

}