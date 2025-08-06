package com.example.objactservice.contrller;

import com.example.objactservice.entity.ObjectHome;
import com.example.objactservice.service.ObjectHomeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/objects")
@RequiredArgsConstructor
public class ObjectHomeController {
    private final ObjectHomeService objectHomeService;

    @GetMapping("/{id}")
    public ResponseEntity<ObjectHome> getObjectHome(@PathVariable Long id) {
        return objectHomeService.getObjectHomeById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new RuntimeException("объект не найден"));
    }
    @PostMapping()
    public ResponseEntity<ObjectHome>createObjectHome(@RequestBody ObjectHome objectHome) {
        ObjectHome saveObjectHome = objectHomeService.saveObjectHome(objectHome);
        return new ResponseEntity<>(saveObjectHome, HttpStatus.CREATED);
    }
    /**
     * GET /api/objects/{id}/is-paid — проверить, оплачен ли объект
     */
    @GetMapping("/{id}/is-paid")
    public Boolean isPaidFor(@PathVariable Long id) {
        return objectHomeService.isPaidFor(id);
    }

}
