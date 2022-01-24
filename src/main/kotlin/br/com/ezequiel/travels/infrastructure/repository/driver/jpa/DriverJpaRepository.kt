package br.com.ezequiel.travels.infrastructure.repository.driver.jpa

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.infrastructure.repository.driver.jpa.entity.toEntity
import br.com.ezequiel.travels.infrastructure.repository.driver.jpa.entity.toModel
import br.com.ezequiel.travels.infrastructure.repository.driver.jpa.provider.SpringDataDriverProvider
import org.springframework.stereotype.Component
import java.util.*
import java.util.stream.Collectors

@Component
class DriverJpaRepository(private val springDataDriverProvider: SpringDataDriverProvider) : DriverRepository {

    override fun save(driver: Driver) = springDataDriverProvider.save(driver.toEntity()).toModel()

    override fun deleteById(driverId: UUID): Unit = springDataDriverProvider.deleteById(driverId)

    override fun getById(driverId: UUID) = springDataDriverProvider.getById(driverId).toModel()

    override fun findAll(): List<Driver> =
        springDataDriverProvider.findAll().stream().map { it.toModel() }.collect(Collectors.toList())

    override fun existsById(driverId: UUID) = springDataDriverProvider.existsById(driverId)

    override fun deleteAll(): Unit = springDataDriverProvider.deleteAll()

}