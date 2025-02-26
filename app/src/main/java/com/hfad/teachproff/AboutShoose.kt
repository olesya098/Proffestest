package com.hfad.teachproff

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.repos.AboutShooseObject
import com.hfad.teachproff.ui.theme.bac
import com.hfad.teachproff.ui.theme.blue
import com.hfad.teachproff.ui.theme.gray
import com.hfad.teachproff.ui.theme.label
import com.hfad.teachproff.ui.theme.red
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutShoose(
    navController: NavController,
    collapsedMaxLine: Int = 3
) {
    var isExpanded by remember { mutableStateOf(false) }
    var isSelected by remember { mutableStateOf(false) }

    val pagerState = rememberPagerState(pageCount = { AboutShooseObject.about.size })
    val corutine = rememberCoroutineScope()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bac
                ),
                actions = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Color.White,
                                    shape = CircleShape
                                )
                                .size(40.dp),
                            contentAlignment = Alignment.Center


                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowLeft,
                                contentDescription = null,
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(
                            text = "Sneaker Shop",
                            fontSize = 15.sp,
                            color = Color.Black,
                            modifier = Modifier.padding(top = 10.dp)

                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = CircleShape
                                )
                                .size(40.dp),
                            contentAlignment = Alignment.Center

                        ) {
                            BadgedBox(
                                badge = {
                                    Badge(
                                        contentColor = Color.White, // Цвет текста внутри значка (если текст есть)
                                        containerColor = red, // Цвет фона значка (синий)
                                        modifier = Modifier
                                            .size(8.dp)
                                            .offset(y = (-7).dp)
                                    ) {
                                        Text(text = "") // Текст внутри значка (оставляем пустым, если не нужен)
                                    }
                                }
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bagntif), // Иконка
                                    contentDescription = "", // Описание для доступности
                                    tint = Color.Black, // Цвет иконки
                                    modifier = Modifier.size(20.dp) // Размер иконки
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
                .background(bac)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            LazyColumn(
                modifier = Modifier
                    .padding(paddingValues)
                    .background(bac)
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Nike Air Max 270 \nEssential",
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 30.sp,
                        color = Color.Black,

                        )
                    Text(
                        text = "Men’s Shoes",
                        fontSize = 20.sp,
                        color = label
                    )
                    Text(
                        text = "₽179.39",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color.Black,
                        modifier = Modifier.padding(top = 10.dp)
                    )
                }
                item {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center

                    ) {
                        HorizontalPager(
                            state = pagerState,

                            ) { page ->
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    painter = painterResource(id = AboutShooseObject.about[page].image),
                                    contentDescription = null,
                                    modifier = Modifier.size(250.dp)
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.poloska),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .size(300.dp)
                                        .padding(top = 120.dp)
                                )

                            }


                        }
                    }

                }
                item {
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 12.dp, bottom = 12.dp)
                    ) {
                        repeat(AboutShooseObject.about.size) { index ->
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(6.dp)
                                    .background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(12.dp)
                                    )
                                    .then(
                                        if (pagerState.currentPage == index) {
                                            Modifier.border(
                                                width = 2.dp,
                                                shape = RoundedCornerShape(12.dp),
                                                color = blue
                                            )
                                        } else {
                                            Modifier
                                        }
                                    )
                                    .clickable {
                                        corutine.launch {
                                            pagerState.animateScrollToPage(index)
                                        }
                                    }

                            ) {
                                Image(
                                    painter = painterResource(id = AboutShooseObject.about[index].image),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(40.dp)
                                        .align(Alignment.Center)
                                )
                            }

                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier.padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = "Вставка Max Air 270 обеспечивает непревзойденный комфорт в течение всего дня. Изящный дизайн позволяет одеть эти кроссовки но любое событие. Это универсальный вариант.",
                            maxLines = if (isExpanded) Int.MAX_VALUE else collapsedMaxLine,
                            lineHeight = 25.sp,
                            overflow = TextOverflow.Ellipsis,
                            color = label
                        )
                        Text(
                            text = if (isExpanded) "Скрыть" else "Показать",
                            color = blue,
                            modifier = Modifier
                                .clickable {
                                    isExpanded = !isExpanded
                                }
                                .align(Alignment.End)


                        )

                    }
                }

                item {

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom,
                        horizontalArrangement = Arrangement.SpaceAround,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .background(
                                    color = gray,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id =if (isSelected) R.drawable.path2 else R.drawable.path),
                                contentDescription = null,
                                modifier = Modifier.size(23.dp).clickable {
                                    isSelected = !isSelected
                                }
                            )
                        }
                        Button(
                            onClick = {
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = blue
                            ),
                            modifier = Modifier
                                .background(
                                    color = blue,
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .height(60.dp)
                                .fillMaxWidth(0.9f)

                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceAround
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.bag22),
                                    tint = Color.White,
                                    contentDescription = null,
                                    modifier = Modifier.size(20.dp)

                                )
                                Spacer(modifier = Modifier.height(20.dp))


                                Text(
                                    text = "В Корзину",
                                    color = Color.White,

                                    )
                                Spacer(modifier = Modifier.height(20.dp))
                                Text(
                                    text = "",
                                    color = Color.White,

                                    )
                            }

                        }
                    }

                }
            }

        }

    }


}


@Preview(showBackground = true)
@Composable
fun PreviewAboutShoose() {
    AboutShoose(rememberNavController())
}