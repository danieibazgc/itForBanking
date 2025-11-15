package com.danieibazgc.interbank.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.danieibazgc.interbank.ui.theme.InterbankBlue
import com.danieibazgc.interbank.ui.theme.InterbankGray
import com.danieibazgc.interbank.ui.theme.InterbankGreen
import com.danieibazgc.interbank.ui.theme.InterbankTextGray

@Composable
fun HomeScreen(
    onLogout: () -> Unit = {}
) {
    var selectedTab by remember { mutableStateOf(0) }
    var saldosOcultos by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            HomeTopBar(
                saldosOcultos = saldosOcultos,
                onToggleSaldos = { saldosOcultos = !saldosOcultos }
            )
        },
        bottomBar = {
            HomeBottomBar(
                selectedTab = selectedTab,
                onTabSelected = { selectedTab = it }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Acción QR */ },
                containerColor = InterbankGreen,
                contentColor = Color.White,
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "QR",
                    modifier = Modifier.size(32.dp)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(InterbankGray)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Header con información de T.C. referencial
            HeaderInfo(saldosOcultos = saldosOcultos)

            Spacer(modifier = Modifier.height(16.dp))

            // Sección de Productos
            ProductsSection(
                saldosOcultos = saldosOcultos,
                onToggleSaldos = { saldosOcultos = !saldosOcultos }
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Sección de Pagos Rápidos
            QuickPaymentsSection()

            Spacer(modifier = Modifier.height(24.dp))

            // Banner de pagos a tiempo
            PaymentsBanner()

            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    saldosOcultos: Boolean,
    onToggleSaldos: () -> Unit
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                // Logo
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .background(
                            color = InterbankBlue,
                            shape = RoundedCornerShape(4.dp)
                        )
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Interbank",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = InterbankGreen
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Notificaciones */ }) {
                Icon(
                    imageVector = Icons.Outlined.Notifications,
                    contentDescription = "Notificaciones",
                    tint = InterbankGreen
                )
            }
            IconButton(onClick = { /* Menú */ }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Menú",
                    tint = InterbankGreen
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun HeaderInfo(saldosOcultos: Boolean) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "T.C. referencial: Compra: S/3.930 Venta: S/3.980",
                fontSize = 12.sp,
                color = InterbankTextGray
            )
        }
    }
}

@Composable
fun ProductsSection(
    saldosOcultos: Boolean,
    onToggleSaldos: () -> Unit
) {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Productos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextButton(
                onClick = onToggleSaldos,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = InterbankGreen
                )
            ) {
                Text(
                    text = if (saldosOcultos) "Mostrar saldos" else "Ocultar saldos",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = InterbankGreen
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Cuenta Simple Soles
        ProductCard(
            title = "Cuenta Simple Soles",
            amount = if (saldosOcultos) "****" else "S/ 1,200.00",
            subtitle = "Saldo disponible",
            backgroundColor = Color(0xFFE8F5F1),
            icon = Icons.Filled.AccountBox
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tarjeta Visa Premia
        ProductCard(
            title = "Tarjeta Visa Premia",
            amount = if (saldosOcultos) "****" else "S/ 25,000.00",
            subtitle = "Línea de crédito disponible",
            backgroundColor = Color(0xFFE8F5F1),
            icon = Icons.Filled.Star
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Millas Benefit
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5F1)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(
                                color = InterbankBlue,
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Star,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Column {
                        Text(
                            text = "Millas Benefit",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                        Text(
                            text = "¡Conócelas!",
                            fontSize = 14.sp,
                            color = InterbankGreen,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    title: String,
    amount: String,
    subtitle: String,
    backgroundColor: Color,
    icon: ImageVector
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = Color(0xFFFF69B4).copy(alpha = 0.3f),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFFFF1493),
                        modifier = Modifier.size(28.dp)
                    )
                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(
                        text = title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                    Text(
                        text = amount,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = subtitle,
                        fontSize = 12.sp,
                        color = InterbankTextGray
                    )
                }
            }
        }
    }
}

@Composable
fun QuickPaymentsSection() {
    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Pagos rápidos",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            TextButton(onClick = { /* Ver todos */ }) {
                Text(
                    text = "Ver todos",
                    color = InterbankGreen,
                    fontWeight = FontWeight.Medium
                )
                Icon(
                    imageVector = Icons.Filled.ArrowForward,
                    contentDescription = null,
                    tint = InterbankGreen,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            QuickPaymentItem(
                icon = Icons.Filled.Person,
                label = "Pagar a\ncontacto"
            )
            QuickPaymentItem(
                icon = Icons.Filled.Star,
                label = "Pago de\nmi tarjeta"
            )
            QuickPaymentItem(
                icon = Icons.Filled.Build,
                label = "Pago\nde luz"
            )
        }
    }
}

@Composable
fun QuickPaymentItem(
    icon: ImageVector,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(80.dp)
    ) {
        Box(
            modifier = Modifier
                .size(64.dp)
                .background(
                    color = Color.White,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = InterbankGreen,
                modifier = Modifier.size(32.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = label,
            fontSize = 12.sp,
            color = Color.Black,
            textAlign = TextAlign.Center,
            lineHeight = 14.sp
        )
    }
}

@Composable
fun PaymentsBanner() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF4A90E2)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = "Tus pagos a tiempo",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Texto de tres líneas con\ndescripción y detalles únicos\npara cada cliente.",
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    lineHeight = 16.sp
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .background(
                        color = Color.White.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }
        }
    }
}

@Composable
fun HomeBottomBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = InterbankGreen
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Inicio"
                )
            },
            label = { Text("Inicio", fontSize = 10.sp) },
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = InterbankGreen,
                selectedTextColor = InterbankGreen,
                unselectedIconColor = InterbankTextGray,
                unselectedTextColor = InterbankTextGray,
                indicatorColor = InterbankGray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Operaciones"
                )
            },
            label = { Text("Operaciones", fontSize = 10.sp) },
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = InterbankGreen,
                selectedTextColor = InterbankGreen,
                unselectedIconColor = InterbankTextGray,
                unselectedTextColor = InterbankTextGray,
                indicatorColor = InterbankGray
            )
        )

        // Espacio para el FAB
        NavigationBarItem(
            icon = { },
            label = { },
            selected = false,
            onClick = { },
            enabled = false
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Mis Finanzas"
                )
            },
            label = { Text("Mis Finanzas", fontSize = 10.sp) },
            selected = selectedTab == 3,
            onClick = { onTabSelected(3) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = InterbankGreen,
                selectedTextColor = InterbankGreen,
                unselectedIconColor = InterbankTextGray,
                unselectedTextColor = InterbankTextGray,
                indicatorColor = InterbankGray
            )
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = Icons.Filled.MoreVert,
                    contentDescription = "Para ti"
                )
            },
            label = { Text("Para ti", fontSize = 10.sp) },
            selected = selectedTab == 4,
            onClick = { onTabSelected(4) },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = InterbankGreen,
                selectedTextColor = InterbankGreen,
                unselectedIconColor = InterbankTextGray,
                unselectedTextColor = InterbankTextGray,
                indicatorColor = InterbankGray
            )
        )
    }
}

