package br.com.ezequiel.travels.unity.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToPartialUpdate
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.driver.service.PartialUpdateDriverService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class PartialUpdateDriverServiceTest {

    private val driverRepository: DriverRepository = mockk(relaxed = true)
    private val subject = PartialUpdateDriverService(driverRepository)
    private val oldMockedDriver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.MIN)
    private val updatedAllPropertiesMockDriver = Driver(oldMockedDriver.id, "Arya Stark", LocalDate.MAX)
    private val updatedNameMockDriver = Driver(oldMockedDriver.id, "Arya Stark", LocalDate.MIN)
    private val updatedBirthdateMockDriver = Driver(oldMockedDriver.id, "Jon Snow", LocalDate.MAX)

    @Test
    fun whenAllPropertiesPartialUpdateDriverThenReturnUpdatedDriver() {
        // given
        val updatedDriver = DriverToPartialUpdate(
            updatedAllPropertiesMockDriver.id,
            updatedAllPropertiesMockDriver.name,
            updatedAllPropertiesMockDriver.birthdate
        )
        every { driverRepository.getById(oldMockedDriver.id) } returns oldMockedDriver
        every { driverRepository.save(any()) } returns updatedAllPropertiesMockDriver

        // when
        val result = subject.execute(updatedDriver)

        // then
        Assertions.assertEquals(updatedAllPropertiesMockDriver.id, result.id)
        Assertions.assertEquals(updatedAllPropertiesMockDriver.name, result.name)
        Assertions.assertEquals(updatedAllPropertiesMockDriver.birthdate, result.birthdate)
        verify(exactly = 1) { driverRepository.getById(oldMockedDriver.id) }
        verify(exactly = 1) { driverRepository.save(any()) }
    }

    @Test
    fun whenNamePartialUpdateDriverThenReturnUpdatedDriver() {
        // given
        val updatedDriver = DriverToPartialUpdate(
            updatedNameMockDriver.id,
            updatedNameMockDriver.name,
            null
        )
        every { driverRepository.getById(oldMockedDriver.id) } returns oldMockedDriver
        every { driverRepository.save(any()) } returns updatedNameMockDriver

        // when
        val result = subject.execute(updatedDriver)

        // then
        Assertions.assertEquals(updatedNameMockDriver.id, result.id)
        Assertions.assertEquals(updatedNameMockDriver.name, result.name)
        Assertions.assertEquals(updatedNameMockDriver.birthdate, result.birthdate)
        verify(exactly = 1) { driverRepository.getById(oldMockedDriver.id) }
        verify(exactly = 1) { driverRepository.save(any()) }
    }

    @Test
    fun whenBirthdatePartialUpdateDriverThenReturnUpdatedDriver() {
        // given
        val updatedDriver = DriverToPartialUpdate(
            updatedBirthdateMockDriver.id,
            null,
            updatedBirthdateMockDriver.birthdate
        )
        every { driverRepository.getById(oldMockedDriver.id) } returns oldMockedDriver
        every { driverRepository.save(any()) } returns updatedBirthdateMockDriver

        // when
        val result = subject.execute(updatedDriver)

        // then
        Assertions.assertEquals(updatedBirthdateMockDriver.id, result.id)
        Assertions.assertEquals(updatedBirthdateMockDriver.name, result.name)
        Assertions.assertEquals(updatedBirthdateMockDriver.birthdate, result.birthdate)
        verify(exactly = 1) { driverRepository.getById(oldMockedDriver.id) }
        verify(exactly = 1) { driverRepository.save(any()) }
    }

}