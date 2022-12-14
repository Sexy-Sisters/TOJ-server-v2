package com.sexysisters.tojserverv2.interfaces.image

import com.sexysisters.tojserverv2.common.response.CommonResponse
import com.sexysisters.tojserverv2.infrastructure.image.s3.Dir
import com.sexysisters.tojserverv2.infrastructure.image.s3.S3Executor
import com.sexysisters.tojserverv2.infrastructure.image.s3.getDirName
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@Api(tags = ["이미지 업로드 관련 API"])
@RestController
@RequestMapping("/api/v2/image")
class ImageApiController(
    private val s3Executor: S3Executor,
) {

    @ApiOperation("이미지 업로드")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveImage(
        @RequestPart images: List<MultipartFile>,
        @RequestParam(name = "dir")dir: Dir,
    ): CommonResponse<ImageUrlResponse> {
        val dirName = dir.getDirName()
        val imgUrlList = images.map { s3Executor.uploadImage(it, dirName) }
        return CommonResponse.success(ImageUrlResponse(imgUrlList))
    }
}