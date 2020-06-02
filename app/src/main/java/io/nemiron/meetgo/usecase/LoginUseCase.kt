package io.nemiron.meetgo.usecase

import com.haroldadmin.cnradapter.NetworkResponse
import io.nemiron.domain.entities.CommonError
import io.nemiron.domain.entities.LoginAnswer
import io.nemiron.domain.entities.LoginInfo
import io.nemiron.meetgo.data.repositories.AuthorizationRepository
import io.nemiron.meetgo.extensions.ServerError
import io.nemiron.meetgo.extensions.handleServerError

class LoginUseCase(
    private val repository: AuthorizationRepository
)  {

    suspend operator fun invoke(info: LoginInfo): LoginAnswer {
        /*TODO("сделать LoginAnswer immutable объектом")*/
        val loginAnswer = LoginAnswer()
        when(val login = repository.loginUser(info)) {
            is NetworkResponse.Success -> {
                loginAnswer.isSuccessful = true
            }
            is NetworkResponse.NetworkError -> {
                loginAnswer.error = CommonError.getConnectionError(login.error)
            }
            is NetworkResponse.UnknownError -> {
                loginAnswer.error = CommonError.UNEXPECTED_ERROR
            }
            is NetworkResponse.ServerError -> when(login.handleServerError()) {
                ServerError.USER_NOT_FOUND -> loginAnswer.isUsernameCorrect = false
                ServerError.LOGIN_FAILED -> loginAnswer.isPasswordCorrect = false
                /*TODO(вот тут нужно подумать: нужно ли сохранять текстовое
                   сообщение с сервера об ошибке и показывать пользвоателю)*/
                else -> loginAnswer.error = CommonError.SERVER_ERROR
            }
        }
        return loginAnswer
    }
}
