package ma.youcode.candlelight.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import jakarta.validation.Valid;
import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.services.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor

@Controller
public class UserController {

    private final UserService service;

    @MutationMapping
    public UserDto signUp(final @Valid @Argument UserSignUpDto newUser) {
        return service.create(newUser);
    }

    @MutationMapping
    public UserDto login(final @Valid @Argument UserLoginDto user) {
        return service.login(user);
    }

    @MutationMapping
    public UserDto updateUser(final @Argument Long id, final @Argument UserSignUpDto user) {
        return service.update(id, user);
    }

    @MutationMapping
    public String deleteUser(final @Argument Long id) {
        return service.delete(id);
    }

}
