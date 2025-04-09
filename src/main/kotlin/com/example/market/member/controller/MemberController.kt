package com.example.market.member.controller

import com.example.market.member.dto.MemberRequest
import com.example.market.member.service.MemberService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/members")
class MemberController(private val memberService: MemberService) {
    @PostMapping("/signup")
    suspend fun signUp(@RequestBody @Valid memberRequest: MemberRequest): ResponseEntity<Any> {
        return try {
            memberService.signUp(memberRequest)
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } catch (e: IllegalArgumentException) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapOf("error" to e.message))
        }
    }
}