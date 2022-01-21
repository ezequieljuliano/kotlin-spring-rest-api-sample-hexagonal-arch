package br.com.ezequiel.travels.domain.driver.service

import br.com.ezequiel.travels.domain.driver.ListAllDrivers
import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import org.springframework.stereotype.Service

@Service
class ListAllDriversService(private val driverRepository: DriverRepository) : ListAllDrivers {

    override fun execute(): List<Driver> = driverRepository.findAll()

}