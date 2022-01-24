package br.com.ezequiel.travels.infrastracture.repository.passenger.jpa.provider

import br.com.ezequiel.travels.infrastracture.repository.passenger.jpa.entity.PassengerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataPassengerProvider : JpaRepository<PassengerEntity, UUID>