package com.example.netroniktest.data.remoute.dto

data class UserDto(
    val login: LoginDto?,
    val gender: String?,
    val name: NameDto?,
    val location: LocationDto?,
    val email: String?,
    val phone: String?,
    val cell: String?,
    val picture: PictureDto?,
)