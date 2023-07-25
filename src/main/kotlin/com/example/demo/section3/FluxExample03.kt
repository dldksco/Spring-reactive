package com.example.demo.section3

import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

fun main() {
    /**
     * concatwith를 이용해서 mono를 붙혀서 새로운 flux를 만들 수 있음
     */
    val flux: Flux<Any> =
        Mono.justOrEmpty<Any>(null)
            .concatWith(Mono.justOrEmpty("Jobs"))
    flux.subscribe { data ->
        println("# result: $data")
    }
}