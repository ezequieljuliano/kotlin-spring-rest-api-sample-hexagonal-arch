package br.com.ezequiel.travels.component

import br.com.ezequiel.travels.domain.driver.model.Driver
import br.com.ezequiel.travels.domain.driver.repository.DriverRepository
import br.com.ezequiel.travels.domain.passenger.model.Passenger
import br.com.ezequiel.travels.domain.passenger.repository.PassengerRepository
import br.com.ezequiel.travels.domain.travel.model.Travel
import br.com.ezequiel.travels.domain.travel.model.TravelDriver
import br.com.ezequiel.travels.domain.travel.model.TravelPassenger
import br.com.ezequiel.travels.domain.travel.model.TravelStatus
import br.com.ezequiel.travels.domain.travel.repository.TravelRepository
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class TravelRequestControllerTest {

    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var travelRepository: TravelRepository

    @Autowired
    private lateinit var passengerRepository: PassengerRepository

    @Autowired
    private lateinit var driverRepository: DriverRepository

    @BeforeEach
    fun setup() {
        RestAssured.baseURI = "http://localhost:$port/travels-api"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown() {
        travelRepository.deleteAll()
    }

    @Test
    fun whenCreateTravelRequestThenReturnStatusCreatedAndResponseBody() {
        val passengerId = passengerRepository.save(Passenger(null, "Ezequiel")).id
        val createTravelRequestJson = """ {"origin":"maravilha","destination":"campinas","passengerId":"$passengerId"} """
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(createTravelRequestJson)
            .post("/travel-requests")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("origin", CoreMatchers.equalTo("maravilha"))
            .body("destination", CoreMatchers.equalTo("campinas"))
            .body("passenger.id", CoreMatchers.notNullValue())
            .body("passenger.name", CoreMatchers.equalTo("Ezequiel"))
    }

    @Test
    fun whenAcceptTravelRequestThenReturnStatusNoContent() {
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        val travel = Travel(null, "Origin", "Destination", travelPassenger, TravelStatus.CREATED, null)
        val travelId = travelRepository.save(travel).id
        val driverId = driverRepository.save(Driver(null, "Ezequiel", LocalDate.ofEpochDay(0))).id
        val driverTravelRequestJson = """{"driverId":"$driverId"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(driverTravelRequestJson)
            .put("/travel-requests/$travelId/accept")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenRefuseTravelRequestThenReturnStatusNoContent() {
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        val driver = driverRepository.save(Driver(null, "Ezequiel", LocalDate.ofEpochDay(0)))
        val travelDriver = TravelDriver(driver.id!!, driver.name)
        val travel = Travel(null, "Origin", "Destination", travelPassenger, TravelStatus.ACCEPTED, travelDriver)
        val travelId = travelRepository.save(travel).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .put("/travel-requests/$travelId/refuse")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }

    @Test
    fun whenListTravelRequestsThenReturnStatusOkAndResponseBody() {
        val passenger = passengerRepository.save(Passenger(null, "Jon Snow"))
        val travelPassenger = TravelPassenger(passenger.id!!, passenger.name)
        val travel = Travel(null, "Origin", "Destination", travelPassenger, TravelStatus.CREATED, null)
        travelRepository.save(travel)
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/travel-requests/available")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body(CoreMatchers.notNullValue())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].origin", CoreMatchers.equalTo("Origin"))
            .body("[0].destination", CoreMatchers.equalTo("Destination"))
            .body("[0].passenger.id", CoreMatchers.notNullValue())
            .body("[0].passenger.name", CoreMatchers.equalTo("Jon Snow"))
    }

}