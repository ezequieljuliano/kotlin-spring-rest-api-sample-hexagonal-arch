package br.com.ezequiel.travels.infrastracture.repository.travel.jpa

import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import br.com.ezequiel.travels.infrastracture.repository.travel.jpa.entity.toEntity
import br.com.ezequiel.travels.infrastracture.repository.travel.jpa.entity.toModel
import br.com.ezequiel.travels.infrastracture.repository.travel.jpa.provider.SpringDataTravelProvider
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class TravelJpaRepository(private val springDataTravelProvider: SpringDataTravelProvider) : TravelRepository {

    override fun save(travel: Travel) = springDataTravelProvider.save(travel.toEntity()).toModel()

    override fun getById(travelId: UUID) = springDataTravelProvider.getById(travelId).toModel()

    override fun findByStatus(status: TravelStatus): List<Travel> =
        springDataTravelProvider.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun deleteAll(): Unit = springDataTravelProvider.deleteAll()

}