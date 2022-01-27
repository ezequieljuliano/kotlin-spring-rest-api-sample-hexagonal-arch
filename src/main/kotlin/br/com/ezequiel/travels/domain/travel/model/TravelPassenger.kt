package br.com.ezequiel.travels.domain.travel.model

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import java.util.*

data class TravelPassenger(

    val id: UUID,
    val name: String

)

fun Passenger.toTravelPassenger() = TravelPassenger(
    id = id!!,
    name = name
)
