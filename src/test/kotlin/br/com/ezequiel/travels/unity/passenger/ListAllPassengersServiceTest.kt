package br.com.ezequiel.travels.unity.passenger

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.passenger.service.ListAllPassengersService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ListAllPassengersServiceTest {

    private val passengerRepository: PassengerRepository = mockk(relaxed = true)
    private val subject = ListAllPassengersService(passengerRepository)
    private val mockedPassengers = List(2) {
        Passenger(UUID.randomUUID(), "Jon Snow")
        Passenger(UUID.randomUUID(), "Arya Stark")
    }

    @Test
    fun whenListPassengersThenReturnPassengersSuccessfully() {
        // given
        every { passengerRepository.findAll() } returns mockedPassengers

        // when
        val result = subject.execute()

        // then
        Assertions.assertEquals(mockedPassengers.size, result.size)
        assertEquals(mockedPassengers[0], result[0])
        assertEquals(mockedPassengers[1], result[1])
        verify(exactly = 1) { passengerRepository.findAll() }
    }

    private fun assertEquals(mockedPassenger: Passenger, resultPassenger: Passenger) {
        Assertions.assertEquals(mockedPassenger.id, resultPassenger.id)
        Assertions.assertEquals(mockedPassenger.name, resultPassenger.name)
    }

}