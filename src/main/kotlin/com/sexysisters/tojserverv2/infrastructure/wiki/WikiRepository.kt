package com.sexysisters.tojserverv2.infrastructure.wiki

import com.sexysisters.tojserverv2.domain.wiki.domain.Wiki
import org.springframework.data.jpa.repository.JpaRepository

interface WikiRepository : JpaRepository<Wiki, String>