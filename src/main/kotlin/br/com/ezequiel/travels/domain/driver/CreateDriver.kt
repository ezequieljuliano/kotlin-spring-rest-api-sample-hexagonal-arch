package br.com.ezequiel.travels.domain.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToCreate

interface CreateDriver {

    fun execute(driverToCreate: DriverToCreate): Driver

}