package com.example.demo.section3

import reactor.core.publisher.Mono

/**
 * 원본 데이터의 emit 없이 onComplete signal만 emit한다
 */

    fun main(args:Array<String>){
    /**
     * 첫번째는 상위 업스트림으로부터 에밋된 데이터를 전달받는 부분
     * 두번째는 업스트림에서 에러가 발생했을 때 에러를받는부분
     * 세번째는 업스트림으로부터 온컴플릿 시그널을 전달받ㄴ느 부분
     */
    Mono.empty<Any>()
            .subscribe(
                { data -> println(data) },
                { error -> },
                { println("Emitted onComplete signal") }
            )


    }
