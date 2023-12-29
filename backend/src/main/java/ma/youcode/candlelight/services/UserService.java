package ma.youcode.candlelight.services;

import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;

public interface UserService {

    public UserDto create(UserSignUpDto newUser);
    public UserDto login(UserLoginDto user);
    public UserDto update(String userName, UserSignUpDto updatedUser);
    public UserDto delete(String userName);
}
