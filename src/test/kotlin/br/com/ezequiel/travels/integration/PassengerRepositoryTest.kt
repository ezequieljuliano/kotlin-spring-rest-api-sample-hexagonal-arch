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
        val passenger = Passenger(null, "Jon Snow")

        // when
        val result = subject.save(passenger)

        // then
        Assertions.assertEquals(passenger.name, result.name)
    }

    @Test
    fun whenDeleteByIdPassengerThenSuccessfullyDeletePassenger() {
        // given
        val passenger = Passenger(null, "Jon Snow")
        val passengerId = subject.save(passenger).id ?: throw RuntimeException("PassengerId is null")

        // when
        subject.deleteById(passengerId)

        // then
        Assertions.assertFalse(subject.existsById(passengerId))
    }

    @Test
    fun whenGetByIdPassengerThenReturnPassenger() {
        // given
        val passenger = Passenger(null, "Jon Snow")
        val passengerId = subject.save(passenger).id ?: throw RuntimeException("PassengerId is null")

        // when
        val result = subject.getById(passengerId)

        // then
        Assertions.assertEquals(passengerId, result.id)
        Assertions.assertEquals("Jon Snow", result.name)
    }

    @Test
    fun whenFindAllPassengersThenReturnAllPassengers() {
        subject.deleteAll()

        // given
        subject.save(Passenger(null, "Jon Snow"))
        subject.save(Passenger(null, "Arya Stark"))

        // when
        val result = subject.findAll()

        // then
        Assertions.assertEquals(2, result.size)
    }

}