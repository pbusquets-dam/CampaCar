package campalans.campacar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import campalans.campacar.ApiClient.comprovarLogin
import kotlinx.coroutines.launch

@Composable
fun Login(){
    var contrasenya by remember { mutableStateOf("") }
    var usuari by remember { mutableStateOf("") }
    var contrasenyaVisible by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    var login = remember { mutableStateOf(false) }
    var register = remember { mutableStateOf(false) }
        if (login.value) {
            Main()
        }else if(register.value){
            Register()
        } else {
            Scaffold {
                Box(modifier = Modifier.fillMaxWidth().fillMaxHeight()) {
                    Column(modifier = Modifier.align(Alignment.Center)) {
                        Text(
                            "Login",
                            style = MaterialTheme.typography.displayLarge,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        TextField(
                            value = usuari,
                            onValueChange = { usuari = it },
                            label = { Text("Usuari") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Text
                            ),
                            trailingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Person,
                                    contentDescription = "Icona d'usuari"
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        TextField(
                            value = contrasenya,
                            onValueChange = { contrasenya = it },
                            label = { Text("Contrasenya") },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            ),
                            visualTransformation = if (contrasenyaVisible) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            },
                            trailingIcon = {
                                val image = if (contrasenyaVisible)
                                    Icons.Filled.Visibility
                                else
                                    Icons.Filled.VisibilityOff

                                val descripcio = if (contrasenyaVisible) "Ocultar" else "Mostrar"

                                IconButton(onClick = { contrasenyaVisible = !contrasenyaVisible }) {
                                    Icon(imageVector = image, contentDescription = descripcio)
                                }
                            }
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Button(
                            onClick = {
                                register.value = true;
                            },

                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Registrar")
                        }

                        Button(
                            onClick = {
                                scope.launch {
                                    val valid = comprovarLogin(usuari, contrasenya)
                                    login.value = valid
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text("Iniciar sessió")
                        }
                    }
                }
            }
        }
    }
