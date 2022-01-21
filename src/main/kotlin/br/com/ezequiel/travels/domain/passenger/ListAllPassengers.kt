package br.com.ezequiel.travels.domain.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger

interface ListAllPassengers {

    fun execute(): List<Passenger>

}