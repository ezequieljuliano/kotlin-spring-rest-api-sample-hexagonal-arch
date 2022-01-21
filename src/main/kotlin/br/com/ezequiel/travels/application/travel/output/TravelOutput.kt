package br.com.ezequiel.travels.application.travel.output

import br.com.ezequiel.travels.domain.travel.model.Travel
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class TravelOutput(

    @field:Schema(description = "Travel request identifier")
    val id: UUID,

    @field:Schema(description = "Travel request origin")
    val origin: String,

    @field:Schema(description = "Travel request destination")
    val destination: String,

    @field:Schema(description = "Travel request passenger")
    val passenger: TravelPassengerOutput

)

fun Travel.toOutput() = TravelOutput(

    id = id,
    origin = origin,
    destination = destination,
    passenger = TravelPassengerOutput(passenger.id, passenger.name)

)
