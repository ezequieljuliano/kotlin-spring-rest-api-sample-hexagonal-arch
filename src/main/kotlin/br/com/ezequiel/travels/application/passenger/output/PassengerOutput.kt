package br.com.ezequiel.travels.application.passenger.output

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import io.swagger.v3.oas.annotations.media.Schema
import java.util.*

data class PassengerOutput(

    @field:Schema(description = "Passenger identifier")
    val id: UUID,

    @field:Schema(description = "Passenger name")
    val name: String

)

fun Passenger.toOutput() = PassengerOutput(

    id = id,
    name = name

)
