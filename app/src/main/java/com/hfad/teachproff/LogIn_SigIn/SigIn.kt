package com.hfad.teachproff.LogIn_SigIn

import android.provider.ContactsContract.CommonDataKinds.Email
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.hfad.teachproff.Navigatia.Screen
import com.hfad.teachproff.R
import com.hfad.teachproff.ui.theme.bac
import com.hfad.teachproff.ui.theme.label

@Composable
fun SigIn(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp, start = 20.dp)

        ) {
            Box(
                modifier = Modifier
                    .background(
                        bac,
                        shape = CircleShape
                    )
                    .size(40.dp)
                    .padding(),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null
                )
            }
            Text(
                text = "Регистрация",
                fontSize = 32.sp,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(8.dp)
            )

            Text(
                text = "Заполните Свои данные или \n продолжите через социальные медиа",
                textAlign = TextAlign.Center,
                color = label

            )
            fun isEmail(email: String): Boolean {
                val pattern = """^[a-z0-9]+@gmail\.com${'$'}""".toRegex()
                return pattern.matches(email)

            }

            fun isPassword(password: String): Boolean {
                val patternpass =
                    """^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$&^%*!])(?=\\S+$).{8,}$""".toRegex()
                return patternpass.matches(password)
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Ваше имя"

            )
            TextField(
                value = name,
                onValueChange = {
                    name = it
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.96f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = bac,
                    unfocusedContainerColor = bac,
                    focusedTextColor = Color.Black,
                    unfocusedLabelColor = label,
                    focusedLabelColor = label,
                    unfocusedTextColor = label
                ),
                label = {
                    Text(
                        text = "XXXXXXX"
                    )
                }

            )
            Text(
                text = "Email"

            )
            TextField(
                value = email,
                onValueChange = {
                    email = it
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.96f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = bac,
                    unfocusedContainerColor = bac,
                    focusedTextColor = Color.Black,
                    unfocusedLabelColor = label,
                    focusedLabelColor = label,
                    unfocusedTextColor = label
                ),
                label = {
                    Text(
                        text = "xyz@gmail.com"
                    )
                }

            )
            Text(
                text = "Пароль"

            )
            TextField(
                value = password,
                onValueChange = {
                    password = it
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(0.96f),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = bac,
                    unfocusedContainerColor = bac,
                    focusedTextColor = Color.Black,
                    unfocusedLabelColor = label,
                    focusedLabelColor = label,
                    unfocusedTextColor = label
                ),
                label = {
                    Text(
                        text = "********"
                    )
                },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(
                        onClick = { passwordVisible = !passwordVisible }
                    ) {
                        Image(
                            painter = painterResource(id = if (passwordVisible) R.drawable.eyepocaz else R.drawable.eye),
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

            )
            Button(
                onClick = {
                    if (!isEmail(email)) {
                        errorMessage = "Некорректный формат Email"
                        showError = true
                    } else if (!isPassword(password)) {
                        errorMessage = "Пароль должен содержать минимум 8 символов, одну цифру, одну заглавную букву и один специальный символ."
                        showError = true
                    }else{
                        navController.navigate(Screen.Home.route)
                    }
                }
            ) {
                Text(
                    text = "Войти"
                )
            }
            if (showError){
                AlertDialog(
                    onDismissRequest = {showError = false},
                    title = { Text("Ошибка")},
                    text = { Text(errorMessage) },
                    confirmButton = {
                        TextButton(
                            onClick = {showError = false}
                        ) {
                            Text(
                                "Ok"
                            )
                        }
                    }
                )
            }


        }


    }

}


@Preview(showBackground = true)
@Composable
fun PreviewSigIn() {
    SigIn(rememberNavController())
}