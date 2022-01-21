package br.com.ezequiel.travels.domain.travel

import br.com.ezequiel.travels.domain.travel.model.Travel
import java.util.*

interface AcceptTravel {

    fun execute(travelId: UUID, driverId: UUID): Travel

}