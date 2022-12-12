package com.sexysisters.tojserverv2.infrastructure.image.s3

enum class Dir {
    USER, TEACHER, AD,
}

fun Dir.getDirName() =
    when (name) {
        Dir.USER.name -> "user-profile/"
        Dir.TEACHER.name -> "teacher-profile/"
        Dir.AD.name -> "ad-img/"
        else -> ""
    }