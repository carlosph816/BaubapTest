package com.caperezh.baubaptest.data.model

import com.caperezh.baubaptest.common.BaseError

class UserProvider {

    companion object {

        const val timeResponse:Long = 1000
        const val code:Int  = 200

        internal fun getUserRandom(): UserModel {
            val position = (user.indices).random()
            return user[position]
        }

        internal fun getStatusCodeRandom(): BaseError {
            val position = (statusCode.indices).random()
            return statusCode[position]
        }

        private val statusCode = listOf(
            BaseError(
                code = 200
            ),
            BaseError(
                code = 400
            )
        )

        private val user = listOf(
            UserModel(
                name = "Carlos Andres Perez",
                email = "carlos@baubap.com"
            ),
            UserModel(
                name = "Manuel Rivera",
                email = "manuel@baubap.com"
            ),
            UserModel(
                name = "Jose Molina",
                email = "jose@baubap.com"
            ),
            UserModel(
                name = "Guadalupe Montes",
                email = "guadalupe816@baubap.com"
            ),
            UserModel(
                name = "Antonieta Gomez",
                email = "antonieta@baubap.com"
            ),
            UserModel(
                name = "Melani Echeverria",
                email = "melani@baubap.com"
            ),
            UserModel(
                name = "Jonathan Lopez",
                email = "jonathan@baubap.com"
            ),
            UserModel(
                name = "Andres Aguilar",
                email = "andres816@baubap.com"
            ),
            UserModel(
                name = "Ivan Condado",
                email = "carlosph816@baubap.com"
            ),
            UserModel(
                name = "Luis Mendez",
                email = "luis@baubap.com"
            ),
            UserModel(
                name = "Susana Hernandez",
                email = "susana@baubap.com"
            )
        )
    }
}