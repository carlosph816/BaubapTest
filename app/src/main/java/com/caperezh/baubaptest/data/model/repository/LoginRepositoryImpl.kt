package com.caperezh.baubaptest.data.model.repository

import com.caperezh.baubaptest.common.BaseError
import com.caperezh.baubaptest.common.DataState
import com.caperezh.baubaptest.data.model.UserModel
import com.caperezh.baubaptest.data.model.UserProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository{

    override suspend fun getLogin(): Flow<DataState<UserModel>> = flow {
        emit(DataState.Loading)
        delay(UserProvider.timeResponse)
        val response = UserProvider.getStatusCodeRandom()
        if(response.code == UserProvider.code){
            emit(DataState.Success(UserProvider.getUserRandom()))
        }else{
            emit(DataState.Error(BaseError()))
        }
    }
}

