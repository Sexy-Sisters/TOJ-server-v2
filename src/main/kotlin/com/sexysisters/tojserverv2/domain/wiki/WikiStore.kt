package com.sexysisters.tojserverv2.domain.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki

interface WikiStore {
    fun store(wiki: Wiki): Wiki
}