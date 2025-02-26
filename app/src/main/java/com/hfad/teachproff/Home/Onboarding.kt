package com.hfad.teachproff.Home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.R
import com.hfad.teachproff.ui.theme.TeachProffTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class Slide(
    val image: Int,
    val text1: String,
    val text2: String,
    val text3: String,
    val textButton: String,
) {}

@Composable
fun Onboarding(navController: NavController) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(72, 178, 231, 255),
            Color(47, 141, 189, 255),
            Color(22, 105, 147, 255),
            Color(8, 75, 108, 255),
        )
    )

    val slider = listOf(
        Slide(
            R.drawable.cross1,
            "Добро",
            "пожаловать",
            "",
            "Начать"
        ),
        Slide(
            R.drawable.cross2,
            "Начнем",
            "путешествие",
            "Умная, великолепная и модная \n коллекция Изучите сейчас",
            "Далее"
        ),
        Slide(
            R.drawable.cross3,
            "У вас есть сила,",
            "Чтобы",
            "В вашей комнате много красивых и \n привлекательных растений",
            "Далее"
        ),
    )
    val pagerState = rememberPagerState(pageCount = { slider.size })
    val corutine = rememberCoroutineScope()

  Surface (modifier = Modifier.fillMaxSize()){
      val visible = remember {
          List(slider.size){ mutableStateOf(false) }
      }

      LaunchedEffect (pagerState.currentPage){
          visible.forEach{it.value = false}
          delay(100)
          visible[pagerState.currentPage].value = true
      }

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
                .padding(top = 70.dp)
        ){
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f),
                userScrollEnabled = false
            ){page ->
                val sli = slider[page]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    AnimatedVisibility(
                        visible = visible[page].value,
                        enter = fadeIn(tween(700)),
                        exit = fadeOut(tween(700))
                    ){
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                        ) {

                            if (page == 0) {
                                SlideText(sli)
                                SlideImage(sli)
                            } else {
                                SlideImage(sli)
                                SlideText(sli)
                            }

                        }

                    }

                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                repeat(slider.size){iteration ->
                    val color = if (pagerState.currentPage == iteration)
                        Color.White
                    else
                        Color.White.copy(alpha = 0.3f)
                    val width = if (pagerState.currentPage == iteration) 49.dp else 29.dp

                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .background(color, shape = RoundedCornerShape(12.dp))
                            .size(width =width, height = 6.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(bottom = 62.dp)
                    .size(width = 300.dp, height = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,

                ),
                onClick = {
                    if (pagerState.currentPage< slider.size - 1){
                        corutine.launch {
                            pagerState.scrollToPage(pagerState.currentPage + 1)
                        }
                    }else{
                        navController.navigate(Screen.LogIN.route)
                    }
                }
            ) {
                Text(
                    slider[pagerState.currentPage].textButton,
                    color = Color.Black
                )
            }

        }

    }
}

@Composable
fun SlideText(slide: Slide) {
    Text(
        text = slide.text1,
        fontSize = 32.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 48.dp, bottom = 5.dp)
    )
    Text(
        text = slide.text2,
        fontSize = 32.sp,
        color = Color.White,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(top = 3.dp)
    )
    Text(
        text = slide.text3,
        fontSize = 18.sp,
        color = Color(android.graphics.Color.parseColor("#E9E8E8")),
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(8.dp)
    )

}

@Composable
fun SlideImage(slide: Slide) {
    Image(
        painter = painterResource(id = slide.image),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(top = 16.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewOnboarding() {
    TeachProffTheme {
        Onboarding(rememberNavController())
    }
}

