package com.quipux.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLogin {
    private String username;
    private String password;
}
