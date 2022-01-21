package br.com.ezequiel.travels.domain.driver.service

import br.com.ezequiel.travels.domain.driver.PartialUpdateDriver
import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToPartialUpdate
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import org.springframework.stereotype.Service

@Service
class PartialUpdateDriverService(private val driverRepository: DriverRepository) : PartialUpdateDriver {

    override fun execute(driverToUpdate: DriverToPartialUpdate): Driver {
        val foundDriver = driverRepository.getById(driverToUpdate.id)
        val updateDriver = foundDriver.copy(
            name = driverToUpdate.name ?: foundDriver.name,
            birthdate = driverToUpdate.birthdate ?: foundDriver.birthdate,
        )
        return driverRepository.save(updateDriver)
    }

}