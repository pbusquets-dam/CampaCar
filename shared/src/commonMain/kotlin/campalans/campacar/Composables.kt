package campalans.campacar

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.material3.*
import androidx.compose.foundation.text.input.rememberTextFieldState

@Composable
fun CampText(nomCamp: String, textInicial: String = ""){
    val state = rememberTextFieldState(textInicial)
    TextField(state = state, label = { Text(nomCamp) })
}


