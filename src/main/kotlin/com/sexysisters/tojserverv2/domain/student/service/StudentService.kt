package com.sexysisters.tojserverv2.domain.student.service

import com.sexysisters.tojserverv2.domain.student.StudentCommand
import com.sexysisters.tojserverv2.domain.student.StudentInfo

interface StudentService {
    fun createStudent(command: StudentCommand.Create): Long
    fun getWaitingList(): List<StudentInfo.Main>
    fun getStudentList(): List<StudentInfo.Main>
}