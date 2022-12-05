package com.pavel.forumapplication.Model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {
    private String username;

    private String password;
}
