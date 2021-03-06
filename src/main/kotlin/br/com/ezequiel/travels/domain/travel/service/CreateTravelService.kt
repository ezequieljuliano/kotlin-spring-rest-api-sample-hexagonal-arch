package br.com.ezequiel.travels.domain.travel.service

import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.travel.CreateTravel
import br.com.ezequiel.travels.domain.travel.model.TravelToCreate
import br.com.ezequiel.travels.domain.travel.model.toTravel
import br.com.ezequiel.travels.domain.travel.model.toTravelPassenger
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import org.springframework.stereotype.Service

@Service
class CreateTravelService(
    private val travelRepository: TravelRepository,
    private val passengerRepository: PassengerRepository
) : CreateTravel {

    override fun execute(travelToCreate: TravelToCreate) =
        travelRepository.save(
            travelToCreate.toTravel(
                passengerRepository.getById(travelToCreate.passengerId).toTravelPassenger()
            )
        )

}