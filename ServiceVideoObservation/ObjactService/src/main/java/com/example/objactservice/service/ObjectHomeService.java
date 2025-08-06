package com.example.objactservice.service;

import com.example.objactservice.entity.ObjectHome;
import com.example.objactservice.repository.ObjectHomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ObjectHomeService {
    private final ObjectHomeRepository objectHomeRepository;
    //сохраняем объект
    public ObjectHome saveObjectHome(ObjectHome objectHome) {
        return objectHomeRepository.save(objectHome);
    }

    //поиск по айди
    public Optional <ObjectHome> getObjectHomeById(Long id) {
        return objectHomeRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return objectHomeRepository.existsById(id);
    }

    //  * Проверяет, оплачен ли объект


    public boolean isPaidFor(Long id) {
        return getObjectHomeById(id)
                .map(ObjectHome::isPaidFor)
                .orElse(false);
    }
    /**
     * Обновляет статус оплаты объекта
     */
    public ObjectHome updatePaymentStatus(Long id, boolean paid) {
        ObjectHome object = objectHomeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Объект с ID " + id + " не найден"));

        object.setPaidFor(paid);
        return objectHomeRepository.save(object);
    }
}
