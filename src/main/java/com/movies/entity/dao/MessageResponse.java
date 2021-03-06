package com.movies.entity.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MessageResponse {

    private int status;
    private String message;
    private LocalDateTime dateTime;
}
