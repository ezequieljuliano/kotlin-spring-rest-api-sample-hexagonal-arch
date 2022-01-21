package br.com.ezequiel.travels.infrastracture.repository.travel

import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import br.com.ezequiel.travels.infrastracture.repository.travel.entity.toEntity
import br.com.ezequiel.travels.infrastracture.repository.travel.entity.toModel
import br.com.ezequiel.travels.infrastracture.repository.travel.support.TravelSpringDataJpaRepository
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class TravelJpaRepository(private val travelSpringDataJpaRepository: TravelSpringDataJpaRepository) : TravelRepository {

    override fun save(travel: Travel) = travelSpringDataJpaRepository.save(travel.toEntity()).toModel()

    override fun getById(travelId: UUID) = travelSpringDataJpaRepository.getById(travelId).toModel()

    override fun findByStatus(status: TravelStatus): List<Travel> =
        travelSpringDataJpaRepository.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun deleteAll(): Unit = travelSpringDataJpaRepository.deleteAll()

}