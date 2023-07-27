package com.example.demo.section5

import org.reactivestreams.Subscription
import reactor.core.publisher.BaseSubscriber
import reactor.core.publisher.Flux

/**
 * subscriber가 처리가능한 만큼의 request를 갯수를 조절하는 backpressure
 */
fun main() {
    var count =0

    Flux.range(1,5)
        .doOnNext{data -> println(data)}
        .doOnRequest { data -> println(data) }
        .subscribe (


            object : BaseSubscriber<Int>(){
                override fun hookOnSubscribe(subscription: Subscription) {
                request(2)
            }

            override fun hookOnNext(value: Int) {
                count++;
                println(count)
                if(count==2){
                    Thread.sleep(2000)
                    request(2)
                    count=0
                }
            }
        })
}