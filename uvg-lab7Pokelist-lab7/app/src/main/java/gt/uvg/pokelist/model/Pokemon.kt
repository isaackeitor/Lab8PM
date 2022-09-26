package gt.uvg.pokelist.model

import com.squareup.moshi.Json

data class Pokemon(
    @Json(name = "url")
    val url: String,
    @Json(name = "name")
    val name: String,
    //val id: Int = url[url.length - 2].digitToInt()
) {
    private fun removefirstNchars(s: String?, n: Int): String? {
        return if (s == null || s.length < n) {
            s
        } else s.substring(n)
    }
    private val url2: String = removefirstNchars(url,34)!!

    private fun removeLastNchars(str: String, n: Int): String {
        return str.substring(0, str.length - n)
    }

    val id: Int = Integer.valueOf(removeLastNchars(url2, 1))

    val imageUrlFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    val imageUrlBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/$id.png"
    val imageUrlShinnyFront: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/$id.png"
    val imageUrlShinnyBack: String get() = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/$id.png"
}

data class PokemonResponse(@Json(name="results") val result : List<Pokemon>)