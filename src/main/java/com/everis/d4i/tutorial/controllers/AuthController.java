package com.everis.d4i.tutorial.controllers;

import com.everis.d4i.tutorial.payload.request.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface AuthController {

    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest);

}
