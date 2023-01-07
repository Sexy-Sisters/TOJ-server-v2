package com.sexysisters.tojserverv2.infrastructure.wiki

import com.sexysisters.tojserverv2.domain.wiki.WikiReader
import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import com.sexysisters.tojserverv2.domain.wiki.exception.WikiException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class WikiReaderImpl(
    private val wikiRepository: WikiRepository,
) : WikiReader {

    override fun getWiki(id: String): Wiki {
        return wikiRepository.findByIdOrNull(id)
            ?: throw WikiException.WikiNotFound()
    }
}