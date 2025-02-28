package com.hfad.teachproff.Home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.repos.CardsRepository.cards
import com.hfad.teachproff.repos.CategoryRepository.category
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.R
import com.hfad.teachproff.dataCalasses.Actii
import com.hfad.teachproff.dataCalasses.Cardss
import com.hfad.teachproff.dataCalasses.CategoryList
import com.hfad.teachproff.ui.theme.bac
import com.hfad.teachproff.ui.theme.blue
import com.hfad.teachproff.ui.theme.label
import com.hfad.teachproff.ui.theme.red

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    var poisk by remember { mutableStateOf(TextFieldValue("")) }

    val customFontFamily = FontFamily(
        Font(R.font.kursor, FontWeight.Normal), // Обычный стиль
    )

    val actia = listOf(
        Actii(
            R.drawable.aboba
        ),
        Actii(
            R.drawable.aboba
        ), Actii(
            R.drawable.aboba
        ), Actii(
            R.drawable.aboba
        ), Actii(
            R.drawable.aboba
        )
    )
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
                        Image(
                            painter = painterResource(id = R.drawable.boc),
                            contentDescription = null,
                            modifier = Modifier.size(32.dp)
                        )
                        Box(
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.highlight_05),
                                contentDescription = null,
                                modifier = Modifier
                                    .offset(x = -10.dp, y = -6.dp)
                                    .size(15.dp)
                            )
                            Text(
                                text = "Главная",
                                color = Color.Black,
                                fontSize = 28.sp


                            )
                        }
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    Color.White,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ){
                            BadgedBox(
                                badge = {
                                    Badge(
                                        contentColor = Color.White,
                                        containerColor = red,
                                        modifier = Modifier
                                            .size(8.dp)
                                            .offset(y = (-8).dp)
                                    ){
                                        Text(
                                            text = ""
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bagntif),
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(20.dp)
                                )
                            }

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
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate(Screen.Poisk.route) }
                    ) {

                        Box(
                            modifier = Modifier
                                .background(Color.White, shape = RoundedCornerShape(12.dp))
                                .size(
                                    width = 250.dp,
                                    height = 50.dp
                                )
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,

                            ) {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = null,
                                    tint = label,
                                    modifier = Modifier.padding(12.dp)
                                )
                                Text(
                                    text = "Поиск",
                                    color = label,
                                    modifier = Modifier.padding(12.dp)

                                )

                            }


                        }

                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .background(
                                    blue,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.sliders),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                            )
                        }
                    }

                }
                item {
                    Column {
                        Text(
                            text = "Категории",
                            color = Color.Black,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(10.dp)
                        )
                    }
                    LazyRow {
                        items(category) { cat ->
                            Categories(c = cat, navController)
                        }
                    }
                }
                item {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Популярное",
                                color = Color.Black,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                text = "Все",
                                color = blue,
                                fontSize = 15.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clickable { navController.navigate(Screen.Popular.route) }

                            )
                        }

                    }
                    LazyRow {
                        items(cards) { c ->
                            Cardd(ca = c, navController, customFontFamily)
                        }
                    }
                }
                item {
                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Акции",
                                color = Color.Black,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                            Text(
                                text = "Все",
                                color = blue,
                                fontSize = 15.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    LazyRow {
                        items(actia) { ac ->
                            Actis(act = ac)

                        }


                    }
                }
                item {
                    Spacer(modifier = Modifier.height(100.dp))
                }

            }

            Navigate(navController)


        }
    }
}

