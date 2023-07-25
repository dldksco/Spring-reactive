package com.example.demo.section3

import reactor.core.publisher.Flux

fun main(args:Array<String>){
    Flux.fromArray(arrayOf(3, 6, 7, 9))
        .filter{ num -> num>6 }
        .map { num -> num*2 }
        .subscribe { multiply -> println(multiply) }
}