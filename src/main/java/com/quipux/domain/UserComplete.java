package com.quipux.domain;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserComplete {

    private String id;

    private String email;

    private String completeName;

    private String token;

}
