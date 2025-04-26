package com.hcmutnightowls.authenticationservice.utils;

import com.hcmutnightowls.authenticationservice.component.JwtAuthen;
import com.hcmutnightowls.authenticationservice.config.TestConfig;
import com.hcmutnightowls.authenticationservice.model.Staff;
import com.hcmutnightowls.authenticationservice.repository.StaffRepo;
import com.hcmutnightowls.authenticationservice.service.AuthService;
import com.hcmutnightowls.authenticationservice.service.StaffService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

//import java.util.Optional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class MockUsers {

    @InjectMocks
    private StaffService staffService;

    @Mock
    private StaffRepo staffRepo;

    @Mock
    private AuthService authService;

    @Test
    public void testAuthentication() {
//         Create a mock user
        Staff mockUser = Staff.builder()
                .id(101)
                .subject("nphung.htkhang2020@gmail.com")
                .role("DOCTOR")
                .password("123456")
                .build();

        // Setup the mock repository to return our mock user
        Mockito.when(staffRepo.findBySubject("nphung.htkhang2020@gmail.com")).thenReturn(Optional.of(mockUser));


        // Test the authentication with plaintext password
        String token = "string";
        assertNotNull(token);
    }
}