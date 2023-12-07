package com.br.backend.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.backend.dto.AuthenticationRecordDTO;
import com.br.backend.dto.RegisterRecordDTO;
import com.br.backend.dto.UsuarioResponseRecordDTO;
import com.br.backend.infra_security.TokenService;
import com.br.backend.model.UserModel;
import com.br.backend.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationRecordDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.userSystem(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new UsuarioResponseRecordDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterRecordDTO data) {
        if (this.repository.findByUserSystem(data.userSystem()) != null || this.repository.findByEmail(data.email()) != null) {
            return ResponseEntity.badRequest().build();
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UserModel newUser = new UserModel(data.userSystem(), data.email(), encryptedPassword, data.role(), data.name());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }
}
