package com.example.demo

import org.junit.jupiter.api.Test
import reactor.core.publisher.Flux


class Class01ExampleTest {
    @Test
    fun helloReactorTest(){
        /**
         * Flux : 생산자 즉 Publisher
         * Subscribe : subscriber가 sequence를 구독하는 것
        *   Emit : publisher가 데이터를 내보내는 것
         *  Sequence : publisher가 emit하는 데이터의 연속적인 흐름을 정의해둔 것, operator 체인형태로 정의됨
         *  dispose : subscriber가 sequence 구독을 해지하는것
         */
        val sequence: Flux<String> = Flux.just("Hello", "Reacotr")
        sequence.map{data -> data.lowercase()}
            .subscribe {data -> println(data) }
    }
}