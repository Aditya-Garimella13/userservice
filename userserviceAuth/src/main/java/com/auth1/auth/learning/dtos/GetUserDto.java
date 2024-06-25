package com.auth1.auth.learning.dtos;

import com.auth1.auth.learning.model.User;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetUserDto {
    User user;
    String error;
}
