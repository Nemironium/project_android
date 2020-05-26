package io.nemiron.meetgo.interactors

import io.nemiron.meetgo.domain.RegistrationStatus
import io.nemiron.meetgo.domain.ValidateRegistrationData
import io.nemiron.meetgo.domain.entities.RegistrationInfo
import io.nemiron.meetgo.repositories.RegistrationRepository

class RegistrationUseCase(
    private val validate: ValidateRegistrationData,
    private val repository: RegistrationRepository
) {
    fun validateData(registrationInfo: RegistrationInfo): RegistrationStatus =
        validate(registrationInfo)

    fun submitRegistration(registrationInfo: RegistrationInfo) {
        TODO("Should invoke repository and return correct status from server")
    }
}