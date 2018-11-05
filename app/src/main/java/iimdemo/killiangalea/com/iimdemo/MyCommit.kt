package iimdemo.killiangalea.com.iimdemo


// Template Objet Commit
data class MyCommit (
    val commit: Commit
)
data class Commit (
    val author: Author,
    val message: String
)
data class Author (
    val name: String
)