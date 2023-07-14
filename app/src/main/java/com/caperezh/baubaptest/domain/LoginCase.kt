package com.caperezh.baubaptest.domain

import com.caperezh.baubaptest.common.DataState
import com.caperezh.baubaptest.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LoginCase {
    suspend operator fun invoke(): Flow<DataState<UserModel>>
}
