package com.example.demo.section5

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * unbounded request일 경우, Downstream에 Backpressure drop 전략을 적용하는 예제
 * Downstream으로 전달할 데이터가 버퍼에 가득찰경우, 버퍼 밖에서 대기하는 먼저 emit 된 데이터를  drop함
 */
fun main() {
    Flux.interval(Duration.ofMillis(1L))
        .onBackpressureDrop { dropped -> println("#drropp ${dropped}")}
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