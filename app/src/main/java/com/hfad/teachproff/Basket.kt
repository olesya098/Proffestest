package com.hfad.teachproff

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.dataCalasses.Cardss
import com.hfad.teachproff.repos.CardsRepository.cards
import com.hfad.teachproff.ui.theme.bac
import com.hfad.teachproff.ui.theme.blue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Basket(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = bac
                ),
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .padding(start = 12.dp, top = 12.dp)
                            .background(
                                color = Color.White,
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
                },
                title = {
                    Box(
                        modifier = Modifier
                            .offset(x = (-15).dp)
                            .padding(top = 10.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Корзина",
                            fontSize = 18.sp,
                            color = Color.Black
                        )
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
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp)
            ) {
                Text(
                    text = "${cards.size} товара",
                    color = Color.Black,
                    modifier = Modifier.padding(12.dp)
                )

                // LazyColumn для списка товаров
                Box(
                    modifier = Modifier
                        .weight(1f) // Занимает все доступное пространство
                        .padding(bottom = 120.dp) // Отступ для BacNav
                ) {
                    LazyColumn {
                        items(cards) { item ->
                            // Состояние для хранения текущего смещения карточки по оси X
                            val offsetX = remember { mutableFloatStateOf(0f) }
                            // Анимированное значение смещения, которое плавно изменяется к targetValue
                            val animatedOffsetX by animateFloatAsState(
                                targetValue = offsetX.floatValue,
                                //это параметр, который указывает конечное значение анимации.
                                // animateFloatAsState — это функция, которая позволяет создавать анимацию для значений типа Float.
                                // Когда значение меняется (в данном случае offsetX.value),
                                // animatedOffsetX будет плавно заменено на новое значение с использованием указанного animationSpec.
                                animationSpec = spring(//animationSpec определяет настройки анимации
                                    // spring(): Это функция, которая создает спецификацию анимации на основе модели пружины.
                                    dampingRatio = Spring.DampingRatioMediumBouncy, // Средняя упругость анимации
                                    //В значении Spring.DampingRatioMediumBouncy, пружина будет «пружинистым» образом затухать,
                                    // создавая эффект легкой «упругости».
                                    stiffness = Spring.StiffnessLow // Низкая жесткость пружины
                                    //Жесткость пружины, определяет, насколько сильно пружина будет сопротивляться движению к целевому значению.
                                    // Указание Spring.StiffnessLow приводит к меньшему сопротивлению,
                                    // в результате чего анимация будет более плавной и медленной.
                                )
                            )
                            // Область корутин для запуска анимаций
                            val coroutineScope = rememberCoroutineScope()

                            // Передаем данные одного элемента в компонент SwipeableCardContent
                            SwipeableCardContent(
                                animatedOffsetX = animatedOffsetX,
                                offsetX = offsetX,
                                coroutineScope = coroutineScope,
                                item = item,
                                navController = navController
                            )

                        }
                    }
                }
            }

            // BacNav закреплен внизу
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Выравниваем по нижнему краю
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(16.dp)
            ) {
                BacNav(navController)
            }
        }
    }
}

