package br.com.ezequiel.travels.unity.passenger

import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.passenger.service.DeletePassengerService
import io.mockk.*
import org.junit.jupiter.api.Test
import java.util.*

class DeletePassengerServiceTest {

    private val passengerRepository: PassengerRepository = mockk(relaxed = true)
    private val subject = DeletePassengerService(passengerRepository)
    private val passengerId = UUID.randomUUID()

    @Test
    fun whenDeletePassengerThenRunSuccessfullyWithoutExceptions() {
        // given
        every { passengerRepository.deleteById(passengerId) } just Runs

        // when
        subject.execute(passengerId)

        // then
        verify(exactly = 1) { passengerRepository.deleteById(passengerId) }
    }

}