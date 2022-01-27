package br.com.ezequiel.travels.unity.travel

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelPassenger
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.model.TravelToCreate
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import br.com.ezequiel.travels.domain.travel.service.CreateTravelService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class CreateTravelServiceTest {

    private val travelRepository: TravelRepository = mockk(relaxed = true)
    private val passengerRepository: PassengerRepository = mockk(relaxed = true)
    private val subject = CreateTravelService(travelRepository, passengerRepository)
    private val mockedPassenger = Passenger(UUID.randomUUID(), "Ezequiel")
    private val mockedTravelPassenger = TravelPassenger(mockedPassenger.id!!, mockedPassenger.name)
    private val mockedTravel = Travel(
        UUID.randomUUID(), "Origin", "Destination", mockedTravelPassenger, TravelStatus.CREATED, null
    )

    @Test
    fun whenCreateTravelRequestThenReturnCreatedTravel() {
        // given
        val newTravel = TravelToCreate(
            mockedTravel.origin, mockedTravel.destination, mockedTravel.passenger.id
        )
        every { travelRepository.save(any()) } returns mockedTravel
        every { passengerRepository.getById(any()) } returns mockedPassenger

        // when
        val result = subject.execute(newTravel)

        // then
        Assertions.assertEquals(mockedTravel.id, result.id)
        Assertions.assertEquals(mockedTravel.origin, result.origin)
        Assertions.assertEquals(mockedTravel.destination, result.destination)
        Assertions.assertEquals(mockedTravel.passenger.id, result.passenger.id)
        verify(exactly = 1) { travelRepository.save(any()) }
    }

}