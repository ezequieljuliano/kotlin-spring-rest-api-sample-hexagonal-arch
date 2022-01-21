package br.com.ezequiel.travels.domain.passenger.model

import java.util.*

data class PassengerToCreate(

    val name: String

)

fun PassengerToCreate.toPassenger() = Passenger(

    id = UUID.randomUUID(),
    name = name

)
