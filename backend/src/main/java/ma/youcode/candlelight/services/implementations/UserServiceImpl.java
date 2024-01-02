package ma.youcode.candlelight.services.implementations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import ma.youcode.candlelight.exceptions.InvalidCredentials;
import ma.youcode.candlelight.exceptions.ResourceAlreadyExist;
import ma.youcode.candlelight.exceptions.ResourceNotFound;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.repositories.UserRepository;
import ma.youcode.candlelight.services.SequenceGeneratorService;
import ma.youcode.candlelight.services.UserService;

@AllArgsConstructor

@Service
public class UserServiceImpl implements UserService {
    
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final ModelMapper modelMapper;
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public UserDto create(final UserSignUpDto newUser) {
        validCredentials(newUser);
        var user = modelMapper.map(newUser, User.class);
        user.setJoinedAt(LocalDate.now());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(sequenceGenerator.generateSequence(User.getSequenceName()));
            return modelMapper.map(
            repository.save(user),
            UserDto.class
        );
    }


    private void validCredentials(UserSignUpDto user) {
        if(user.getUserName() != null && repository.existsByUserName(user.getUserName()))
            throw new ResourceAlreadyExist("User name ("+ user.getUserName() +") is taken.");
        if(user.getEmail() != null && repository.existsByEmailIgnoreCase(user.getEmail()))
            throw new ResourceAlreadyExist("User email ("+ user.getEmail() +") is taken.");
        if(user.getPhoneNumber() != null && repository.existsByPhoneNumber(user.getPhoneNumber()))
            throw new ResourceAlreadyExist("User phone number ("+ user.getPhoneNumber() +") is taken.");
    }

    

    @Override
    public UserDto login(final UserLoginDto user) {
        return repository.findByUserName(user.getUserName())
                        .map(foundedUser -> {
                            if(!encoder.matches(user.getPassword(), foundedUser.getPassword()))
                                throw new InvalidCredentials("Invalid user password.");
                            return modelMapper.map(foundedUser, UserDto.class);
                        })
                        .orElseThrow(() -> new ResourceNotFound("User name not found."));
    }

    @Override
    public UserDto update(final Long identifier, final UserSignUpDto userToUpdate) {
        validCredentials(userToUpdate);
        return repository.findById(identifier).map(founded -> {
            if(!founded.getPassword().equals(userToUpdate.getPassword()))
                throw new InvalidCredentials("Invalid user password.");
            Optional.ofNullable(userToUpdate.getUserName()).ifPresent(founded::setUserName);
            Optional.ofNullable(userToUpdate.getEmail()).ifPresent(founded::setEmail);
            Optional.ofNullable(userToUpdate.getPhoneNumber()).ifPresent(founded::setPassword);
            Optional.ofNullable(userToUpdate.getPassword()).ifPresent(founded::setPassword);
            return modelMapper.map(repository.save(founded), UserDto.class);
        }).orElseThrow(() -> new ResourceNotFound("User name not found."));
    }

    @Override
    public String delete(Long id) {
        repository.deleteById(id);
        return "user with id:" + id + " deleted successfully.";
    }


    @Override
    public List<UserDto> search(String attributeValue) {
        return Arrays.asList(modelMapper.map(repository.findByUserNameLike(attributeValue), UserDto[].class));
    }
    
    
}
