package com.danieibazgc.interbank.utils

/**
 * Utilidades para formateo de datos en la aplicación Interbank
 */

object FormatUtils {

    /**
     * Formatea un número de tarjeta agregando espacios cada 4 dígitos
     * Ejemplo: "1234567890123456" -> "1234 5678 9012 3456"
     */
    fun formatCardNumber(cardNumber: String): String {
        val cleaned = cardNumber.filter { it.isDigit() }
        return cleaned.chunked(4).joinToString(" ")
    }

    /**
     * Formatea un número de tarjeta ocultando los dígitos del medio
     * Ejemplo: "1234567890123456" -> "1234 56•• •••• 3456"
     */
    fun formatCardNumberMasked(cardNumber: String): String {
        val cleaned = cardNumber.filter { it.isDigit() }
        if (cleaned.length < 8) return cleaned

        val first4 = cleaned.take(4)
        val last4 = cleaned.takeLast(4)
        val middle = cleaned.substring(4, cleaned.length - 4)
        val maskedMiddle = middle.replace(Regex("."), "•")

        return "$first4 ${maskedMiddle.chunked(4).joinToString(" ")} $last4"
    }

    /**
     * Limpia un número dejando solo dígitos
     */
    fun cleanNumericInput(input: String): String {
        return input.filter { it.isDigit() }
    }

    /**
     * Valida que un número de tarjeta tenga el formato correcto
     */
    fun isValidCardNumberLength(cardNumber: String): Boolean {
        val cleaned = cleanNumericInput(cardNumber)
        return cleaned.length == 16
    }

    /**
     * Valida que un DNI tenga el formato correcto
     */
    fun isValidDNI(dni: String): Boolean {
        val cleaned = cleanNumericInput(dni)
        return cleaned.length == 8
    }

    /**
     * Valida que un CE tenga el formato correcto
     */
    fun isValidCE(ce: String): Boolean {
        val cleaned = cleanNumericInput(ce)
        return cleaned.length in 9..12
    }

    /**
     * Valida el tipo y número de documento
     */
    fun isValidDocument(type: String, number: String): Boolean {
        return when (type) {
            "DNI" -> isValidDNI(number)
            "CE" -> isValidCE(number)
            "Pasaporte" -> number.length in 6..20
            else -> false
        }
    }

    /**
     * Formatea un número de documento con máscara
     * Ejemplo: "12345678" -> "••••5678"
     */
    fun formatDocumentMasked(documentNumber: String): String {
        if (documentNumber.length < 4) return documentNumber
        val last4 = documentNumber.takeLast(4)
        val masked = "•".repeat(documentNumber.length - 4)
        return masked + last4
    }
}

/**
 * Validador de contraseñas
 */
object PasswordValidator {

    /**
     * Valida que la contraseña cumpla con los requisitos mínimos
     */
    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    /**
     * Valida que la contraseña sea fuerte
     */
    fun isStrongPassword(password: String): Boolean {
        val hasMinLength = password.length >= 8
        val hasUpperCase = password.any { it.isUpperCase() }
        val hasLowerCase = password.any { it.isLowerCase() }
        val hasDigit = password.any { it.isDigit() }
        val hasSpecialChar = password.any { !it.isLetterOrDigit() }

        return hasMinLength && hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar
    }

    /**
     * Obtiene el nivel de fortaleza de la contraseña (0-100)
     */
    fun getPasswordStrength(password: String): Int {
        var strength = 0

        if (password.length >= 6) strength += 20
        if (password.length >= 8) strength += 20
        if (password.any { it.isUpperCase() }) strength += 20
        if (password.any { it.isLowerCase() }) strength += 20
        if (password.any { it.isDigit() }) strength += 10
        if (password.any { !it.isLetterOrDigit() }) strength += 10

        return strength.coerceIn(0, 100)
    }
}

/**
 * Resultado de validación
 */
sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

/**
 * Constantes de la aplicación
 */
object Constants {
    const val MAX_CARD_NUMBER_LENGTH = 16
    const val MAX_DNI_LENGTH = 8
    const val MAX_CE_LENGTH = 12
    const val MIN_PASSWORD_LENGTH = 6
    const val MAX_PASSWORD_LENGTH = 20

    // Mensajes de error
    const val ERROR_INVALID_CARD = "Número de tarjeta inválido"
    const val ERROR_INVALID_DOCUMENT = "Número de documento inválido"
    const val ERROR_INVALID_PASSWORD = "Contraseña inválida"
    const val ERROR_EMPTY_FIELD = "Este campo es obligatorio"
    const val ERROR_MIN_PASSWORD_LENGTH = "La contraseña debe tener al menos 6 caracteres"
}

