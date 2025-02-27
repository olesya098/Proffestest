package com.hfad.teachproff

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Home.Cardd
import com.hfad.teachproff.Home.Categories
import com.hfad.teachproff.repos.CardsRepository.cards
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.repos.CategoryRepository
import com.hfad.teachproff.ui.theme.bac

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoriiShoos(navController: NavController) {
val category = navController
    .currentBackStackEntryAsState()
    .value
    ?.arguments
    ?.getString("category")
    ?:"Все"
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    bac
                ),
                actions = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .clickable { navController.navigate(Screen.Home.route) }
                                .background(
                                    Color.White,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center

                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }

                        Text(
                            text = category,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp)
                        )


                        Box(
                            modifier = Modifier
                                .size(40.dp),
                            contentAlignment = Alignment.Center

                        ) {
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .background(bac)
        ) {
            Column {
                Text(
                    text = "Категории",
                    color = Color.Black,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp
                        )
                )
                LazyRow {
                    items(CategoryRepository.category) { cat ->  // Используем список категорий
                        Categories(c = cat, navController)
                    }
                }

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2)
                ) {
                    items(cards) { c ->
                        Cardd(ca = c, navController, customFontFamily = FontFamily())
                    }
                }
            }


        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewCategoriiShoos(){
    CategoriiShoos(rememberNavController())
}