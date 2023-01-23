package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import java.util.*

interface WikiReader {
    fun getWiki(id: UUID): Wiki
}