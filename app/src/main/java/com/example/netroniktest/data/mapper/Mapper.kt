package com.example.netroniktest.data.mapper

import com.example.netroniktest.data.local.UserEntity
import com.example.netroniktest.data.remoute.dto.UserDto
import com.example.netroniktest.domain.model.User

fun UserDto.toDomain() = User(
    id = login?.uuid.orEmpty(),
    fullName = listOfNotNull(name?.first, name?.last).joinToString(" "),
    email = email.orEmpty(),
    phone = phone.orEmpty(),
    country = location?.country.orEmpty(),
    city = location?.city.orEmpty(),
    picture = picture?.medium.orEmpty()
)

fun UserEntity.toDomain(): User = User(
    id = id,
    fullName = fullName,
    email = email,
    phone = phone,
    country = country,
    city = city,
    picture = picture
)

fun User.toEntity(): UserEntity = UserEntity(
    id = id,
    fullName = fullName,
    email = email,
    phone = phone,
    country = country,
    city = city,
    picture = picture
)