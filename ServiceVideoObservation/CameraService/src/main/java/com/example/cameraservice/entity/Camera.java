package com.example.cameraservice.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "Cameras")
public class Camera {
    @Id
    private Long id;
    private int number;
    private String ip;
    private int port;
    private boolean status;
    private int numberObject;
    private String password;


}
