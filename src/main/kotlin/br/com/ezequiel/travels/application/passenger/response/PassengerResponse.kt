package br.com.ezequiel.travels.application.passenger.response

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class PassengerResponse(

    @field:Schema(description = "Passenger identifier")
    val id: UUID,

    @field:Schema(description = "Passenger name")
    val name: String

)

fun Passenger.toOutput() = PassengerResponse(

    id = id,
    name = name

)
