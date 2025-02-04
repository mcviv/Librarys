package com.example.library_app.ui.screens.account

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.library_app.data.AuthViewModel
import com.example.library_app.navigations.ROUTE_FORGOT_PASSWORD
import com.example.library_app.navigations.ROUTE_HOME
import com.example.library_app.navigations.ROUTE_SIGNUP

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignIn(
    navController: NavHostController
) {
    Scaffold(
        topBar = {
            LargeTopAppBar(
                title = {
                    Text(text = """
                    
                    LOGIN
                """.trimIndent(),
                        color = Color.Blue,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Serif,
                        fontStyle = FontStyle.Italic,
                        lineHeight = 64.sp,
                        fontSize = 40.sp,
                        textAlign = TextAlign.Center

                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back to Previous Page"
                        )
                    }
                }
            )
        }
    ) {
            innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                val context = LocalContext.current
                var account = AuthViewModel(navController, context)
                var email by remember {
                    mutableStateOf("")
                }
                var password by rememberSaveable {
                    mutableStateOf("")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    OutlinedTextField(value = email, onValueChange = {
                        email = it
                    },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Email
                        ),
                        placeholder = {
                            Text(text = "Email")
                        },
                        label = {
                            Text(text = "Email")
                        },
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {

                    var passwordVisibility by remember { mutableStateOf(false) }

                    val icon = if (passwordVisibility) {
                        Icons.Filled.ArrowBack
                    } else {
                        Icons.Filled.ArrowBack
                    }

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        placeholder = { Text(text = "Password") },
                        label = { Text(text = "Password") },
                        trailingIcon = {
                            IconButton(onClick = {
                                passwordVisibility = !passwordVisibility
                            }) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Visibility Icon"
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = if (passwordVisibility) VisualTransformation.None
                        else PasswordVisualTransformation(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),

                ) {
                    Button(
                        onClick = {
                            account.signIn(
                                email.trim(),
                                password.trim()
                            )
                            navController.navigate(ROUTE_HOME)
                        },
                        modifier = Modifier
                            .padding(10.dp)) {
                        Text(text = "LOGIN",
                        color = Color.Blue)
                    }

                    TextButton(
                        onClick = {
                            navController.navigate(ROUTE_FORGOT_PASSWORD)
                        }
                    ) {
                        Text(text = "Forgot Password?",
                            color = Color.Red)
                    }

                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Don't have an account?")
                    ElevatedButton(onClick = { navController.navigate(ROUTE_SIGNUP) },
                        modifier = Modifier
                            .padding(10.dp)) {
                        Text(text = "SIGN IN")
                    }
                }
            }
        }
    }
}