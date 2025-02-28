package com.hfad.teachproff

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Home.Cardd
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.repos.CardsRepository.cards
import com.hfad.teachproff.ui.theme.bac
import com.hfad.teachproff.ui.theme.label

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Poisk(navController: NavController) {

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
                                .padding(15.dp)
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
                            text = "Поиск",
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(top = 25.dp)
                        )
                        Box(
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .size(40.dp),
                            contentAlignment = Alignment.Center
                        ) {

                        }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(bac)
                .padding(paddingValues)
        ) {
            var poisk by remember { mutableStateOf("") }

            val list = listOf(
                "New Shoes",
                "Nike Top Shoes",
                "Nike Air Force",
                "Shoes",
                "Snakers Nike Shoes",
                "Regular Shoes"

            )
            Box(
                modifier = Modifier.padding(18.dp)
            ) {
                TextField(
                    value = poisk,
                    onValueChange = { poisk = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 8.dp,
                            spotColor = Color.Black.copy(alpha = 0.1f)
                        ),

                    shape = RoundedCornerShape(12.dp),
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedLabelColor = label,
                        unfocusedLabelColor = label,
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = label
                    ),
                    placeholder = {
                        Text(
                            text = "Nike Air Max",
                            color = label
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = label
                        )
                    },
                    trailingIcon = {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.search2),
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                            if (poisk.isNotEmpty()) {
                                IconButton(
                                    onClick = { poisk = "" }
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        tint = label
                                    )
                                }
                            }

                        }

                    }
                )
                if (poisk.isNotEmpty()) {
                    Column(
                        modifier = Modifier.padding(top = 60.dp)
                    ) {
                        list.takeLast(3).forEach { item ->
                            ListItem(
                                colors = ListItemDefaults.colors(
                                    containerColor = bac
                                ),
                                modifier = Modifier
                                    .clickable {
                                        poisk = item
                                    },
                                headlineContent = {
                                    Text(
                                        text = item,
                                        color = label
                                    )
                                },
                                leadingContent = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.oclock),
                                        contentDescription = null,
                                        tint = label,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            )

                        }
                    }
                }

            }
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),

                ) {
                items(cards) { card ->
                    Cardd(ca = card, navController, customFontFamily = FontFamily())

                }
            }

        }
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewPoisk() {
    Poisk(rememberNavController())
}
