package com.example.demo.section3

import reactor.core.publisher.Flux

fun main(args:Array<String>){
    /**
     *
     */
    Flux.just(6,9,13)
        .map { data -> data%2 }
        .subscribe{remainder -> print(remainder) }
}