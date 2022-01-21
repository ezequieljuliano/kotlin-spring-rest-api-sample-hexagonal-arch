package br.com.ezequiel.travels.infrastracture.repository.driver.entity

import br.com.ezequiel.travels.domain.driver.model.Driver
import java.time.LocalDate
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Past
import javax.validation.constraints.Size

@Entity
@Table(name = "driver")
data class DriverEntity(

    @field:Id
    @field:GeneratedValue
    @field:Column(columnDefinition = "uuid", name = "drv_id", updatable = false)
    val id: UUID = UUID.randomUUID(),

    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    @field:Column(name = "drv_name")
    val name: String,

    @field:NotNull
    @field:Past
    @field:Column(name = "drv_birthdate")
    val birthdate: LocalDate

)

fun Driver.toEntity() = DriverEntity(

    id = id,
    name = name,
    birthdate = birthdate

)

fun DriverEntity.toModel() = Driver(

    id = id,
    name = name,
    birthdate = birthdate

)
