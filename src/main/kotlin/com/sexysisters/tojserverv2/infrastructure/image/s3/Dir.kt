package com.sexysisters.tojserverv2.infrastructure.image.s3

enum class Dir {
    USER, TEACHER, AD,
}

fun Dir.getDirName() = when {
    this.name.equals(Dir.USER) -> "user-profile/"
    this.name.equals(Dir.TEACHER) -> "teacher-profile/"
    this.name.equals(Dir.AD) -> "ad-img/"
    else -> ""
}