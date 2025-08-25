package com.codenova.mindmate.data.mapper

import com.codenova.mindmate.data.remote.dto.RegisterResponseDto
import com.codenova.mindmate.domain.model.User

class UserMapper {

    fun toDomain(userDto: RegisterResponseDto): User {
        return User(
            id = userDto.id,
            email = userDto.email,
            role = userDto.role
        )
    }
}