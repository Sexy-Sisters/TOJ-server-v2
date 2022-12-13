package com.sexysisters.tojserverv2.domain.wiki

interface WikiStore {
    fun store(wiki: Wiki): Wiki
}