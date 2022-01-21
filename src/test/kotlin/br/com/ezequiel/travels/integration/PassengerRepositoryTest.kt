package br.com.ezequiel.travels.integration

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
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
class PassengerRepositoryTest {

    @Autowired
    private lateinit var subject: PassengerRepository

    @AfterEach
    fun tearDown() {
        subject.deleteAll()
    }

    @Test
    fun whenSavePassengerThenReturnSavedPassenger() {
        // given
        val passenger = Passenger(UUID.randomUUID(), "Jon Snow")

        // when
        val result = subject.save(passenger)

        // then
        Assertions.assertEquals(passenger.name, result.name)
    }

    @Test
    fun whenDeleteByIdPassengerThenSuccessfullyDeletePassenger() {
        // given
        val passenger = Passenger(UUID.randomUUID(), "Jon Snow")
        val driverId = subject.save(passenger).id

        // when
        subject.deleteById(driverId)

        // then
        Assertions.assertFalse(subject.existsById(driverId))
    }

    @Test
    fun whenGetByIdPassengerThenReturnPassenger() {
        // given
        val passenger = Passenger(UUID.randomUUID(), "Jon Snow")
        val id = subject.save(passenger).id

        // when
        val result = subject.getById(id)

        // then
        Assertions.assertEquals(id, result.id)
        Assertions.assertEquals("Jon Snow", result.name)
    }

    @Test
    fun whenFindAllPassengersThenReturnAllPassengers() {
        // given
        subject.save(Passenger(UUID.randomUUID(), "Jon Snow"))
        subject.save(Passenger(UUID.randomUUID(), "Arya Stark"))

        // when
        val result = subject.findAll()

        // then
        Assertions.assertEquals(2, result.size)
    }

}