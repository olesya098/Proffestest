package com.hfad.teachproff

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Home.Cardd
import com.hfad.teachproff.Home.Navigate
import com.hfad.teachproff.repos.CardsRepository.cards
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.ui.theme.bac

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Favorit (navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bac
                ),
                actions = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween

                    ) {
                        Box(
                            modifier = Modifier
                                .padding(start = 10.dp)
                                .background(color = Color.White, shape = CircleShape)
                                .size(40.dp)
                                .clickable { navController.navigate(Screen.Home.route) },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        Text(
                            text = "Избранное",
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .background(color = Color.White, shape = CircleShape)
                                .size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.path2),
                                contentDescription = null,
                                modifier = Modifier.size(20.dp)

                            )
                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(bac)
                .padding(paddingValues)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2)
            ) {
                items(cards){ card ->
                    Cardd(ca = card, navController, customFontFamily = FontFamily())

                }
            }
            Navigate(navController)

        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewFavorit(){
    Favorit(rememberNavController())
}