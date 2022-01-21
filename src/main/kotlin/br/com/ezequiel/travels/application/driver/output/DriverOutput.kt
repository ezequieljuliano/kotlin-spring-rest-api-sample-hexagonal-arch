package br.com.ezequiel.travels.application.driver.output

import br.com.ezequiel.travels.domain.driver.model.Driver
import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDate
import java.util.*

data class DriverOutput(

    @field:Schema(description = "Driver identifier")
    val id: UUID,

    @field:Schema(description = "Driver name")
    val name: String,

    @field:Schema(description = "Driver birthdate")
    val birthdate: LocalDate

)

fun Driver.toOutput() = DriverOutput(

    id = id,
    name = name,
    birthdate = birthdate

)
