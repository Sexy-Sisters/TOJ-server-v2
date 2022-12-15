package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.AdCommand

interface AdService {
    fun createAd(command: AdCommand.Create)
}