package br.com.ezequiel.travels.infrastructure.repository.driver.jpa.provider

import br.com.ezequiel.travels.infrastructure.repository.driver.jpa.entity.DriverEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SpringDataDriverProvider : JpaRepository<DriverEntity, UUID>