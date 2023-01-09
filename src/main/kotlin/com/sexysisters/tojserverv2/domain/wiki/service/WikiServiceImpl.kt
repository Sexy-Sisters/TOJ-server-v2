package com.sexysisters.tojserverv2.domain.wiki.service

import com.sexysisters.tojserverv2.common.util.date.FormatUtil
import com.sexysisters.tojserverv2.domain.school.SchoolReader
import com.sexysisters.tojserverv2.domain.student.StudentReader
import com.sexysisters.tojserverv2.domain.wiki.*
import com.sexysisters.tojserverv2.domain.wiki.domain.HTML
import com.sexysisters.tojserverv2.domain.wiki.domain.Markdown
import com.sexysisters.tojserverv2.domain.wiki.domain.Name
import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import com.sexysisters.tojserverv2.domain.wiki.policy.WikiPolicy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class WikiServiceImpl(
    private val wikiStore: WikiStore,
    private val wikiReader: WikiReader,
    private val wikiMapper: WikiMapper,
    private val wikiPolicy: List<WikiPolicy>,
    private val studentReader: StudentReader,
    private val schoolReader: SchoolReader,
) : WikiService {

    override fun createWiki(schoolCode: String) {
        val school = schoolReader.getSchool(schoolCode)
        val initWiki = Wiki(Name(school.nameValue()))
        initWiki.makeRelation(school)
        wikiStore.store(initWiki)
    }

    override fun getSchoolWiki(schoolCode: String): WikiInfo.Main {
        val school = schoolReader.getSchool(schoolCode)
        val wiki = school.wiki!!
        wiki.countViews()
        val updatedAt = FormatUtil.format(wiki.updatedAt)
        return wikiMapper.of(wiki, updatedAt, school.wallpaper)
    }

    override fun updateWiki(command: WikiCommand.Update) {
        val wiki = wikiReader.getWiki(command.id)
        val student = studentReader.getCurrentStudent()
        wikiPolicy.forEach { it.check(student, wiki.school!!) }
        wiki.update(
            html = HTML(command.html),
            markdown = Markdown(command.markdown)
        )
    }
}