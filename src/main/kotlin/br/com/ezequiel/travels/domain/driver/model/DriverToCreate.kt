package br.com.ezequiel.travels.domain.driver.model

import java.time.LocalDate
import java.util.*

data class DriverToCreate(

    val name: String,
    val birthdate: LocalDate

)

fun DriverToCreate.toDriver() = Driver(

    id = UUID.randomUUID(),
    name = name,
    birthdate = birthdate

)
