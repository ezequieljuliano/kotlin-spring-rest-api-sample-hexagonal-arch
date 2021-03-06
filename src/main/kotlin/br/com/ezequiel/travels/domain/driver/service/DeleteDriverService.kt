package br.com.ezequiel.travels.domain.driver.service

import br.com.ezequiel.travels.domain.driver.DeleteDriver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class DeleteDriverService(private val driverRepository: DriverRepository) : DeleteDriver {

    override fun execute(driverId: UUID): Unit = driverRepository.deleteById(driverId)

}