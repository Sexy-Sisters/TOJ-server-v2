package com.sexysisters.tojserverv2.domain.ad.service

import com.sexysisters.tojserverv2.domain.ad.AdCommand

interface AdService {
    fun openAd(command: AdCommand.Create)
    fun deleteAd(id: Long)
}