package com.sexysisters.tojserverv2.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotEmpty

@ConstructorBinding
@Validated
@ConfigurationProperties("cloud.aws.s3")
class S3Properties(
    @NotEmpty
    val bucket: String,
) {
    companion object {
        val defaultProfileImg = "https://toj-img-bucket.s3.ap-northeast-2.amazonaws.com/profile-img/default.jpg"
    }
}