package com.sexysisters.tojserverv2.infrastructure.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import com.sexysisters.tojserverv2.domain.wiki.WikiStore
import org.springframework.stereotype.Component

@Component
class WikiStoreImpl(
    private val wikiRepository: WikiRepository,
) : WikiStore {
    override fun store(wiki: Wiki) = wikiRepository.save(wiki)
}