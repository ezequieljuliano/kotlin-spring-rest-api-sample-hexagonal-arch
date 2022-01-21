package br.com.ezequiel.travels.domain.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import java.util.*

interface GetPassenger {

    fun execute(passengerId: UUID): Passenger

}