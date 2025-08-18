package com.codenova.mindmate.ui.modifiers

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

fun Modifier.flexibleWidth(width: Dp?): Modifier  {
    return if(width != null) this.width(width) else this.fillMaxWidth()
}