package ru.avystavkin.githubgists.models.database

data class GistFileInfo (
    var fileName: String?,
    var content: String?
){
    constructor() : this("","")
}
