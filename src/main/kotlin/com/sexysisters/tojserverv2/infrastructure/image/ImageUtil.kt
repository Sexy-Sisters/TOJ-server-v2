package com.sexysisters.tojserverv2.infrastructure.image

import org.springframework.web.multipart.MultipartFile

interface ImageUtil {
    fun uploadImage(
        image: MultipartFile,
        dirName: String,
    ): String
}