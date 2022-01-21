package br.com.ezequiel.travels.application.travel

import br.com.ezequiel.travels.application.travel.input.TravelDriverInput
import br.com.ezequiel.travels.application.travel.input.TravelToCreateInput
import br.com.ezequiel.travels.application.travel.input.toModel
import br.com.ezequiel.travels.application.travel.output.TravelOutput
import br.com.ezequiel.travels.application.travel.output.toOutput
import br.com.ezequiel.travels.domain.travel.AcceptTravel
import br.com.ezequiel.travels.domain.travel.CreateTravel
import br.com.ezequiel.travels.domain.travel.ListAvailableTravels
import br.com.ezequiel.travels.domain.travel.RefuseTravel
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
    path = ["/travel-requests"],
    produces = [MediaType.APPLICATION_JSON_VALUE],
    consumes = [MediaType.APPLICATION_JSON_VALUE]
)
@Tag(name = "Travel Requests API", description = "Manage travel requests data")
class TravelRequestController(
    private val acceptTravel: AcceptTravel,
    private val createTravel: CreateTravel,
    private val listAvailableTravels: ListAvailableTravels,
    private val refuseTravel: RefuseTravel
) {

    @GetMapping("/available")
    @Operation(description = "List all available travel requests")
    fun listAvailableTravels(): List<TravelOutput> =
        listAvailableTravels.execute().stream().map { it.toOutput() }.collect(Collectors.toList())

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    @Operation(description = "Create a new travel request")
    fun createTravelRequest(@Valid @RequestBody travelToCreate: TravelToCreateInput) =
        createTravel.execute(travelToCreate.toModel()).toOutput()

    @PutMapping("/{id}/accept")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Driver accept a travel request")
    fun acceptTravelRequest(@PathVariable("id") id: UUID, @Valid @RequestBody travelDriver: TravelDriverInput) {
        acceptTravel.execute(id, travelDriver.driverId)
    }

    @PutMapping("/{id}/refuse")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @Operation(description = "Refuse a travel request")
    fun refuseTravelRequest(@PathVariable("id") id: UUID) {
        refuseTravel.execute(id)
    }

}