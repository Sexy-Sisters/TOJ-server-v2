package com.sexysisters.tojserverv2.domain.wiki

interface WikiReader {
    fun getWiki(id: Long): Wiki
}