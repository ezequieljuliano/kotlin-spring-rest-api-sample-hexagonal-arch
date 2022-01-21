package br.com.ezequiel.travels.infrastracture.repository.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.infrastracture.repository.passenger.entity.toEntity
import br.com.ezequiel.travels.infrastracture.repository.passenger.entity.toModel
import br.com.ezequiel.travels.infrastracture.repository.passenger.support.PassengerSpringDataJpaRepository
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class PassengerJpaRepository(
    private val passengerSpringDataJpaRepository: PassengerSpringDataJpaRepository
) : PassengerRepository {

    override fun save(passenger: Passenger) = passengerSpringDataJpaRepository.save(passenger.toEntity()).toModel()

    override fun deleteById(passengerId: UUID): Unit = passengerSpringDataJpaRepository.deleteById(passengerId)

    override fun getById(passengerId: UUID) = passengerSpringDataJpaRepository.getById(passengerId).toModel()

    override fun findAll(): List<Passenger> =
        passengerSpringDataJpaRepository.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun existsById(passengerId: UUID) = passengerSpringDataJpaRepository.existsById(passengerId)

    override fun deleteAll(): Unit = passengerSpringDataJpaRepository.deleteAll()

}