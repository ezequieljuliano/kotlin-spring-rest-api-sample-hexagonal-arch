package br.com.ezequiel.travels.application.passenger

import br.com.ezequiel.travels.application.passenger.input.PassengerToCreateInput
import br.com.ezequiel.travels.application.passenger.input.PassengerToUpdateInput
import br.com.ezequiel.travels.application.passenger.input.toModel
import br.com.ezequiel.travels.application.passenger.output.PassengerOutput
import br.com.ezequiel.travels.application.passenger.output.toOutput
import br.com.ezequiel.travels.domain.passenger.*
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
    path = ["/passengers"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Passengers API", description = "Manage passenger data")
class PassengerController(
    private val createPassenger: CreatePassenger,
    private val updatePassenger: UpdatePassenger,
    private val getPassenger: GetPassenger,
    private val listAllPassengers: ListAllPassengers,
    private val deletePassenger: DeletePassenger
) {

    @GetMapping
    @Operation(description = "List all available passengers")
    fun listPassengers(): List<PassengerOutput> =
        listAllPassengers.execute().stream().map { it.toOutput() }.collect(Collectors.toList())

    @GetMapping("/{id}")
    @Operation(description = "Returns a passenger by id")
    fun getPassenger(@PathVariable("id") id: UUID) = getPassenger.execute(id).toOutput()

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(description = "Create a new passenger")
    fun createPassenger(@Valid @RequestBody passengerToCreate: PassengerToCreateInput) =
        createPassenger.execute(passengerToCreate.toModel()).toOutput()

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Update a passenger by id")
    fun updatePassenger(@PathVariable("id") id: UUID, @Valid @RequestBody passengerToUpdate: PassengerToUpdateInput) {
        updatePassenger.execute(passengerToUpdate.toModel(id))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Delete a passenger by id")
    fun deletePassenger(@PathVariable("id") id: UUID) {
        deletePassenger.execute(id)
    }

}