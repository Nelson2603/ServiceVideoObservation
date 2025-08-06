package com.example.cameraservice.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ObjectServiceClient {
    private final WebClient webClient;

    public Mono<Boolean> isObjectExist(int numberObject){
        return webClient.get()
                .uri("/api/objects/{id}", numberObject)
                .exchangeToMono(response->{
                    if(response.statusCode().is2xxSuccessful()){
                        return Mono.just(true);
                    }else {
                        return Mono.just(false);
                    }
                }).onErrorReturn(false);
    }

    /**
    *Проверяет оплачен ли объект
            */
    public Mono<Boolean> isObjectPaid(int numberObject){
        return webClient.get()
                .uri("api/objects/{id}/is-paid",numberObject)
                .retrieve()
                .bodyToMono(Boolean.class)
                .onErrorReturn(false);
    }

}
