package com.example.market.member.repository

import com.example.market.entity.Member
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface MemberRepository : ReactiveMongoRepository<Member, String>{
    fun findByLoginId(loginId: String): Mono<Member>
}
