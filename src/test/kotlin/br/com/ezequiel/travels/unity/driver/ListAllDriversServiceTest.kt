package br.com.ezequiel.travels.unity.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.driver.service.ListAllDriversService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class ListAllDriversServiceTest {

    private val driverRepository: DriverRepository = mockk(relaxed = true)
    private val subject = ListAllDriversService(driverRepository)
    private val mockedDrivers = List(2) {
        Driver(UUID.randomUUID(), "Jon Snow", LocalDate.MIN)
        Driver(UUID.randomUUID(), "Arya Stark", LocalDate.MAX)
    }

    @Test
    fun whenListDriversThenReturnDriversSuccessfully() {
        // given
        every { driverRepository.findAll() } returns mockedDrivers

        // when
        val result = subject.execute()

        // then
        Assertions.assertEquals(mockedDrivers.size, result.size)
        assertEquals(mockedDrivers[0], result[0])
        assertEquals(mockedDrivers[1], result[1])
        verify(exactly = 1) { driverRepository.findAll() }
    }

    private fun assertEquals(mockedDriver: Driver, resultDriver: Driver) {
        Assertions.assertEquals(mockedDriver.id, resultDriver.id)
        Assertions.assertEquals(mockedDriver.name, resultDriver.name)
        Assertions.assertEquals(mockedDriver.birthdate, resultDriver.birthdate)
    }

}