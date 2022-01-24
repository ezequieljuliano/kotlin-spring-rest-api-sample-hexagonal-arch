package br.com.ezequiel.travels.infrastructure.repository.travel.jpa.provider

import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.infrastructure.repository.travel.jpa.entity.TravelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataTravelProvider : JpaRepository<TravelEntity, UUID> {

    fun findByStatus(status: TravelStatus): List<TravelEntity>

}