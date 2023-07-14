package com.caperezh.baubaptest.domain

import com.caperezh.baubaptest.data.model.repository.LoginRepository
import javax.inject.Inject

class LoginCaseImpl @Inject constructor(
    private val loginRepository: LoginRepository
): LoginCase {
    override suspend operator fun invoke() = loginRepository.getLogin()
}

