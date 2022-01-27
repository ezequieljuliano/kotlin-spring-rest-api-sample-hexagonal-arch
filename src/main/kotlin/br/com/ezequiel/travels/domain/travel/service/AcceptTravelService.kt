package br.com.ezequiel.travels.domain.travel.service

import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.travel.AcceptTravel
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelDriver
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class AcceptTravelService(
    private val travelRepository: TravelRepository,
    private val driverRepository: DriverRepository
) : AcceptTravel {

    override fun execute(travelId: UUID, driverId: UUID): Travel {
        val travel = travelRepository.getById(travelId)
        val driver = driverRepository.getById(driverId)
        travel.status = TravelStatus.ACCEPTED
        travel.driver = TravelDriver(driver.id!!, driver.name)
        travelRepository.save(travel)
        return travel
    }

}