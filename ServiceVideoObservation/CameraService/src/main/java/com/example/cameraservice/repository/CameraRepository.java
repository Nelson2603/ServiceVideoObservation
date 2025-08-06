package com.example.cameraservice.repository;

import com.example.cameraservice.entity.Camera;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CameraRepository extends R2dbcRepository<Camera, Long> {
    Mono<Camera> findByNumber(int number);
}
