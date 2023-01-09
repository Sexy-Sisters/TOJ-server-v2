package com.sexysisters.tojserverv2.infrastructure.ad

import com.sexysisters.tojserverv2.domain.ad.domain.Ad
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AdRepository : JpaRepository<Ad, UUID>, AdCustomRepository