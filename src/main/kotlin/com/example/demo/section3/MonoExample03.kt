package com.example.demo.section3

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import com.jayway.jsonpath.DocumentContext
import com.jayway.jsonpath.JsonPath

fun main(args: Array<String>) {
    val worldTimeUri = UriComponentsBuilder.newInstance().scheme("http").host("worldtimeapi.org").port(80)
        .path("/api/timezone/Asia/Seoul").build().encode().toUri()

    val restTemplate = RestTemplate()
    val headers = HttpHeaders()
    headers.accept = listOf(MediaType.APPLICATION_JSON)
    /**
     * mono는 http를 처리하기 좋음
     */
    Mono.just(
        /**
         * just operator를 사용해서 emit함
         */
        restTemplate.exchange(
            worldTimeUri, HttpMethod.GET, HttpEntity<String>(headers),
            String::class.java
        )
    )
        .map {
            /**
             * 두번째 오퍼레이터에서 가공을함
             */
            response ->
            val jsonContext: DocumentContext = JsonPath.parse(response.body)
            val dateTime = jsonContext.read<String>("$.datetime")
            dateTime
        }
        .subscribe(
            /**
             * 최종적으로 subscribe쪽에 전달이됨
             */
            { data -> println("# emitted data: $data") },
            { error -> println("# error: ${error.message}") },
            { println("# emitted onComplete signal") }
        )
}