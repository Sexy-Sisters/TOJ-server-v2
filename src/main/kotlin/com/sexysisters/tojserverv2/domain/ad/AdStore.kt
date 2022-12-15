package com.sexysisters.tojserverv2.domain.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad

interface AdStore {
    fun store(ad: Ad): Ad
}