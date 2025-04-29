package com.hcmutnightowls.authenticationservice.controller;

import com.hcmutnightowls.authenticationservice.dto.request.request;
import com.hcmutnightowls.authenticationservice.dto.respond.APIRespond;
import com.hcmutnightowls.authenticationservice.dto.respond.TokenResponse;
import com.hcmutnightowls.authenticationservice.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth/oauth2")
@RequiredArgsConstructor
public class OAuth2Controller {

    private final TokenService tokenService;

    @Operation(summary = "Get access token for OAuth2 clients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/token")
    public ResponseEntity<APIRespond<TokenResponse>> getToken(@RequestBody request tokenRequest) {
        try {
            // For demonstration purposes, we'll use a simplified authentication
            // In a real application, you would validate credentials against your user store
            String username = tokenRequest.getUsername();
            
            // Simulate roles based on username prefix
            List<GrantedAuthority> authorities;
            
            if (username.startsWith("admin")) {
                authorities = Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("admin")
                );
            } else if (username.startsWith("staff")) {
                authorities = Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_STAFF"),
                    new SimpleGrantedAuthority("staff.read"),
                    new SimpleGrantedAuthority("staff.write")
                );
            } else {
                authorities = Arrays.asList(
                    new SimpleGrantedAuthority("ROLE_PATIENT"),
                    new SimpleGrantedAuthority("patient.read")
                );
            }
            
            // Create authentication object
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username, null, authorities);
            
            // Store in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
            
            // Generate tokens
            String accessToken = tokenService.generateAccessToken(username, authorities);
            String refreshToken = tokenService.generateRefreshToken(username);
            
            TokenResponse response = new TokenResponse(accessToken, refreshToken, 
                    3600L, "Bearer", 
                    authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            
            return ResponseEntity.ok(APIRespond.<TokenResponse>builder()
                    .status(200)
                    .data(response)
                    .message("Token generated successfully")
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<TokenResponse>builder()
                    .status(401)
                    .message("Authentication failed: " + e.getMessage())
                    .build());
        }
    }
    
    @Operation(summary = "Refresh an expired access token", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PostMapping("/refresh")
    public ResponseEntity<APIRespond<TokenResponse>> refreshToken(@RequestBody request refreshRequest) {
        try {
            // In a real application, you would validate the refresh token
            // and generate a new access token based on the user's identity and authorities
            
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            List<GrantedAuthority> authorities = authentication.getAuthorities().stream()
                    .map(a -> (GrantedAuthority) a)
                    .collect(Collectors.toList());
            
            String accessToken = tokenService.generateAccessToken(username, authorities);
            String refreshToken = tokenService.generateRefreshToken(username);
            
            TokenResponse response = new TokenResponse(accessToken, refreshToken, 
                    3600L, "Bearer", 
                    authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
            
            return ResponseEntity.ok(APIRespond.<TokenResponse>builder()
                    .status(200)
                    .data(response)
                    .message("Token refreshed successfully")
                    .build());
            
        } catch (Exception e) {
            return ResponseEntity.status(401).body(
                    APIRespond.<TokenResponse>builder()
                    .status(401)
                    .message("Token refresh failed: " + e.getMessage())
                    .build());
        }
    }
}