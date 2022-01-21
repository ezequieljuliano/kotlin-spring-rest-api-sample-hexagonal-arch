package br.com.ezequiel.travels.domain.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToFullUpdate

interface FullUpdateDriver {

    fun execute(driverToUpdate: DriverToFullUpdate): Driver

}