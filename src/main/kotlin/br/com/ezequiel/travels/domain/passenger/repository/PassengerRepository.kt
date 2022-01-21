package br.com.ezequiel.travels.domain.passenger.repository

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import java.util.*

interface PassengerRepository {

    fun save(passenger: Passenger): Passenger

    fun deleteById(passengerId: UUID)

    fun getById(passengerId: UUID): Passenger

    fun findAll(): List<Passenger>

    fun existsById(passengerId: UUID): Boolean

    fun deleteAll()

}