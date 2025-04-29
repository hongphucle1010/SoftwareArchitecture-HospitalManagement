package com.hcmutnightowls.authenticationservice.model;


import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PUBLIC)  // Make default constructor public
@Entity
@Data
@Builder
public class Admin extends User {
}
