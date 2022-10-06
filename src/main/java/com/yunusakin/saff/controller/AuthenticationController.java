package com.yunusakin.saff.controller;

import com.yunusakin.saff.controller.data.AuthRequest;
import com.yunusakin.saff.controller.data.AuthResponse;
import com.yunusakin.saff.handler.ResponseHandler;
import com.yunusakin.saff.model.User;
import com.yunusakin.saff.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private AccessTokenUtil accessTokenUtil;

    @PostMapping("/accesstoken")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );

            User user = (User) authentication.getPrincipal();
            String accessToken = accessTokenUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmail(), accessToken);
            return ResponseHandler.generateResponse(true,null, HttpStatus.OK, response);
        } catch (BadCredentialsException ex) {
            return ResponseHandler.generateResponse(false, ex.getMessage(), HttpStatus.UNAUTHORIZED, null);
        }catch (Exception e) {
            return ResponseHandler.returnErrorResponse(e);
        }
    }
}
