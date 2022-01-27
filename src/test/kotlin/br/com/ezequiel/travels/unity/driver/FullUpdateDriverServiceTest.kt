package br.com.ezequiel.travels.unity.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.model.DriverToFullUpdate
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.driver.service.FullUpdateDriverService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class FullUpdateDriverServiceTest {

    private val driverRepository: DriverRepository = mockk(relaxed = true)
    private val subject = FullUpdateDriverService(driverRepository)
    private val oldMockedDriver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.MIN)
    private val updatedMockDriver = Driver(oldMockedDriver.id, "Arya Stark", LocalDate.MAX)

    @Test
    fun whenFullUpdateDriverThenReturnUpdatedDriver() {
        // given
        val updatedDriver = DriverToFullUpdate(updatedMockDriver.id!!, updatedMockDriver.name, updatedMockDriver.birthdate)
        every { driverRepository.getById(oldMockedDriver.id!!) } returns oldMockedDriver
        every { driverRepository.save(any()) } returns updatedMockDriver

        // when
        val result = subject.execute(updatedDriver)

        // then
        Assertions.assertEquals(updatedMockDriver.id, result.id)
        Assertions.assertEquals(updatedMockDriver.name, result.name)
        Assertions.assertEquals(updatedMockDriver.birthdate, result.birthdate)
        verify(exactly = 1) { driverRepository.getById(oldMockedDriver.id!!) }
        verify(exactly = 1) { driverRepository.save(any()) }
    }

}