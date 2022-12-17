package com.sexysisters.tojserverv2.infrastructure.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import org.springframework.data.jpa.repository.JpaRepository

interface AdRepository : JpaRepository<Ad, Long>, AdCustomRepository