package br.com.ezequiel.travels.domain.driver.service

import br.com.ezequiel.travels.domain.driver.FullUpdateDriver
import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToFullUpdate
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import org.springframework.stereotype.Service

@Service
class FullUpdateDriverService(private val driverRepository: DriverRepository) : FullUpdateDriver {

    override fun execute(driverToUpdate: DriverToFullUpdate): Driver {
        val foundDriver = driverRepository.getById(driverToUpdate.id)
        val updateDriver = foundDriver.copy(name = driverToUpdate.name, birthdate = driverToUpdate.birthdate)
        return driverRepository.save(updateDriver)
    }

}