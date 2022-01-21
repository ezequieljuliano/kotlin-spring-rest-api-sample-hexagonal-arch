package br.com.ezequiel.travels.domain.travel.service

import br.com.ezequiel.travels.domain.travel.RefuseTravel
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class RefuseTravelService(private val travelRepository: TravelRepository) : RefuseTravel {

    override fun execute(travelId: UUID): Travel {
        val travel = travelRepository.getById(travelId)
        travel.status = TravelStatus.REFUSED
        travelRepository.save(travel)
        return travel
    }

}