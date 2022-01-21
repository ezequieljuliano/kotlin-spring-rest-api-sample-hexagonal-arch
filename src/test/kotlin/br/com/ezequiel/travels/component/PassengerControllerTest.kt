package br.com.ezequiel.travels.component

import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class PassengerControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var passengerRepository: PassengerRepository

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port/travels-api"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown() {
        passengerRepository.deleteAll()
    }

    @Test
    fun whenCreatePassengerThenReturnStatusCreatedAndResponseBody() {
        val createPassengerJson = """{"name":"Ezequiel"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(createPassengerJson)
            .post("/passengers")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Ezequiel"))
    }

    @Test
    fun whenUpdatePassengerThenReturnStatusNoContent() {
        val passengerId = passengerRepository.save(Passenger(UUID.randomUUID(), "Ezequiel")).id
        val updatePassengerJson = """{"name":"Juliano"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(updatePassengerJson)
            .put("/passengers/$passengerId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenDeletePassengerThenReturnStatusNoContent() {
        val passengerId = passengerRepository.save(Passenger(UUID.randomUUID(), "Ezequiel")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/passengers/$passengerId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenGetPassengerThenReturnStatusOkAndResponseBody() {
        val passengerId = passengerRepository.save(Passenger(UUID.randomUUID(), "Ezequiel")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/passengers/$passengerId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Ezequiel"))
    }

    @Test
    fun whenListPassengersThenReturnStatusOkAndResponseBody() {
        passengerRepository.save(Passenger(UUID.randomUUID(), "Ezequiel"))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/passengers")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(CoreMatchers.notNullValue())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].name", CoreMatchers.equalTo("Ezequiel"))
    }

}