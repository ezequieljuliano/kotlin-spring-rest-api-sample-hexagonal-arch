package br.com.ezequiel.travels.unity.driver

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.driver.service.GetDriverService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*

class GetDriverServiceTest {

    private val driverRepository: DriverRepository = mockk(relaxed = true)
    private val subject = GetDriverService(driverRepository)
    private val mockedDriver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.MIN)
    private val driverId = mockedDriver.id!!

    @Test
    fun whenGetDriverThenReturnDriverSuccessfully() {
        // given
        every { driverRepository.getById(driverId) } returns mockedDriver

        // when
        val result = subject.execute(driverId)

        // then
        Assertions.assertEquals(mockedDriver.id, result.id)
        Assertions.assertEquals(mockedDriver.name, result.name)
        Assertions.assertEquals(mockedDriver.birthdate, result.birthdate)
        verify(exactly = 1) { driverRepository.getById(driverId) }
    }

}