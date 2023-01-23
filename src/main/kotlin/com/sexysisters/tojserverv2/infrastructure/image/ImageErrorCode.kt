package com.sexysisters.tojserverv2.infrastructure.image

import com.sexysisters.tojserverv2.common.response.ErrorProperty

enum class ImageErrorCode(
    override val errorMsg: String,
) : ErrorProperty {
    FAILED_SAVE_FILE("Faild to save file"),
    EMPTY_FILE("File is empty"),
    TOO_String_TITLE("Title is too String"),
}