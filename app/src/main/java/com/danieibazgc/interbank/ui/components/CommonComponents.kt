package com.danieibazgc.interbank.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danieibazgc.interbank.ui.theme.InterbankGreen

/**
 * Componentes reutilizables para la aplicación Interbank
 */

@Composable
fun InterbankButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    backgroundColor: Color = InterbankGreen,
    contentColor: Color = Color.White
) {
    androidx.compose.material3.Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        enabled = enabled,
        colors = androidx.compose.material3.ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.5f),
            disabledContentColor = contentColor.copy(alpha = 0.5f)
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun InterbankOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    icon: @Composable (() -> Unit)? = null
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = androidx.compose.material3.ButtonDefaults.outlinedButtonColors(
            contentColor = InterbankGreen
        ),
        border = androidx.compose.material3.ButtonDefaults.outlinedButtonBorder.copy(width = 0.dp)
    ) {
        if (icon != null) {
            icon()
            Spacer(modifier = Modifier.width(8.dp))
        }
        Text(
            text = text,
            color = InterbankGreen,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SectionSpacer(height: Int = 16) {
    Spacer(modifier = Modifier.height(height.dp))
}

@Composable
fun DecorativeStripe(
    color: Color = InterbankGreen,
    height: Int = 8
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(
                color = color,
                shape = RoundedCornerShape(4.dp)
            )
    )
}

