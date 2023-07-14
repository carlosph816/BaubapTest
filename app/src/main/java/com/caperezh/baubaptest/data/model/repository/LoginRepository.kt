package com.caperezh.baubaptest.data.model.repository

import com.caperezh.baubaptest.common.DataState
import com.caperezh.baubaptest.data.model.UserModel
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun getLogin() : Flow<DataState<UserModel>>
}