package com.hcmutnightowls.authenticationservice.model;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PUBLIC)  // Make default constructor public
public class Patient extends User {
}
