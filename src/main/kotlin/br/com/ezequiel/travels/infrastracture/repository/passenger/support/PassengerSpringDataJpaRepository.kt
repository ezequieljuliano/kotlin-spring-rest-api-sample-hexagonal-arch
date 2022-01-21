package br.com.ezequiel.travels.infrastracture.repository.passenger.support

import br.com.ezequiel.travels.infrastracture.repository.passenger.entity.PassengerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface PassengerSpringDataJpaRepository : JpaRepository<PassengerEntity, UUID>