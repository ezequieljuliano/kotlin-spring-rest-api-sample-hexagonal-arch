package br.com.ezequiel.travels.domain.driver.repository

import br.com.ezequiel.travels.domain.driver.model.Driver
import java.util.*

interface DriverRepository {

    fun save(driver: Driver): Driver

    fun deleteById(driverId: UUID)

    fun getById(driverId: UUID): Driver

    fun findAll(): List<Driver>

    fun existsById(driverId: UUID): Boolean

    fun deleteAll()

}