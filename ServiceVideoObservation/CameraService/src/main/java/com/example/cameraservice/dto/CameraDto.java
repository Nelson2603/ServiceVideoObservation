package com.example.cameraservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CameraDto {
    private String ip;
    private String password;
}
