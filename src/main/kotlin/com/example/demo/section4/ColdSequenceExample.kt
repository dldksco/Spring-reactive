package com.example.demo.section4

import reactor.core.publisher.Flux

fun main() {
    /**
     * coldSequence기때문에 타임라인이 개가 생성됨
     */
    val coldFlux: Flux<String> = Flux.fromIterable(listOf("RED", "YELLOW", "PINK"))
        .map {
            it-> it.lowercase()
        }
    coldFlux.subscribe{country -> println(country)}
    println("-------------------------")
    coldFlux.subscribe {country -> println(country)}

}