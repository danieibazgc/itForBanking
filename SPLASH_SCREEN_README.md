# Splash Screen - Configuración de Imagen

## ✅ Implementación Completa

Se ha implementado un **Splash Screen** profesional para tu aplicación Interbank usando las mejores prácticas de Android.

## 📁 Ubicación de la Imagen

Tu imagen del splash debe estar ubicada en:

```
app/src/main/res/drawable/
```

**Actualmente se está usando:** `ibk.png` (que ya existe en tu proyecto)

Si deseas usar una **imagen diferente** para el splash:

### Opción 1: Reemplazar la imagen existente
1. Coloca tu nueva imagen en: `app/src/main/res/drawable/`
2. Nómbrala como prefieras (ej: `splash_logo.png`, `logo_interbank.png`, etc.)
3. Actualiza el archivo `SplashScreen.kt` línea 42:
   ```kotlin
   painter = painterResource(id = R.drawable.TU_NOMBRE_DE_IMAGEN)
   ```

### Opción 2: Múltiples resoluciones (Recomendado)
Para mejor calidad en diferentes dispositivos, coloca versiones de tu imagen en:

```
app/src/main/res/drawable-mdpi/splash_logo.png     (48x48 dp ≈ 160 dpi)
app/src/main/res/drawable-hdpi/splash_logo.png     (72x72 dp ≈ 240 dpi)
app/src/main/res/drawable-xhdpi/splash_logo.png    (96x96 dp ≈ 320 dpi)
app/src/main/res/drawable-xxhdpi/splash_logo.png   (144x144 dp ≈ 480 dpi)
app/src/main/res/drawable-xxxhdpi/splash_logo.png  (192x192 dp ≈ 640 dpi)
```

## 🎨 Características Implementadas

✅ **SplashScreen API moderna** (Android 12+)
✅ **Animación fade-in** suave del logo
✅ **Duración:** 2.5 segundos
✅ **Navegación automática** al Login
✅ **No retorno:** El botón atrás no regresa al splash
✅ **Fondo blanco** personalizable

## ⚙️ Personalización

### Cambiar el color de fondo:
Edita `SplashScreen.kt` línea 38:
```kotlin
.background(Color.White)  // Cambia a tu color preferido
```

### Cambiar la duración:
Edita `SplashScreen.kt` línea 32:
```kotlin
delay(2500)  // Tiempo en milisegundos (2500 = 2.5 segundos)
```

### Cambiar el tamaño del logo:
Edita `SplashScreen.kt` línea 43:
```kotlin
.size(200.dp)  // Ajusta el tamaño en dp
```

### Cambiar la velocidad de animación:
Edita `SplashScreen.kt` línea 29:
```kotlin
animationSpec = tween(durationMillis = 800)  // Duración del fade-in
```

## 📱 Flujo de Navegación

```
App Launch → Splash Screen (2.5s) → Login Screen → Home Screen
```

## 🔧 Archivos Modificados/Creados

1. ✅ `SplashScreen.kt` - Nueva pantalla de splash
2. ✅ `NavigationGraph.kt` - Actualizado con ruta splash
3. ✅ `Screen.kt` - Agregada ruta Splash
4. ✅ `MainActivity.kt` - Instalado SplashScreen API
5. ✅ `build.gradle.kts` - Dependencia añadida
6. ✅ `libs.versions.toml` - Versión de biblioteca

## 🚀 Próximos Pasos

1. **Sincroniza Gradle** en Android Studio
2. **Coloca tu imagen** en la carpeta `drawable` si aún no lo has hecho
3. **Compila y ejecuta** la app
4. Disfruta del efecto splash profesional 🎉

## 📝 Notas Importantes

- El splash screen solo se muestra **al inicio de la app**
- **No se puede volver** al splash con el botón atrás
- La imagen actual es `ibk.png` - puedes cambiarla por la que prefieras
- La animación es suave y profesional
- Compatible con Android 12+ usando la nueva API oficial

---

¿Necesitas ayuda adicional? Solo pregunta 😊

