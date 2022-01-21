package br.com.ezequiel.travels.component

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
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
import java.time.LocalDate
import java.util.*

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class DriverControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var driverRepository: DriverRepository

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port/travels-api"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown() {
        driverRepository.deleteAll()
    }

    @Test
    fun whenCreateDriverThenReturnStatusCreatedAndResponseBody() {
        val createDriverJson = """{"name":"Ezequiel","birthdate":"1987-10-15"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(createDriverJson)
            .post("/drivers")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Ezequiel"))
            .body("birthdate", CoreMatchers.equalTo("1987-10-15"))
    }

    @Test
    fun whenFullUpdateDriverThenReturnStatusNoContent() {
        val driverId = driverRepository.save(Driver(UUID.randomUUID(), "Ezequiel", LocalDate.ofEpochDay(0))).id
        val updateDriverJson = """{"name":"Juliano","birthdate":"1987-10-15"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(updateDriverJson)
            .put("/drivers/$driverId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenPartialUpdateDriverThenReturnStatusNoContent() {
        val driverId = driverRepository.save(Driver(UUID.randomUUID(), "Ezequiel", LocalDate.ofEpochDay(0))).id
        val updateDriverJson = """{"name":"Juliano"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(updateDriverJson)
            .patch("/drivers/$driverId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenDeleteDriverThenReturnStatusNoContent() {
        val driverId = driverRepository.save(Driver(UUID.randomUUID(), "Ezequiel", LocalDate.ofEpochDay(0))).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/drivers/$driverId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenGetDriverThenReturnStatusOkAndResponseBody() {
        val driverId = driverRepository.save(Driver(UUID.randomUUID(), "Ezequiel", LocalDate.ofEpochDay(0))).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/drivers/$driverId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Ezequiel"))
            .body("birthdate", CoreMatchers.notNullValue())
    }

    @Test
    fun whenListDriversThenReturnStatusOkAndResponseBody() {
        driverRepository.save(Driver(UUID.randomUUID(), "Ezequiel", LocalDate.ofEpochDay(0)))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/drivers")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(CoreMatchers.notNullValue())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].name", CoreMatchers.equalTo("Ezequiel"))
            .body("[0].birthdate", CoreMatchers.notNullValue())
    }

}