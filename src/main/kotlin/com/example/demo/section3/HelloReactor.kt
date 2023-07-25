package com.example.demo.section3

import reactor.core.publisher.Mono

//class HelloReactor {
//
//}

fun main(args: Array<String>) {
    /**
     * 리액터로 구현된 코드는 퍼블리셔가 오퍼레이터를 사용해서 데이터를 내보내고
     * 그 데이터를 subscribe쪽에서 전달받기 위해서 subscribe 메소드를 호출함
     * 그러면 퍼블리셔쪽에서 정의된 데이터가 섭스크라이브 쪽에 전달이됨
     *  기본적인 형태임 퍼블리셔 -> 데이터 소스 -> 구독자
     */
    Mono.just("Hello Reactor")
        .subscribe { message -> println(message) }
}