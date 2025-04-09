package com.example.market.member.controller

import com.example.market.common.dto.BaseResponse
import com.example.market.member.dto.MemberRequest
import com.example.market.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @PostMapping("/signup")
    suspend fun signUp(@RequestBody @Valid memberRequest: MemberRequest): BaseResponse<Unit> {
        val resultMessage = memberService.signUp(memberRequest)
        return BaseResponse(message = resultMessage)
    }
}