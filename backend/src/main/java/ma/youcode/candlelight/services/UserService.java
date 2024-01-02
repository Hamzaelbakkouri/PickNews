package ma.youcode.candlelight.services;

import java.util.List;

import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;

public interface UserService {

    public UserDto create(UserSignUpDto newUser);
    public UserDto login(UserLoginDto user);
    public UserDto update(Long userName, UserSignUpDto updatedUser);
    public List<UserDto> search(String userName);
    public String delete(Long userName);
}
