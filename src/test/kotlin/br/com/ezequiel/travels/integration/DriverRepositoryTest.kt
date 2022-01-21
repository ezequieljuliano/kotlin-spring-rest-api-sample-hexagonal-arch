package br.com.ezequiel.travels.integration

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.time.LocalDate
import java.util.*

@SpringBootTest
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class DriverRepositoryTest {

    @Autowired
    private lateinit var subject: DriverRepository

    @AfterEach
    fun tearDown() {
        subject.deleteAll()
    }

    @Test
    fun whenSaveDriverThenReturnSavedDriver() {
        // given
        val driver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.ofEpochDay(0))

        // when
        val result = subject.save(driver)

        // then
        Assertions.assertEquals(driver.name, result.name)
        Assertions.assertEquals(driver.birthdate, result.birthdate)
    }

    @Test
    fun whenDeleteByIdDriverThenSuccessfullyDeleteDriver() {
        // given
        val driver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.ofEpochDay(0))
        val driverId = subject.save(driver).id

        // when
        subject.deleteById(driverId)

        // then
        Assertions.assertFalse(subject.existsById(driverId))
    }

    @Test
    fun whenGetByIdDriverThenReturnDriver() {
        // given
        val driver = Driver(UUID.randomUUID(), "Jon Snow", LocalDate.ofEpochDay(0))
        val driverId = subject.save(driver).id

        // when
        val result = subject.getById(driverId)

        // then
        Assertions.assertEquals(driverId, result.id)
        Assertions.assertEquals("Jon Snow", result.name)
        Assertions.assertEquals(LocalDate.ofEpochDay(0), result.birthdate)
    }

    @Test
    fun whenFindAllDriversThenReturnAllDrivers() {
        // given
        subject.save(Driver(UUID.randomUUID(), "Jon Snow", LocalDate.ofEpochDay(0)))
        subject.save(Driver(UUID.randomUUID(), "Arya Stark", LocalDate.ofEpochDay(0)))

        // when
        val result = subject.findAll()

        // then
        Assertions.assertEquals(2, result.size)
    }

}