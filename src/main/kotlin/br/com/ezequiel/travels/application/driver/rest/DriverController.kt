package br.com.ezequiel.travels.application.driver.rest

import br.com.ezequiel.travels.application.driver.request.DriverToCreateRequest
import br.com.ezequiel.travels.application.driver.request.DriverToFullUpdateRequest
import br.com.ezequiel.travels.application.driver.request.DriverToPartialUpdateRequest
import br.com.ezequiel.travels.application.driver.request.toModel
import br.com.ezequiel.travels.application.driver.response.DriverResponse
import br.com.ezequiel.travels.application.driver.response.toOutput
import br.com.ezequiel.travels.domain.driver.*
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors
import javax.validation.Valid

@RestController
@RequestMapping(
    path = ["/drivers"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Drivers API", description = "Manage driver data")
class DriverController(
    private val createDriver: CreateDriver,
    private val fullUpdateDriver: FullUpdateDriver,
    private val partialUpdateDriver: PartialUpdateDriver,
    private val getDriver: GetDriver,
    private val listAllDrivers: ListAllDrivers,
    private val deleteDriver: DeleteDriver
) {

    @GetMapping
    @Operation(description = "List all available drivers")
    fun listDrivers(): List<DriverResponse> =
        listAllDrivers.execute().stream().map { it.toOutput() }.collect(Collectors.toList())

    @GetMapping("/{id}")
    @Operation(description = "Returns a driver by id")
    fun getDriver(@PathVariable("id") id: UUID) = getDriver.execute(id).toOutput()

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(description = "Create a new driver")
    fun createDriver(@Valid @RequestBody driverToCreate: DriverToCreateRequest) =
        createDriver.execute(driverToCreate.toModel()).toOutput()

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Update a driver by id")
    fun fullUpdateDriver(
        @PathVariable("id") id: UUID,
        @Valid @RequestBody driverToFullUpdate: DriverToFullUpdateRequest
    ) {
        fullUpdateDriver.execute(driverToFullUpdate.toModel(id))
    }

    @PatchMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Partially update a driver by id")
    fun partialUpdateDriver(
        @PathVariable("id") id: UUID,
        @Valid @RequestBody driverToPartialUpdate: DriverToPartialUpdateRequest
    ) {
        partialUpdateDriver.execute(driverToPartialUpdate.toModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Delete a driver by id")
    fun deleteDriver(@PathVariable("id") id: UUID) {
        deleteDriver.execute(id)
    }

}