package ru.avystavkin.githubgists.models.local

data class GistFileInfo (
    var fileName: String?,
    var content: String?
){
    constructor() : this("","")
}
