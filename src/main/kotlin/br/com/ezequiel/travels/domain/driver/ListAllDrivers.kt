package br.com.ezequiel.travels.domain.driver

import br.com.ezequiel.travels.domain.driver.model.Driver

interface ListAllDrivers {

    fun execute(): List<Driver>

}