package com.jetauth.utils.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)


val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavItem(
        label = "Category",
        icon = Icons.Default.Category,
        route = Screens.CategoryScreen.name
    ),
    NavItem(
        label = "Cart",
        icon = Icons.Default.ShoppingCart,
        route = Screens.CartScreen.name
    ),
    NavItem(
        label = "Profile",
        icon = Icons.Default.Person,
        route = Screens.ProfileScreen.name
    ),
)