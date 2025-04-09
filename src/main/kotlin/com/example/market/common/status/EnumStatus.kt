package com.example.market.common.status

enum class Gender(val description: String) {
    MALE("남"),
    FEMALE("여"),
}

enum class ResultCode(val message: String) {
    SUCCESS("정상 처리되었습니다."),
    ERROR("오류가 발생했습니다."),
}