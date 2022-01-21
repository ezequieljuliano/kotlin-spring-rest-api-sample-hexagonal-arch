package br.com.ezequiel.travels.infrastracture.repository.driver.support

import br.com.ezequiel.travels.infrastracture.repository.driver.entity.DriverEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DriverSpringDataJpaRepository : JpaRepository<DriverEntity, UUID>