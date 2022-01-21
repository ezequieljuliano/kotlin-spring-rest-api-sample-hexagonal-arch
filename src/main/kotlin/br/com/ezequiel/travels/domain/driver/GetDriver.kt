package br.com.ezequiel.travels.domain.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import java.util.*

interface GetDriver {

    fun execute(driverId: UUID): Driver

}