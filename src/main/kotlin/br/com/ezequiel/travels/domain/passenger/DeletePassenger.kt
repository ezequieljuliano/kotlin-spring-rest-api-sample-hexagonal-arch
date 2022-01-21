package br.com.ezequiel.travels.domain.passenger

import java.util.*

interface DeletePassenger {

    fun execute(passengerId: UUID)

}