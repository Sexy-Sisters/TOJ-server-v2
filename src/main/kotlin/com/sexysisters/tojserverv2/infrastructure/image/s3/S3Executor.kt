package com.sexysisters.tojserverv2.infrastructure.image.s3

import com.amazonaws.services.s3.AmazonS3Client
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import com.sexysisters.tojserverv2.config.properties.S3Properties
import com.sexysisters.tojserverv2.infrastructure.image.ImageUtil
import com.sexysisters.tojserverv2.infrastructure.image.s3.exception.EmptyFileException
import com.sexysisters.tojserverv2.infrastructure.image.s3.exception.SaveFailedException
import com.sexysisters.tojserverv2.infrastructure.image.s3.exception.TooLongTitleException
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Component
class S3Executor(
    private val s3Properties: S3Properties,
    private val amazonS3Client: AmazonS3Client,
) : ImageUtil {

    override fun uploadImage(image: MultipartFile, dirName: String): String {
        validateFile(image)
        val fileName = "${s3Properties.bucket}/${dirName}${UUID.randomUUID()}-${image.originalFilename}"
        try {
            val putObjectRequest = PutObjectRequest(
                s3Properties.bucket,
                fileName,
                image.inputStream,
                getObjectMetadata(image)
            )
            amazonS3Client.putObject(putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead))
        } catch (e: Exception) {
            throw SaveFailedException()
        }

        return amazonS3Client.getUrl(s3Properties.bucket, fileName).toString()
    }

    private fun getObjectMetadata(image: MultipartFile): ObjectMetadata {
        val objectMetadata = ObjectMetadata()
        objectMetadata.contentType = image.contentType
        objectMetadata.contentLength = image.size
        return objectMetadata
    }

    private fun validateFile(file: MultipartFile) {
        val isTooLong = file.originalFilename!!.length > 20
        if (file.isEmpty) {
            throw EmptyFileException()
        }
        if (isTooLong) {
            throw TooLongTitleException()
        }
    }
}