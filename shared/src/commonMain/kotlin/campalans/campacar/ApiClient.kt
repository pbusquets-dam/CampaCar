
package campalans.campacar

import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class User(val mail: String, val passwd: String)

@Serializable
data class RegisterRequest(
    val nom: String,
    val cognom1: String,
    val cognom2: String?,
    val horari: String,
    val rol: String,
    val cotxe: Boolean,
    val ubicacio: String,
    val mail: String,
    val telf: String?,
    val passwd: String
)

object ApiClient {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                isLenient = true
            })
        }
    }

    suspend fun comprovarLogin(mail: String, passwd: String): Boolean {

        val usuari = User(mail, passwd)

        return try {
            val response: HttpResponse =
                client.post("https://campacar-api.onrender.com/api/user/login") {
                    contentType(ContentType.Application.Json)
                    setBody(usuari)
                }

            response.status.value == 200

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    suspend fun registrarUsuari(
        nom: String,
        cognom1: String,
        cognom2: String,
        horari: String,
        rol: String,
        cotxe: Boolean,
        ubicacio: String,
        mail: String,
        telf: String,
        passwd: String
    ): Boolean {

        val usuari = RegisterRequest(
            nom,
            cognom1,
            cognom2.ifBlank { null },
            horari,
            rol,
            cotxe,
            ubicacio,
            mail,
            telf.ifBlank { null },
            passwd
        )

        return try {

            val response: HttpResponse =
                client.post("https://campacar-api.onrender.com/api/user/register") {
                    contentType(ContentType.Application.Json)
                    setBody(usuari)
                }

            response.status.value in 200..299

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}
