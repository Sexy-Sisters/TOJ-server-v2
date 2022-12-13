package com.sexysisters.tojserverv2.domain.wiki.service

import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.wiki.Wiki
import com.sexysisters.tojserverv2.domain.wiki.WikiInfo
import com.sexysisters.tojserverv2.domain.wiki.WikiMapper
import com.sexysisters.tojserverv2.domain.wiki.WikiStore
import com.sexysisters.tojserverv2.domain.wiki.makeRelation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WikiServiceImpl(
    private val wikiStore: WikiStore,
    private val schoolReader: SchoolReader,
    private val wikiMapper: WikiMapper,
) : WikiService {

    @Transactional
    override fun createWiki(schoolCode: String) {
        val school = schoolReader.getSchool(schoolCode)
        val initWiki = Wiki(name = school.name)
        initWiki.makeRelation(school)
        wikiStore.store(initWiki)
    }

    @Transactional(readOnly = true)
    override fun getSchoolWiki(scholCode: String): WikiInfo.Main {
        val school = schoolReader.getSchool(scholCode)
        return wikiMapper.of(school.wiki!!)
    }
}