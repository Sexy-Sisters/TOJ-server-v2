package com.sexysisters.tojserverv2.common.util.api.neis.dto

class NeisSchoolInfoResponse(
    val schoolInfo: List<SchoolInfo>?
)

class SchoolInfo(
    val head: List<Head>,
    val row: List<Row>
)

class Head(
    val RESULT: Result,
    val list_total_count: Int,
)

class Result(
    val CODE: String,
    val MESSAGE: String,
)

data class Row(
    val SD_SCHUL_CODE: String, // 학교 코드
    val ATPT_OFCDC_SC_NM: String, // 교육청,
    val SCHUL_NM: String, // 이름,
    val SCHUL_KND_SC_NM: String, // 초,중,고 구분
    val ORG_RDNMA: String, // 주소
    val FOAS_MEMRD: String, // 설립일
    val HMPG_ADRES: String, // 홈페이지 주소
    val ORG_TELNO: String, // 전화번호
    val HS_SC_NM: String, // 고등학교 종류
)