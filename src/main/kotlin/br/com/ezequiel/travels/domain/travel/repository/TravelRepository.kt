package br.com.ezequiel.travels.domain.travel.repository

import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import java.util.*

interface TravelRepository {

    fun save(travel: Travel): Travel

    fun getById(travelId: UUID): Travel

    fun findByStatus(status: TravelStatus): List<Travel>

    fun deleteAll()

}