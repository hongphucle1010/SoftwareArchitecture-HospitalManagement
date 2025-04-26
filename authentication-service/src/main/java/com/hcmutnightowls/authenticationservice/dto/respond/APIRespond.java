package com.hcmutnightowls.authenticationservice.dto.respond;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class APIRespond<T> {
    private int status;
    private String message;
    private T data;
}
