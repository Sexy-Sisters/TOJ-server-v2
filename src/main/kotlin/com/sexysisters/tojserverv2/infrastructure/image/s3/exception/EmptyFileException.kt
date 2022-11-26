package com.sexysisters.tojserverv2.infrastructure.image.s3.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.infrastructure.image.ImageErrorCode

class EmptyFileException : BaseException(ImageErrorCode.EMPTY_FILE)