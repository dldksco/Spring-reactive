package com.example.demo.section5

import reactor.core.publisher.BufferOverflowStrategy
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * unbounded request일 경우, Downstream에
 * Downstream으로 전달할 데이터가 버퍼에 가득찰경우, 버퍼 안에 있는 데이터 중에서
 * 가장 먼저 버퍼로 들어온 오래된 데이터부터 drop 시키는 전략
 */
fun main() {
    Flux.interval(Duration.ofMillis(300L))
        .doOnNext {data-> println("#emiit by ${data}")}
        .onBackpressureBuffer(
            2,
            {
            dropped ->  println("overflow&drop${dropped}")
            },BufferOverflowStrategy.DROP_OLDEST
           )
        /**
         * prefetch란?
         * 추가된 thread에서 사용하는 일종의 버퍼같은 개념
         */
        .publishOn(Schedulers.parallel(),false,1)
        .subscribe(
            { data ->
                Thread.sleep(1000)
                println(data)
            },
            { error ->
                println(error)
            }
        )
    Thread.sleep(2000)
}