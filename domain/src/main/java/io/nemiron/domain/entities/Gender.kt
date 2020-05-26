package io.nemiron.domain.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
enum class Gender {
    @SerialName("male") MALE,
    @SerialName("female") FEMALE,
    @SerialName("another") ANOTHER,
    @Transient BLANK;

    /*
    * FIXME(метод не будет корректно работать с другой локализацией;
    *  Нужно придумать способ, как без ресурсов нормально сделать)
    * */
    companion object {
        fun getGender(stringGender: String): Gender = when(stringGender) {
            "Male" -> MALE
            "Female" -> FEMALE
            "Another" -> ANOTHER
            else -> BLANK
        }
    }
}