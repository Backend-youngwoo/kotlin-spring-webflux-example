package com.example.market.member.service

import com.example.market.common.exception.InvalidInputException
import com.example.market.entity.Member
import com.example.market.member.dto.MemberRequest
import com.example.market.member.repository.MemberRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(private val memberRepository: MemberRepository) {
    suspend fun signUp(memberRequest: MemberRequest): String {
        var member: Member? = memberRepository.findByLoginId(memberRequest.loginId).awaitSingleOrNull()
        if (member != null) {
            throw InvalidInputException("loginId", "이미 등록된 ID 입니다.")
        }

        member = memberRequest.toEntity()
        memberRepository.save(member).awaitSingle()

        return "회원가입이 완료되었습니다."
    }
}