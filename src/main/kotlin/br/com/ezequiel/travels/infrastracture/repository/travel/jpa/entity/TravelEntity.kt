package br.com.ezequiel.travels.infrastracture.repository.travel.jpa.entity

import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelDriver
import br.com.ezequiel.travels.domain.travel.model.TravelPassenger
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import org.springframework.data.annotation.Immutable
import java.time.LocalDateTime
import java.util.*
import javax.persistence.*
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@Entity
@Table(name = "travel")
data class TravelEntity(

    @field:Id
    @field:GeneratedValue
    @field:Column(columnDefinition = "uuid", name = "trv_id", updatable = false)
    val id: UUID = UUID.randomUUID(),

    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    @field:Column(name = "trv_origin")
    val origin: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 255)
    @field:Column(name = "trv_destination")
    val destination: String,

    @field:Valid
    @field:NotNull
    @field:ManyToOne
    @field:JoinColumn(name = "trv_psg_id", referencedColumnName = "psg_id")
    val passenger: TravelPassengerEntity,

    @field:Valid
    @field:ManyToOne
    @field:JoinColumn(name = "trv_drv_id", referencedColumnName = "drv_id")
    var driver: TravelDriverEntity? = null,

    @field:NotNull
    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "trv_status")
    var status: TravelStatus = TravelStatus.CREATED,

    @field:NotNull
    @field:Column(name = "trv_created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()

)

@Entity
@Table(name = "passenger")
@Immutable
data class TravelPassengerEntity(

    @field:Id
    @field:Column(columnDefinition = "uuid", name = "psg_id", updatable = false)
    val id: UUID,

    @field:Column(name = "psg_name")
    val name: String

)

@Entity
@Table(name = "driver")
@Immutable
data class TravelDriverEntity(

    @field:Id
    @field:Column(columnDefinition = "uuid", name = "drv_id", updatable = false)
    val id: UUID,

    @field:Column(name = "drv_name")
    val name: String

)

fun Travel.toEntity() = TravelEntity(

    id = id,
    origin = origin,
    destination = destination,
    passenger = TravelPassengerEntity(passenger.id, passenger.name),
    driver = driver?.let { TravelDriverEntity(it.id, it.name) }

)

fun TravelEntity.toModel() = Travel(

    id = id,
    origin = origin,
    destination = destination,
    passenger = TravelPassenger(passenger.id, passenger.name),
    driver = driver?.let { TravelDriver(it.id, it.name) },
    status = status

)