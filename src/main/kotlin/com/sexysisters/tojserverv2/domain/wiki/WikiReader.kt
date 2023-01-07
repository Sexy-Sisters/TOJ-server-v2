package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki

interface WikiReader {
    fun getWiki(id: String): Wiki
}