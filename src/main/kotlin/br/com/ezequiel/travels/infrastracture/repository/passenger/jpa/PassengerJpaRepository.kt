package br.com.ezequiel.travels.infrastracture.repository.passenger.jpa

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.infrastracture.repository.passenger.jpa.entity.toEntity
import br.com.ezequiel.travels.infrastracture.repository.passenger.jpa.entity.toModel
import br.com.ezequiel.travels.infrastracture.repository.passenger.jpa.provider.SpringDataPassengerProvider
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class PassengerJpaRepository(private val springDataPassengerProvider: SpringDataPassengerProvider) : PassengerRepository {

    override fun save(passenger: Passenger) = springDataPassengerProvider.save(passenger.toEntity()).toModel()

    override fun deleteById(passengerId: UUID): Unit = springDataPassengerProvider.deleteById(passengerId)

    override fun getById(passengerId: UUID) = springDataPassengerProvider.getById(passengerId).toModel()

    override fun findAll(): List<Passenger> =
        springDataPassengerProvider.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun existsById(passengerId: UUID) = springDataPassengerProvider.existsById(passengerId)

    override fun deleteAll(): Unit = springDataPassengerProvider.deleteAll()

}