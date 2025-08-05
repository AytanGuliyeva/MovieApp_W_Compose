package com.example.movieapp_w_compose.features.bottomNav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.example.movieapp_w_compose.features.presentation.theme.customTheme.MovieAppTheme

@Composable
fun BottomNavigationBar(
    selectedItem: BottomNavItem,
    onItemSelected: (BottomNavItem) -> Unit
) {
    NavigationBar(modifier = Modifier, containerColor = MovieAppTheme.colors.menuColor) {
        val items = listOf(
            BottomNavItem.Home,
            BottomNavItem.Search,
            BottomNavItem.Profile
        )
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = item.title,
                        tint = if (selectedItem == item) Color.White else Color.Gray
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (selectedItem == item) Color.White else Color.Gray
                    )
                },
                selected = selectedItem == item,
                onClick = { onItemSelected(item) }
            )
        }
    }
}