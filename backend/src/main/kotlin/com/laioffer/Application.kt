package com.laioffer

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

@Serializable
data class Playlist (
    val id: Int,
    val songs: List<Song>
)

@Serializable
data class Song (
    val name: String,
    val lyric: String,
    val src: String,
    val length: String
)

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
        })
    }
    // TODO: adding the routing configuration here
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        //http://0.0.0.0:8080/feed
        get("feed") {
            val jsonString = this::class.java.classLoader.getResource("feed.json")?.readText()
            call.respondText(jsonString ?: "null")
        }

        //http://0.0.0.0:8080/playlists
        get("playlists") {
            val jsonString = this::class.java.classLoader.getResource("playlists.json")?.readText()
            call.respondText(jsonString ?: "null")
        }

        //http://0.0.0.0:8080/playlist/2
        get("playlist/{id}") {
//            val jsonString: String? = this::class.java.classLoader.getResource("playlists.json")?.readText()
//            // jsonString -> List -> find id -> Item
//            // String -> Object: Deserialize / decode
//            // object -> String: Serialize
//            val playlists: List<Playlist> = Json.decodeFromString(ListSerializer(Playlist.serializer()), jsonString)
//
//            val id = call.parameters["id"]
//            val playlist: Playlist? = playlists.firstOrNull { item ->
//                item.id.toString() == id
//            }
//
//            call.respondNullable(playlist)

            this::class.java.classLoader.getResource("playlists.json")?.readText()?.let { jsonString ->
                val playlists: List<Playlist> = Json.decodeFromString(ListSerializer(Playlist.serializer()), jsonString)
                val id = call.parameters["id"]
                val playlist: Playlist? = playlists.firstOrNull { item ->
                    item.id.toString() == id
                }
                call.respondNullable(playlist)
            } ?: call.respondText("null")
        }

        //http://0.0.0.0:8080/songs/solo.mp3
        static("/") {
            staticBasePackage = "static"
            static("songs") {
                resources("songs")
            }
        }

    }

    myRouting {
        myGet("/") {

        }
    }
}

fun myRouting(block: () -> Unit) {

}

fun myGet(path: String, block: () -> Unit) {

}