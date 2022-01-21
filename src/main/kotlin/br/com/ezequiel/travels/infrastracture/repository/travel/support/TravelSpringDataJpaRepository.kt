package br.com.ezequiel.travels.infrastracture.repository.travel.support

import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.infrastracture.repository.travel.entity.TravelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TravelSpringDataJpaRepository : JpaRepository<TravelEntity, UUID> {

    fun findByStatus(status: TravelStatus): List<TravelEntity>

}