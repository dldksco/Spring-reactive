package com.example.demo.section5

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * subscriber가 처리가능한 만큼의 request를 제어함
 */
fun main() {
    Flux.range(1,5)
        .doOnNext{data -> println(data)}
        .doOnRequest { data -> println(data) }
        .subscribe (
            /**
             * basesubscriber를 이용하면 요청데이터개수를 조절할 수 있음
             *
             */

            object : BaseSubscriber<Int>(){
                /**
                 * 구독 시점에 데이터의 요청개수를 1로지정함
                 */
                override fun hookOnSubscribe(subscription: Subscription) {
                request(1)
            }

            override fun hookOnNext(value: Int) {
                Thread.sleep(2000)
                println(value)
                request(1)
            }
        })
}