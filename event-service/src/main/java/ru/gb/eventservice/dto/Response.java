package ru.gb.eventservice.dto;

import lombok.Data;

@Data
public class Response {
    private boolean result = false;
    private String error = "";
}
