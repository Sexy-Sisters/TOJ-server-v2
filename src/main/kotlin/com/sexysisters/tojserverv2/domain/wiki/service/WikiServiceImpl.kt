package com.sexysisters.tojserverv2.domain.wiki.service

import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.wiki.*
import com.sexysisters.tojserverv2.domain.wiki.policy.WikiPolicy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class WikiServiceImpl(
    private val wikiStore: WikiStore,
    private val wikiReader: WikiReader,
    private val wikiMapper: WikiMapper,
    private val wikiPolicy: List<WikiPolicy>,
    private val studentReader: StudentReader,
    private val schoolReader: SchoolReader,
) : WikiService {

    @Transactional
    override fun createWiki(schoolCode: String) {
        val school = schoolReader.getSchool(schoolCode)
        val initWiki = Wiki(name = school.name)
        initWiki.makeRelation(school)
        wikiStore.store(initWiki)
    }

    @Transactional
    override fun getSchoolWiki(schoolCode: String): WikiInfo.Main {
        val school = schoolReader.getSchool(schoolCode)
        val wiki = wikiReader.getWiki(school.wiki!!.id)
        wiki.countViews()
        return wikiMapper.of(wiki)
    }

    @Transactional
    override fun updateWiki(command: WikiCommand.Update) {
        val wiki = wikiReader.getWiki(command.id)
        val student = studentReader.getCurrentStudent()
        wikiPolicy.forEach { it.check(student, wiki.school!!) }
        wiki.update(
            html = command.html,
            markdown = command.markdown
        )
    }
}