package com.hcmutnightowls.authenticationservice.dto.request;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class request {
    String Subject;
    String password;
}
