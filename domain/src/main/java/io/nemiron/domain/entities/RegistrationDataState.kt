package io.nemiron.domain.entities

data class RegistrationDataState(
    val isFirstNameCorrect: Boolean,
    val isLastNameCorrect: Boolean,
    val isGenderCorrect: Boolean,
    val isUsernameCorrect: Boolean,
    val isEmailCorrect: Boolean,
    val isPasswordStrong: Boolean,
    val isPasswordConfirmed: Boolean
) {
    /*
    * TODO(лучше решения придумать не удалось :( )
    * */
    val isAllValid: Boolean
        get() {
            return isFirstNameCorrect &&
                    isLastNameCorrect &&
                    isGenderCorrect &&
                    isUsernameCorrect &&
                    isEmailCorrect &&
                    isPasswordStrong &&
                    isPasswordConfirmed
        }
}
