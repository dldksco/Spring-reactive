package com.example.demo.section5

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * unbounded request일 경우, Downstream에 Backpressure Error 전략을 적용하는 예제
 * Downstream으로 전달할 데이터가 버퍼에 가득찰경우, exception을 발생
 */
fun main() {
    Flux.interval(Duration.ofMillis(1L))
        .onBackpressureError()
        .doOnNext { data -> println("doOnNext $data") }
        .publishOn(Schedulers.parallel())
        .subscribe(
            { data ->
                Thread.sleep(5)
                println(data)
            },
            { error ->
                println(error)
            }
        )
    Thread.sleep(2000)
}