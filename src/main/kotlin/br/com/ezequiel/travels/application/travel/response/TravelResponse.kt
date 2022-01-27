package br.com.ezequiel.travels.application.travel.response

import br.com.ezequiel.travels.domain.travel.model.Travel
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class TravelResponse(

    @field:Schema(description = "Travel request identifier")
    val id: UUID,

    @field:Schema(description = "Travel request origin")
    val origin: String,

    @field:Schema(description = "Travel request destination")
    val destination: String,

    @field:Schema(description = "Travel request passenger")
    val passenger: TravelPassengerResponse

)

fun Travel.toOutput() = TravelResponse(
    id = id!!,
    origin = origin,
    destination = destination,
    passenger = TravelPassengerResponse(passenger.id, passenger.name)
)
