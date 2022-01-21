package br.com.ezequiel.travels.application.travel.output

import io.swagger.v3.oas.annotations.media.Schema
import java.util.UUID

data class TravelPassengerOutput(

    @field:Schema(description = "Passenger identifier")
    val id: UUID,

    @field:Schema(description = "Passenger name")
    val name: String

)
