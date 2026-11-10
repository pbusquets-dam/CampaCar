package campalans.campacar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Register() {

    var nom by remember { mutableStateOf("") }
    var cognom1 by remember { mutableStateOf("") }
    var cognom2 by remember { mutableStateOf("") }
    var horari by remember { mutableStateOf("M") }
    var rol by remember { mutableStateOf("S") }
    var cotxe by remember { mutableStateOf(false) }
    var ubicacio by remember { mutableStateOf("") }
    var mail by remember { mutableStateOf("") }
    var telf by remember { mutableStateOf("") }
    var passwd by remember { mutableStateOf("") }

    var passwdVisible by remember { mutableStateOf(false) }

    var missatge by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                "Registre",
                style = MaterialTheme.typography.displayLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            TextField(
                value = nom,
                onValueChange = { nom = it },
                label = { Text("Nom") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = cognom1,
                onValueChange = { cognom1 = it },
                label = { Text("Primer cognom") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = cognom2,
                onValueChange = { cognom2 = it },
                label = { Text("Segon cognom") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Horari
            Text("Horari")

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = horari == "M",
                    onClick = { horari = "M" }
                )
                Text("Matí")

                RadioButton(
                    selected = horari == "T",
                    onClick = { horari = "T" }
                )
                Text("Tarda")

                RadioButton(
                    selected = horari == "D",
                    onClick = { horari = "D" }
                )
                Text("Dia sencer")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Rol

            Text("Rol")

            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = rol == "S",
                        onClick = { rol = "S" }
                    )
                    Text("Estudiant")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = rol == "A",
                        onClick = { rol = "A" }
                    )
                    Text("Administrador")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = rol == "P",
                        onClick = { rol = "P" }
                    )
                    Text("Professor")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Cotxe
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = cotxe,
                    onCheckedChange = { cotxe = it }
                )
                Text("Tinc cotxe")
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = ubicacio,
                onValueChange = { ubicacio = it },
                label = { Text("Ubicació") }
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = mail,
                onValueChange = { mail = it },
                label = { Text("Correu electrònic") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = telf,
                onValueChange = { telf = it },
                label = { Text("Telèfon") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = passwd,
                onValueChange = { passwd = it },
                label = { Text("Contrasenya") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (passwdVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    val image = if (passwdVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    IconButton(
                        onClick = {
                            passwdVisible = !passwdVisible
                        }
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = "Mostrar contrasenya"
                        )
                    }
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {

                    scope.launch {

                        ApiClient.registrarUsuari(nom, cognom1, cognom2, horari, rol, cotxe, ubicacio, mail, telf, passwd)

                    }

                }
            ) {
                Text("Registrar-se")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(missatge)
        }
    }
}
