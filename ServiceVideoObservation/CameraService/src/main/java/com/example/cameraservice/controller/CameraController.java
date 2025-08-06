package com.example.cameraservice.controller;

import com.example.cameraservice.dto.CameraDto;
import com.example.cameraservice.entity.Camera;
import com.example.cameraservice.service.CameraService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/cameras")

public class CameraController {
    private final CameraService cameraService;

    public CameraController(CameraService cameraService) {
        this.cameraService = cameraService;

    }

    /**
     * POST /api/cameras — сохранить камеру
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Camera> saveCamera(@RequestBody Camera camera) {
        return cameraService.createCamera(camera);
    }

    /**
     * GET /api/cameras/credentials/{number} — получить IP и пароль
     */
    @GetMapping("/credentials/{number}")
    public Mono<CameraDto> getCamera(@PathVariable int number) {
        return cameraService.getCameraDto(number);
    }
}




//сохранение камеры условие сохраняем если объект с таким номером сущесвует
//получение айпи адреса камеры и пороля ввиде дто объекта получить по номеру камеры если в сервисе объектов
// объект, прикрепленный к данной камере оплатил услугу