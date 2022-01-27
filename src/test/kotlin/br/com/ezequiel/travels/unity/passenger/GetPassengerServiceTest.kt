package br.com.ezequiel.travels.unity.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.passenger.service.GetPassengerService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class GetPassengerServiceTest {

    private val passengerRepository: PassengerRepository = mockk(relaxed = true)
    private val subject = GetPassengerService(passengerRepository)
    private val mockedPassenger = Passenger(UUID.randomUUID(), "Jon Snow")
    private val passengerId = mockedPassenger.id!!

    @Test
    fun whenGetPassengerThenReturnPassengerSuccessfully() {
        // given
        every { passengerRepository.getById(passengerId) } returns mockedPassenger

        // when
        val result = subject.execute(passengerId)

        // then
        Assertions.assertEquals(mockedPassenger.id, result.id)
        Assertions.assertEquals(mockedPassenger.name, result.name)
        verify(exactly = 1) { passengerRepository.getById(passengerId) }
    }

}