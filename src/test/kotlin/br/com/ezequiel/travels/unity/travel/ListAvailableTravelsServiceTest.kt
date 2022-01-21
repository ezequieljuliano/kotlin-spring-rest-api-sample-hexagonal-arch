package br.com.ezequiel.travels.unity.travel

import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelPassenger
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import br.com.ezequiel.travels.domain.travel.service.ListAvailableTravelsService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*

class ListAvailableTravelsServiceTest {

    private val travelRepository: TravelRepository = mockk(relaxed = true)
    private val subject = ListAvailableTravelsService(travelRepository)
    private val mockedPassenger = TravelPassenger(UUID.randomUUID(), "Ezequiel")
    private val mockedTravels = List(2) {
        Travel(UUID.randomUUID(), "Origin1", "Destination1", mockedPassenger, TravelStatus.CREATED, null)
        Travel(UUID.randomUUID(), "Origin2", "Destination2", mockedPassenger, TravelStatus.CREATED, null)
    }

    @Test
    fun whenListAvailableTravelsThenReturnListSuccessfully() {
        // given
        every { travelRepository.findByStatus(TravelStatus.CREATED) } returns mockedTravels

        // when
        val result = subject.execute()

        // then
        Assertions.assertEquals(mockedTravels.size, result.size)
        assertEquals(mockedTravels[0], result[0])
        assertEquals(mockedTravels[1], result[1])
        verify(exactly = 1) { travelRepository.findByStatus(TravelStatus.CREATED) }
    }

    private fun assertEquals(mockedTravel: Travel, resultTravel: Travel) {
        Assertions.assertEquals(mockedTravel.id, resultTravel.id)
        Assertions.assertEquals(mockedTravel.origin, resultTravel.origin)
        Assertions.assertEquals(mockedTravel.destination, resultTravel.destination)
        Assertions.assertEquals(mockedTravel.passenger.id, resultTravel.passenger.id)
        Assertions.assertEquals(mockedTravel.driver?.id, resultTravel.driver?.id)
        Assertions.assertEquals(TravelStatus.CREATED, resultTravel.status)
    }

}