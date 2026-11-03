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
}
