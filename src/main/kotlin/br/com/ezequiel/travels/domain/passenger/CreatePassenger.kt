package br.com.ezequiel.travels.domain.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.model.PassengerToCreate

interface CreatePassenger {

    fun execute(passengerToCreate: PassengerToCreate): Passenger

}