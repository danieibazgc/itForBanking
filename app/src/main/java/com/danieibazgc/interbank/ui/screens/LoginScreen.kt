package com.danieibazgc.interbank.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danieibazgc.interbank.ui.theme.InterbankGreen
import com.danieibazgc.interbank.ui.theme.InterbankTextGray
import com.danieibazgc.interbank.ui.theme.InterbankTheme
import com.danieibazgc.interbank.utils.FormatUtils

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {}
) {
    var cardNumber by remember { mutableStateOf("") }
    var documentType by remember { mutableStateOf("DNI") }
    var documentNumber by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    var rememberData by remember { mutableStateOf(false) }
    var showDocumentTypeMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))

        // Logo de Interbank
        InterbankLogo()

        Spacer(modifier = Modifier.height(32.dp))

        // Imagen decorativa (placeholder)
        ImagePlaceholder()

        Spacer(modifier = Modifier.height(32.dp))

        // Campo de número de tarjeta
        CardNumberField(
            value = cardNumber,
            onValueChange = { newValue ->
                if (newValue.length <= 16) {
                    cardNumber = newValue.filter { it.isDigit() }
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de tipo y número de documento
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Dropdown para tipo de documento
            DocumentTypeDropdown(
                selectedType = documentType,
                onTypeSelected = { documentType = it },
                expanded = showDocumentTypeMenu,
                onExpandedChange = { showDocumentTypeMenu = it },
                modifier = Modifier.weight(1f)
            )

            // Campo de número de documento
            DocumentNumberField(
                value = documentNumber,
                onValueChange = { newValue ->
                    if (newValue.length <= 12) {
                        documentNumber = newValue.filter { it.isDigit() }
                    }
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de contraseña
        PasswordField(
            value = password,
            onValueChange = { password = it },
            passwordVisible = passwordVisible,
            onVisibilityChange = { passwordVisible = !passwordVisible }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Recordar datos y olvidé contraseña
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = rememberData,
                    onCheckedChange = { rememberData = it },
                    colors = CheckboxDefaults.colors(
                        checkedColor = InterbankGreen,
                        uncheckedColor = InterbankTextGray
                    )
                )
                Text(
                    text = "Recordar datos",
                    fontSize = 14.sp,
                    color = Color.Black
                )
            }

            TextButton(onClick = { /* Acción olvidé contraseña */ }) {
                Text(
                    text = "Olvidé mi contraseña",
                    color = InterbankGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Botón Ingresar
        Button(
            onClick = { onLoginSuccess() },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = InterbankGreen,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text(
                text = "Ingresar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Texto "¿Eres nuevo?"
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "¿Eres nuevo? ",
                fontSize = 14.sp,
                color = Color.Black
            )
            TextButton(onClick = { /* Acción registrarse */ }) {
                Text(
                    text = "Regístrate aquí",
                    color = InterbankGreen,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Botones de ubicación y contacto
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedButton(
                onClick = { /* Acción ubicación */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = InterbankGreen
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "Ubicación",
                    tint = InterbankGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Ubícanos",
                    color = InterbankGreen,
                    fontWeight = FontWeight.Medium
                )
            }

            OutlinedButton(
                onClick = { /* Acción contacto */ },
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = InterbankGreen
                ),
                border = ButtonDefaults.outlinedButtonBorder.copy(width = 0.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.Phone,
                    contentDescription = "Contacto",
                    tint = InterbankGreen,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Contacto",
                    color = InterbankGreen,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
fun InterbankLogo() {
    // Logo usando texto estilizado
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(
                    color = Color(0xFF003D7A),
                    shape = RoundedCornerShape(4.dp)
                )
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Interbank",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = InterbankGreen
        )
    }
}

@Composable
fun ImagePlaceholder() {
    Box(
        modifier = Modifier
            .size(200.dp, 150.dp)
            .background(
                color = Color(0xFFF0F0F0),
                shape = RoundedCornerShape(12.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        // Líneas verdes decorativas (simulación)
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            repeat(5) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .background(
                            color = InterbankGreen,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardNumberField(
    value: String,
    onValueChange: (String) -> Unit
) {
    val formattedValue = FormatUtils.formatCardNumber(value)

    OutlinedTextField(
        value = formattedValue,
        onValueChange = { newValue ->
            onValueChange(newValue.filter { it.isDigit() })
        },
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "1623 23•• •••• 9433",
                color = InterbankTextGray
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = InterbankGreen,
            unfocusedBorderColor = Color(0xFFDDDDDD),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentTypeDropdown(
    selectedType: String,
    onTypeSelected: (String) -> Unit,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        modifier = modifier
    ) {
        OutlinedTextField(
            value = selectedType,
            onValueChange = {},
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = InterbankGreen,
                unfocusedBorderColor = Color(0xFFDDDDDD),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(8.dp),
            singleLine = true
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            listOf("DNI", "CE", "Pasaporte").forEach { type ->
                DropdownMenuItem(
                    text = { Text(type) },
                    onClick = {
                        onTypeSelected(type)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentNumberField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        placeholder = {
            Text(
                text = "••••8519",
                color = InterbankTextGray
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = InterbankGreen,
            unfocusedBorderColor = Color(0xFFDDDDDD),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    passwordVisible: Boolean,
    onVisibilityChange: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        placeholder = {
            Text(
                text = "••••••••••••••••",
                color = InterbankTextGray
            )
        },
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            IconButton(onClick = onVisibilityChange) {
                Text(
                    text = if (passwordVisible) "👁" else "👁‍🗨",
                    fontSize = 20.sp,
                    color = InterbankTextGray
                )
            }
        },
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = InterbankGreen,
            unfocusedBorderColor = Color(0xFFDDDDDD),
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        ),
        shape = RoundedCornerShape(8.dp),
        singleLine = true
    )
}

// Función helper para formatear el número de tarjeta
fun formatCardNumber(number: String): String {
    val cleaned = number.filter { it.isDigit() }
    return cleaned.chunked(4).joinToString(" ")
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    InterbankTheme {
        LoginScreen()
    }
}



