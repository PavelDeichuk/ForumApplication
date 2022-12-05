package com.pavel.forumapplication.Controller;

import com.pavel.forumapplication.Model.JwtRequest;
import com.pavel.forumapplication.Model.JwtResponse;
import com.pavel.forumapplication.Security.UserDetailService;
import com.pavel.forumapplication.Utility.JwtUtility;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class TokenController {
    private final AuthenticationManager authenticationManager;

    private final UserDetailService userDetailService;

    private final JwtUtility jwtUtility;

    public TokenController(AuthenticationManager authenticationManager, UserDetailService userDetailService, JwtUtility jwtUtility) {
        this.authenticationManager = authenticationManager;
        this.userDetailService = userDetailService;
        this.jwtUtility = jwtUtility;
    }

    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> GetToken(@RequestBody JwtRequest jwtRequest) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        try {
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword());
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        } catch (Exception exception) {
            throw new RuntimeException(exception.getMessage());
        }
        final UserDetails userDetails = userDetailService.loadUserByUsername(jwtRequest.getUsername());
        final String token = jwtUtility.generateToken(userDetails);
        JwtResponse tokens = new JwtResponse(token);
        return new ResponseEntity(tokens, httpHeaders, HttpStatus.OK);
    }
}
