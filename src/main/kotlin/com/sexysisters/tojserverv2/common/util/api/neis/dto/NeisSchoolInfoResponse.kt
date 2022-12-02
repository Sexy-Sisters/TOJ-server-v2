package com.sexysisters.tojserverv2.infrastructure.neis

class NeisSchoolInfoResponse(
    val schoolInfo: List<SchoolInfo>
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
    val ATPT_OFCDC_SC_NM: String, // 교육청,
    val SCHUL_NM: String, // 이름,
    val ENG_SCHUL_NM: String, // 영문명,
    val SCHUL_KND_SC_NM: String, // 고등학교,
    val ORG_RDNMA: String, // 주소,
    val FOAS_MEMRD: String, // 설립일,
    val HMPG_ADRES: String, // 홈페이지 주소
    val ORG_TELNO: String, // 전화번호
)