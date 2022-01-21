package br.com.ezequiel.travels.domain.travel

import br.com.ezequiel.travels.domain.travel.model.Travel
import java.util.*

interface RefuseTravel {

    fun execute(travelId: UUID): Travel

}