package com.sexysisters.tojserverv2.infrastructure.image.s3.exception

import com.sexysisters.tojserverv2.common.exception.BaseException
import com.sexysisters.tojserverv2.infrastructure.image.ImageErrorCode

class FileException {
    class EmptyFile : BaseException(ImageErrorCode.EMPTY_FILE)
    class SaveFailed : BaseException(ImageErrorCode.FAILED_SAVE_FILE)
    class TooStringTitle : BaseException(ImageErrorCode.TOO_String_TITLE)
}