package ma.youcode.candlelight.services.implementations;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.exceptions.ResourceAlreadyExist;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.repositories.UserRepository;
import ma.youcode.candlelight.services.UserService;

@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public UserDto create(final UserSignUpDto newUser) {
        if(repository.existsById(newUser.getUserName()))
            throw new ResourceAlreadyExist("User name ("+ newUser.getUserName() +") is taken.");
        if(repository.existsByEmailIgnoreCase(newUser.getEmail()))
            throw new ResourceAlreadyExist("User email ("+ newUser.getEmail() +") is taken.");
        if(repository.existsByPhoneNumber(newUser.getPhoneNumber()))
            throw new ResourceAlreadyExist("User phone number ("+ newUser.getPhoneNumber() +") is taken.");
        return modelMapper.map(
            repository.save(modelMapper.map(newUser, User.class)),
            UserDto.class
        );
    }

    

    @Override
    public UserDto login(UserLoginDto user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }

    @Override
    public UserDto update(String userName, UserSignUpDto updatedUser) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public UserDto delete(String userName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
    
}
