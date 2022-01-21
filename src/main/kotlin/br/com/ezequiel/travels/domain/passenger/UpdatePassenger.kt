package br.com.ezequiel.travels.domain.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.model.PassengerToUpdate

interface UpdatePassenger {

    fun execute(passengerToUpdate: PassengerToUpdate): Passenger

}