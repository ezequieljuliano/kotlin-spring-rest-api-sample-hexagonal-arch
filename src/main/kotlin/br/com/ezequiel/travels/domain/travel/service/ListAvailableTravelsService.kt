package br.com.ezequiel.travels.domain.travel.service

import br.com.ezequiel.travels.domain.travel.ListAvailableTravels
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import org.springframework.stereotype.Service

@Service
class ListAvailableTravelsService(private val travelRepository: TravelRepository) : ListAvailableTravels {

    override fun execute(): List<Travel> = travelRepository.findByStatus(TravelStatus.CREATED)

}