package campalans.campacar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

@Composable
fun MainScreen() {
    var mostrarPerfil by remember { mutableStateOf(false) }

    if (mostrarPerfil) {
        PerfilUsuari()
    } else {
        Scaffold { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                Text("PANTALLA PRINCIPAL")
                Button(onClick = { mostrarPerfil = true }) {
                    Text("PERFIL USUARI")
                }
            }
        }
    }
}
