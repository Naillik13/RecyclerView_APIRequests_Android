package iimdemo.killiangalea.com.iimdemo


// Template Objet Repo
data class FullRepo (
    val name: String,
    val created_at: String,
    val homepage: String,
    val language:String,
    val watchers: Int,
    val owner: Owner
)
data class Owner (
    val login: String,
    val avatar_url: String

)