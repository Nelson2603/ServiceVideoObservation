package com.example.cameraservice.service;

import com.example.cameraservice.client.ObjectServiceClient;
import com.example.cameraservice.dto.CameraDto;
import com.example.cameraservice.entity.Camera;
import com.example.cameraservice.repository.CameraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class CameraService {
//    private final CameraService cameraService;
    private final ObjectServiceClient objectServiceClient;
    private final CameraRepository cameraRepository;

    //сохранение камеры условие сохраняем если объект с таким номером сущесвует
    //получение айпи адреса камеры и пороля ввиде дто объекта получить по номеру камеры если в сервисе объектов
    // объект, прикрепленный к данной камере оплатил услугу

    /**
     * Сохраняет камеру, только если объект с numberObject существует
     */

    public Mono<Camera> createCamera(Camera camera) {
        int numberObject = camera.getNumberObject();
        return objectServiceClient.isObjectExist(numberObject)
                .flatMap(exist -> {
                    if (!exist) {
                        return Mono.error(new IllegalArgumentException(
                                "Объект с номером " + numberObject + " не существует"));
                    }
                    return cameraRepository.save(camera);
                });
    }

    public Mono<CameraDto>getCameraDto(int cameraNumber) {
        return cameraRepository.findByNumber(cameraNumber)
                .switchIfEmpty(Mono.error(new RuntimeException("Камера не найдена")))
                .flatMap(camera -> objectServiceClient.isObjectPaid(camera.getNumberObject())
                .flatMap(isPaid->{
                    if(!isPaid){
                        return Mono.error(new RuntimeException("Услуга не оплачена"));
                    }
                    return Mono.just(new CameraDto(camera.getIp(),camera.getPassword()));
                }));


    }
}