@Composable
fun Cardd(ca: Cardss, navController: NavController, customFontFamily: FontFamily) {
    var select by remember { mutableStateOf(false) }
    var select2 by remember { mutableStateOf(false) }
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .width(170.dp)
            .fillMaxHeight()
            .padding(8.dp)
            .clickable { navController.navigate(Screen.AboutShoose.route) }
    ) {
        Column {
            Box(
                modifier = Modifier
                    .padding(8.dp)
            ){
                Box(
                    modifier = Modifier
                        .background(
                            bac,
                            shape = CircleShape
                        )
                        .size(30.dp)
                        .padding()
                        .clickable { select = !select },
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = if (select) R.drawable.path2 else R.drawable.path),
                        contentDescription = null,
                        modifier = Modifier.size(15.dp)
                    )
                }

            }

            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = ca.image),
                    contentDescription = null,
                    modifier = Modifier.size(120.dp)
                )
            }
            Text(
                text = ca.title,
                color = blue,
                modifier = Modifier.padding(start = 5.dp)
            )
            Text(
                text = ca.text,
                color = label,
                modifier = Modifier.padding(start = 5.dp, top = 7.dp)

            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = ca.price,
                    fontFamily = customFontFamily,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(7.dp)
                        .align(Alignment.Bottom)
                )
                Box(
                    modifier = Modifier
                        .background(
                            blue,
                            shape = RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
                        )
                        .size(40.dp)
                        .padding(5.dp)
                        .clickable { select2 = !select2 },
                    contentAlignment = Alignment.Center

                ) {
                    Image(
                        painter = painterResource(id = if (select2) R.drawable.corzina else R.drawable.plus),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }


        }

    }
}


@Composable
fun Categories(c: CategoryList, navController: NavController) {
    var sel by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(8.dp)
            .background(
                Color.White,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                sel = true
                // Навигация к экрану 'CategoriiShoos' с передачей аргумента в виде названия категории 'categ.title'.
                navController.navigate(Screen.CategoriiShoos.createRoute(c.title))
            }

    ) {
        Box(
            modifier = Modifier
                .background(
                    Color.White,
                    shape = RoundedCornerShape(10.dp)
                )
                .size(width = 100.dp, height = 50.dp)

                .then(
                    if (sel) { // Убрали сравнение с true
                        Modifier.border(
                            width = 2.dp,
                            color = Color(0xFF03A9F4),
                            shape = RoundedCornerShape(12.dp)
                        )
                    } else {
                        Modifier
                    }
                )
        ) {
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    text = c.title,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
fun Actis(act: Actii) {
    Card(
        modifier = Modifier.padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = act.imag),
                contentDescription = null
            )

        }


    }
}

@Composable
fun BoxScope.Navigate(navController: NavController) {
    var route = navController.currentBackStackEntryAsState().value?.destination?.route

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .shadow(
                elevation = 8.dp,
                spotColor = Color.Black.copy(alpha = 0.15f),
                ambientColor = Color.Black.copy(alpha = 0.1f),
                shape = RoundedCornerShape(topStart = 25f, topEnd = 25f)
            )
            .align(Alignment.BottomCenter),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            painter = painterResource(id = R.drawable.nav),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            contentScale = ContentScale.FillBounds
        )
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { navController.navigate(Screen.Home.route) }
            ) {
                Image(
                    painter = painterResource(id = if (route == Screen.Home.route) R.drawable.home2 else R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton(
                onClick = { navController.navigate(Screen.Favorite.route) }
            ) {
                Image(
                    painter = painterResource(id = if (route == Screen.Favorite.route) R.drawable.hard2 else R.drawable.hard),

                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            Box(
                modifier = Modifier
                    .offset(y = (-30).dp)
                    .size(60.dp)
                    .padding(bottom = 10.dp)

                    .shadow(
                        elevation = 8.dp,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center

            ) {
                Box(
                    modifier = Modifier
                        .background(
                            blue,
                            shape = CircleShape
                        )
                        .size(50.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.bag22),
                        contentDescription = null,
                        modifier = Modifier.size(32.dp)
                    )

                }

            }
            IconButton(
                onClick = { navController.navigate(Screen.Favorite.route) }
            ) {
                Image(
                    painter = painterResource(id = if (route == Screen.Favorite.route) R.drawable.notif else R.drawable.notif2),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }
            IconButton(
                onClick = { navController.navigate(Screen.Favorite.route) }
            ) {
                Image(
                    painter = painterResource(id = if (route == Screen.Favorite.route) R.drawable.frame2 else R.drawable.frame),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp)
                )
            }


        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    Home(rememberNavController())
}