@Composable
fun SwipeableCardContent(
    navController: NavController,
    animatedOffsetX: Float, // Анимированное значение смещения по X
    offsetX: MutableState<Float>, // Состояние для хранения текущего смещения
    coroutineScope: CoroutineScope, // Область корутин для запуска анимаций
    item: Cardss // Элемент для отображения
) {
    // Пороговые значения для свайпов
    val maxSwipe = 140f // Максимальное возможное смещение

    // Контейнер для карточки и действий свайпа
    Box(modifier = Modifier.fillMaxWidth()) {
        // Красная карточка удаления справа (появляется при свайпе влево)
        SwipeActionCard(
            modifier = Modifier.align(Alignment.CenterEnd), // Выравниваем по центру справа
            // Вычисляем прозрачность карточки в зависимости от смещения
            alpha = (-animatedOffsetX / maxSwipe).coerceIn(0f, 1f),
            //Если animatedOffsetX положительное, это может указывать на движение вправо,
            // а отрицательное — влево.
            color = Color(0xFFF87265) // Красный цвет
        ) {
            // Иконка удаления
            Image(
                painter = painterResource(id = R.drawable.rubish),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }

        // Синяя карточка действий слева (появляется при свайпе вправо)
        SwipeActionCard(
            modifier = Modifier.align(Alignment.CenterStart), // Выравниваем по центру слева
            alpha = (animatedOffsetX / maxSwipe).coerceIn(
                0f,
                1f
            ), // Прозрачность в зависимости от смещения
            color = Color(0xFF48ABE7) // Синий цвет
        ) {
            // Контент для изменения количества
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("+", color = Color.White, fontSize = 32.sp)
                Text("1", color = Color.White, fontSize = 32.sp)
                Text("-", color = Color.White, fontSize = 32.sp)
            }
        }

        // Основная карточка товара, которая свайпается
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .offset { IntOffset(animatedOffsetX.roundToInt(), 0) } // Применяем смещение по X
                .padding(12.dp)
                .pointerInput(Unit) {
                    // Обработка жестов горизонтального свайпа
                    detectHorizontalDragGestures(
                        onDragEnd = {//Этот блок выполняется, когда жест перетаскивания завершается.
                            // При завершении свайпа проверяем, превышает ли смещение порог
                            coroutineScope.launch {
                                // Если абсолютное значение смещения больше 70 пикселей:
                                offsetX.value = if (abs(offsetX.value) > 70f)
                                // Если смещение положительное (свайп вправо), устанавливаем значение в maxSwipe
                                    if (offsetX.value > 0) maxSwipe
                                    // Если смещение отрицательное (свайп влево), устанавливаем значение в -maxSwipe
                                    else -maxSwipe
                                // Если смещение не превышает порог, возвращаем значение к 0
                                else 0f
                            }
                        },
                        onHorizontalDrag = { change, dragAmount ->//Этот блок вызывается во время жеста перетаскивания.
                            // Потребляем жест (отменяем дальнейшую обработку)
                            change.consume()
                            // Обновляем текущее смещение, добавляя изменение драга (dragAmount)
                            // При этом ограничиваем значение в диапазоне от -maxSwipe до maxSwipe
                            offsetX.value =
                                (offsetX.value + dragAmount).coerceIn(-maxSwipe, maxSwipe)
                        }
                    )
                },

            shape = RoundedCornerShape(10.dp), // Закругленные углы карточки
            colors = CardDefaults.cardColors(containerColor = Color.White) // Белый цвет карточки
        ) {
            // Отображаем контент карточки
            BasketCard(item, navController)
        }
    }
}

@Composable
private fun SwipeActionCard(
    modifier: Modifier,
    alpha: Float, // Прозрачность карточки
    color: Color, // Цвет карточки
    content: @Composable () -> Unit // Контент внутри карточки
) {
    // Карточка для действия свайпа (удаление или изменение количества)
    Card(
        modifier = modifier
            .height(120.dp)
            .width(60.dp)
            .alpha(alpha), // Применяем прозрачность
        colors = CardDefaults.cardColors(containerColor = color) // Устанавливаем цвет
    ) {
        // Контейнер для контента карточки
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center // Выравниваем контент по центру
        ) {
            content()
        }
    }
}

@Composable
fun BasketCard(item: Cardss, navController: NavController) {
    Card(
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),


        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .background(
                        bac,
                        shape = RoundedCornerShape(12.dp)
                    )


            ) {
                Image(
                    painter = painterResource(id = item.image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(width = 90.dp, height = 160.dp)
                        .padding(6.dp)
                )
            }
            Column(
                modifier = Modifier
                    .padding(start = 20.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = item.text,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Text(
                    text = item.price,
                    color = Color.Black,
                    modifier = Modifier


                )
            }
        }
    }
}


@Composable
fun BacNav(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Сумма",
                color = Color.Black

            )
            Text(
                text = "₽753.95",
                color = Color.Black

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Доставка",
                color = Color.Black
            )
            Text(
                text = "₽60.00",
                color = Color.Black

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                text = "---------------------------------------------",
                color = Color.Black

            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Итого",
                color = Color.Black

            )
            Text(
                text = "₽814.15",
                color = blue

            )
        }
        Button(
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .padding(top = 12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = blue),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "Оформить заказ",
                color = Color.White
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasket() {
    Basket(rememberNavController())
}