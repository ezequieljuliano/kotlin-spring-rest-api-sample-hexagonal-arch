package br.com.ezequiel.travels.domain.travel

import br.com.ezequiel.travels.domain.travel.model.Travel

interface ListAvailableTravels {

    fun execute(): List<Travel>

}