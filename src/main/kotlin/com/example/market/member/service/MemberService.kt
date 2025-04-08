package com.example.market.member.service

import com.example.market.entity.Member
import com.example.market.member.dto.MemberRequest
import com.example.market.member.repository.MemberRepository
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import java.time.format.DateTimeFormatter

@Service
class MemberService(private val memberRepository: MemberRepository) {
    suspend fun signUp(memberRequest: MemberRequest) {
        var member: Member? = memberRepository.findByLoginId(memberRequest.loginId).awaitSingleOrNull()
        if (member != null) {
            throw IllegalArgumentException("이미 등록된 ID 입니다.")
        }

        val formattedBirthDate = memberRequest.birthDate.format(DateTimeFormatter.ISO_LOCAL_DATE)

        member = Member(
            null,
            memberRequest.loginId,
            memberRequest.password,
            memberRequest.name,
            memberRequest.birthDate,
            memberRequest.gender,
            memberRequest.email,
        )

        memberRepository.save(member).awaitSingle()
    }
}