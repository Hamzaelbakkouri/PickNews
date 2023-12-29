package ma.youcode.candlelight.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Controller
public class UserController {

    private final UserService service;

    @MutationMapping
    public UserDto signUp(final @Argument UserSignUpDto newUser) {
        return service.create(newUser);
    }

}
