package com.sexysisters.tojserverv2.domain.wiki.service

import com.sexysisters.tojserverv2.domain.wiki.WikiCommand
import com.sexysisters.tojserverv2.domain.wiki.WikiInfo

interface WikiService {
    fun createWiki(schoolCode: String)
    fun getSchoolWiki(schoolCode: String): WikiInfo.Main
    fun updateWiki(command: WikiCommand.Update)
}