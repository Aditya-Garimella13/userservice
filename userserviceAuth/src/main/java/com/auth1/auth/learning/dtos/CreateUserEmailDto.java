package com.auth1.auth.learning.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserEmailDto {
    String from;
    String to;
    String body;
    String subject;
}
