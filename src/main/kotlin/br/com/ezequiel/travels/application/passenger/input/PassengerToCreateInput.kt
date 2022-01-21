package br.com.ezequiel.travels.application.passenger.input

import br.com.ezequiel.travels.domain.passenger.model.PassengerToCreate
import io.swagger.v3.oas.annotations.media.Schema
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class PassengerToCreateInput(

    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    @field:Schema(description = "Passenger name")
    val name: String

)

fun PassengerToCreateInput.toModel() = PassengerToCreate(

    name = name

)
