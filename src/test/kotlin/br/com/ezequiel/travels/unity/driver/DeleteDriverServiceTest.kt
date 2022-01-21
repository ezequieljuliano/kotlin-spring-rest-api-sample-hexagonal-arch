package br.com.ezequiel.travels.unity.driver

import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.driver.service.DeleteDriverService
import io.mockk.*
import org.junit.jupiter.api.Test
import java.util.*

class DeleteDriverServiceTest {

    private val driverRepository: DriverRepository = mockk(relaxed = true)
    private val subject = DeleteDriverService(driverRepository)
    private val driverId = UUID.randomUUID()

    @Test
    fun whenDeleteDriverThenRunSuccessfullyWithoutExceptions() {
        // given
        every { driverRepository.deleteById(driverId) } just Runs

        // when
        subject.execute(driverId)

        // then
        verify(exactly = 1) { driverRepository.deleteById(driverId) }
    }

}