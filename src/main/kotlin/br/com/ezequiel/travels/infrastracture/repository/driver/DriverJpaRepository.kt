package br.com.ezequiel.travels.infrastracture.repository.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.infrastracture.repository.driver.entity.toEntity
import br.com.ezequiel.travels.infrastracture.repository.driver.entity.toModel
import br.com.ezequiel.travels.infrastracture.repository.driver.support.DriverSpringDataJpaRepository
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class DriverJpaRepository(private val driverSpringDataJpaRepository: DriverSpringDataJpaRepository) : DriverRepository {

    override fun save(driver: Driver) = driverSpringDataJpaRepository.save(driver.toEntity()).toModel()

    override fun deleteById(driverId: UUID): Unit = driverSpringDataJpaRepository.deleteById(driverId)

    override fun getById(driverId: UUID) = driverSpringDataJpaRepository.getById(driverId).toModel()

    override fun findAll(): List<Driver> =
        driverSpringDataJpaRepository.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun existsById(driverId: UUID) = driverSpringDataJpaRepository.existsById(driverId)

    override fun deleteAll(): Unit = driverSpringDataJpaRepository.deleteAll()

}