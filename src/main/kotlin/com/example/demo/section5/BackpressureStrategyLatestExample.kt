package com.example.demo.section5

import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers
import java.time.Duration

/**
 * unbounded request일 경우, Downstream에 Backpressure drop 전략을 적용하는 예제
 * Downstream으로 전달할 데이터가 버퍼에 가득찰경우, 버퍼 밖에서 폐기되지 않고 대기하는 가장 최근에 emit 된 데이터부터
 * 버퍼에 채움
 */
fun main() {
    /**
     * drop이랑 뭐가 다르지?
     * 드랍은 가득찬다면 바로 그 즉시 드랍시킴
     *
     * 레이티스트는
     * 버퍼가 가득찬 상태에서 데이터 하나(1)가 들어오면 그 즉시 드랍이되는게 아니라
     * 데이터(2) 하나가 더 들어와서 (1)데이터가 폐기가됨
     */
    Flux.interval(Duration.ofMillis(1L))
        .onBackpressureLatest()
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