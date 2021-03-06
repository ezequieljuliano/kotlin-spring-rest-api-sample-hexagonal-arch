package br.com.ezequiel.travels.integration

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelPassenger
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class TravelRepositoryTest {

    @Autowired
    private lateinit var passengerRepository: PassengerRepository

    @Autowired
    private lateinit var subject: TravelRepository

    @AfterEach
    fun tearDown() {
        subject.deleteAll()
    }

    @Test
    fun whenSaveTravelThenReturnSavedTravel() {
        // given
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        val travel = Travel(null, "Origin", "Destination", travelPassenger, TravelStatus.CREATED, null)

        // when
        val result = subject.save(travel)

        // then
        Assertions.assertEquals(travel.origin, result.origin)
        Assertions.assertEquals(travel.destination, result.destination)
        Assertions.assertEquals(travel.passenger.id, result.passenger.id)
    }

    @Test
    fun whenGetByIdTravelThenReturnTravel() {
        // given
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        val travel = Travel(null, "Origin", "Destination", travelPassenger, TravelStatus.CREATED, null)
        val travelId = subject.save(travel).id ?: throw RuntimeException("TravelId is null")

        // when
        val result = subject.getById(travelId)

        // then
        Assertions.assertEquals(travelId, result.id)
        Assertions.assertEquals("Origin", result.origin)
        Assertions.assertEquals("Destination", result.destination)
        Assertions.assertEquals(travelPassenger.id, result.passenger.id)
    }

    @Test
    fun whenFindTravelsByStatusThenReturnTravelsList() {
        subject.deleteAll()

        // given
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        subject.save(Travel(null, "Origin1", "Destination1", travelPassenger, TravelStatus.CREATED, null))
        subject.save(Travel(null, "Origin2", "Destination2", travelPassenger, TravelStatus.CREATED, null))

        // when
        val result = subject.findByStatus(TravelStatus.CREATED)

        // then
        Assertions.assertEquals(2, result.size)
    }

}