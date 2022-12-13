package com.sexysisters.tojserverv2.interfaces.wiki

import com.sexysisters.tojserverv2.domain.wiki.Wiki
import org.springframework.data.jpa.repository.JpaRepository

interface WikiRepository : JpaRepository<Wiki, Long>