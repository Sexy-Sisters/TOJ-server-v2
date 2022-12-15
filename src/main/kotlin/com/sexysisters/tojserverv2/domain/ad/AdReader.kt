package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad

interface AdReader {
    fun getAd(id: Long): Ad
}