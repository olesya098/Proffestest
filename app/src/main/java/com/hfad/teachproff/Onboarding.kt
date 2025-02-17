package com.hfad.teachproff

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Screen.Onboarding.route
import com.hfad.teachproff.ui.theme.TeachProffTheme
import com.hfad.teachproff.ui.theme.TeachProffTheme
import kotlinx.coroutines.launch

data class Slide(
    val image: Int,
    val title1: String,
    val title2: String,
    val title3: String,
    val textButton: String
){}

@Composable
fun Onboarding(navController: NavController) {
    val gradient = Brush.linearGradient(
        colors = listOf(
            Color(23, 172, 239, 255),
            Color(22, 156, 217, 255),
            Color(15, 123, 173, 255),
            Color(13, 93, 131, 255)
        )
    )
    val slider = listOf(
        Slide(
            image = R.drawable.cross1,
            title1 = "Добро",
            title2 = "пожаловать",
            title3 = "",
            textButton = "Далее"
        ),
        Slide(
            image = R.drawable.cross2,
            title1 = "Начнем",
            title2 = "путешествие",
            title3 = "Умная, великолепная и модная коллекция Изучите сейчас",
            textButton = "Далее"
        ),
        Slide(
            image = R.drawable.cross3,
            title1 = "У вас есть сила, ",
            title2 = "чтобы",
            title3 = "В вашей комнате много красивых и привлекательных растений",
            textButton = "Начать"
        )
    )
    val pagerState = rememberPagerState (pageCount = { slider.size })
    val corut = rememberCoroutineScope()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column (
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
                .padding(top = 70.dp)
        ){
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                val slide = slider[page]

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth() // Занимаем всю ширину
                ) {
                    if (page==0){
                        Text(
                            text = slide.title1,
                            fontSize = 32.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 48.dp, bottom = 5.dp)
                        )
                        Text(
                            text = slide.title2,
                            fontSize = 32.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                        Text(
                            text = slide.title3,
                            fontSize = 18.sp,
                            color = Color(android.graphics.Color.parseColor("#E9E8E8")),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                        Image(
                            painter = painterResource(id = slide.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(top = 16.dp)
                        )
                    } else{
                        Image(
                            painter = painterResource(id = slide.image),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(300.dp)
                                .padding(top = 16.dp)
                        )
                        Text(
                            text = slide.title1,
                            fontSize = 32.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 48.dp, bottom = 5.dp)
                        )
                        Text(
                            text = slide.title2,
                            fontSize = 32.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 3.dp)
                        )
                        Text(
                            text = slide.title3,
                            fontSize = 18.sp,
                            color = Color(android.graphics.Color.parseColor("#E9E8E8")),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                repeat(slider.size){ iteration ->
                    val color = if (pagerState.currentPage == iteration)
                        Color.White
                    else
                        Color.White.copy(alpha = 0.3f)
                    val width = if (pagerState.currentPage == iteration) 49.dp else 29.dp

                     Box(
                         modifier = Modifier
                             .padding(horizontal = 4.dp)
                             .clip(CircleShape)
                             .background(color)
                             .size(width = width, height = 6.dp)
                     )
                }

            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier
                    .padding(bottom = 62.dp)
                    .width(300.dp)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(android.graphics.Color.parseColor("#FDD9B5"))
                ),
                onClick = {
                    if (pagerState.currentPage < slider.size - 1){
                        corut.launch{
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
@Preview(showBackground = true)
 @Composable
fun PreviewOnboarding(){
    TeachProffTheme{
        Onboarding(rememberNavController())
    }
}

