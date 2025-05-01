package com.hcmutnightowls.authenticationservice.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class requestRegister {
    Long id;
    String Subject;
    String password;
}
