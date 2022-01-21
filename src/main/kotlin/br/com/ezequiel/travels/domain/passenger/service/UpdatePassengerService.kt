package br.com.ezequiel.travels.domain.passenger.service

import br.com.ezequiel.travels.domain.passenger.UpdatePassenger
import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.model.PassengerToUpdate
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import org.springframework.stereotype.Service

@Service
class UpdatePassengerService(private val passengerRepository: PassengerRepository) : UpdatePassenger {

    override fun execute(passengerToUpdate: PassengerToUpdate): Passenger {
        val foundPassenger = passengerRepository.getById(passengerToUpdate.id)
        val updatePassenger = foundPassenger.copy(name = passengerToUpdate.name)
        return passengerRepository.save(updatePassenger)
    }

}