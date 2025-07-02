package com.codenova.mindmate.data.model

import androidx.annotation.DrawableRes

data class Onboarding(
    @DrawableRes val imageId: Int,
    val title: String,
    val description: String
)
