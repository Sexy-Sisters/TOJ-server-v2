package com.sexysisters.tojserverv2.common.util.random

import org.apache.commons.lang3.RandomStringUtils

class RandomCodeUtil {
    companion object {
        fun generate(count: Int) =
            RandomStringUtils.randomAlphanumeric(count)
    }
}