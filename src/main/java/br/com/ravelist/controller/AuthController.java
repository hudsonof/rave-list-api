package br.com.ravelist.controller;

import br.com.ravelist.exception.BadRequestException;
import br.com.ravelist.payload.request.LoginRequest;
import br.com.ravelist.payload.request.SignupRequest;
import br.com.ravelist.payload.request.TokenRefreshRequest;
import br.com.ravelist.payload.response.JwtResponse;
import br.com.ravelist.payload.response.MessageResponse;
import br.com.ravelist.payload.response.TokenRefreshResponse;
import br.com.ravelist.security.service.UserDetailsImpl;
import br.com.ravelist.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    final AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<MessageResponse> signUp(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authService.singUp(signUpRequest));
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenRefreshResponse> refreshToken(@Valid @RequestBody TokenRefreshRequest request) {
        return ResponseEntity.ok(authService.refreshToken(request));
    }

    @DeleteMapping("/logout")
    public ResponseEntity<MessageResponse> logout(Principal principal) {
        if (principal == null) {
            throw new BadRequestException("You are not logged in.");
        }

        UserDetailsImpl userDetails = (UserDetailsImpl) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

        return ResponseEntity.ok(authService.logout(userDetails.getId()));
    }

}
