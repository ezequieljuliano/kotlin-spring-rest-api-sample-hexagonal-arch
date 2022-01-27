package br.com.ezequiel.travels.infrastructure.repository.passenger.jpa.entity

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import java.util.*
import javax.persistence.*
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

@Entity
@Table(name = "passenger")
data class PassengerEntity(

    @field:Id
    @field:GeneratedValue
    @field:Column(columnDefinition = "uuid", name = "psg_id", updatable = false)
    val id: UUID?,

    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    @field:Column(name = "psg_name")
    val name: String

)

fun Passenger.toEntity() = PassengerEntity(
    id = id,
    name = name
)

fun PassengerEntity.toModel() = Passenger(
    id = id,
    name = name
)