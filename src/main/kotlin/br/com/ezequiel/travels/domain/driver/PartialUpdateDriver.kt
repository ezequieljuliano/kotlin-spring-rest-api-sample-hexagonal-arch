package br.com.ezequiel.travels.domain.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToPartialUpdate

interface PartialUpdateDriver {

    fun execute(driverToUpdate: DriverToPartialUpdate): Driver

}