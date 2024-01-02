package ma.youcode.candlelight.services.implementations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import ma.youcode.candlelight.exceptions.InvalidCredentials;
import ma.youcode.candlelight.exceptions.ResourceAlreadyExist;
import ma.youcode.candlelight.exceptions.ResourceNotFound;
import ma.youcode.candlelight.models.documents.User;
import ma.youcode.candlelight.models.dto.user.UserDto;
import ma.youcode.candlelight.models.dto.user.UserLoginDto;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.repositories.UserRepository;
import ma.youcode.candlelight.services.SequenceGeneratorService;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private UserRepository repository;

    @Mock
    private SequenceGeneratorService sequenceGenerator;
    
    @InjectMocks
    private UserServiceImpl underTest;

    private UserSignUpDto userSignUpDto;
    private UserLoginDto userLoginDto;
    private User user; 
    private UserDto userDto;


    @BeforeEach
    void setUp() {
        userSignUpDto = UserSignUpDto.builder()
                                        .userName("test")
                                        .phoneNumber("0707271102")
                                        .email("email@example.com")
                                        .password("password")
                                        .build();
        userLoginDto = UserLoginDto.builder()
                                        .userName("test")
                                        .password("password")
                                        .build();
        user = User.builder()
                    .id(1L)
                    .userName("test")
                    .phoneNumber("0707271102")
                    .email("email@example.com")
                    .password("password")
                    .build();
        userDto = UserDto.builder()
                            .userName("test")
                            .phoneNumber("0707271102")
                            .email("email@example.com")
                            .build();
        }

    @Test
    void createMethodShouldThrowAnExceptionWhenUserNameIsAlreadyTaken() {
        when(repository.existsByUserName(userSignUpDto.getUserName())).thenReturn(true);
        
        assertThrows(ResourceAlreadyExist.class, () -> underTest.create(userSignUpDto));
        
        verify(repository).existsByUserName(userSignUpDto.getUserName());
    }

    @Test
    void createMethodShouldThrowAnExceptionWhenEmailIsAlreadyTaken() {
        when(repository.existsByUserName(userSignUpDto.getUserName())).thenReturn(false);
        when(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail())).thenReturn(true);
        
        assertThatExceptionOfType(ResourceAlreadyExist.class)
        .isThrownBy(() -> underTest.create(userSignUpDto));
        
        verify(repository).existsByUserName(userSignUpDto.getUserName());
        verify(repository).existsByEmailIgnoreCase(userSignUpDto.getEmail());
    }

    @Test
    void createMethodShouldThrowAnExceptionWhenPhoneNumberIsAlreadyTaken() {
        when(repository.existsByUserName(userSignUpDto.getUserName())).thenReturn(false);
        when(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail())).thenReturn(false);
        when(repository.existsByPhoneNumber(userSignUpDto.getPhoneNumber())).thenReturn(true);

        assertThatExceptionOfType(ResourceAlreadyExist.class)
        .isThrownBy(() -> underTest.create(userSignUpDto));

        verify(repository).existsByUserName(userSignUpDto.getUserName());
        verify(repository).existsByEmailIgnoreCase(userSignUpDto.getEmail());
        verify(repository).existsByPhoneNumber(userSignUpDto.getPhoneNumber());
    }

    @Test
    void createMethodShouldReturnTheUserDtoWhenSuccess() {
        when(repository.existsByUserName(userSignUpDto.getUserName())).thenReturn(false);
        when(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail())).thenReturn(false);
        when(repository.existsByPhoneNumber(userSignUpDto.getPhoneNumber())).thenReturn(false);
        when(modelMapper.map(userSignUpDto, User.class)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        mockStatic(User.class);
        when(User.getSequenceName()).thenReturn("user_seq");
        when(sequenceGenerator.generateSequence("user_seq")).thenReturn(1L);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        
        assertThat(underTest.create(userSignUpDto)).isEqualTo(userDto);

        verify(repository).existsByUserName(userSignUpDto.getUserName());
        verify(repository).existsByEmailIgnoreCase(userSignUpDto.getEmail());
        verify(repository).existsByPhoneNumber(userSignUpDto.getPhoneNumber());
        verify(modelMapper).map(userSignUpDto, User.class);
        verify(repository).save(user);
        verify(modelMapper).map(user, UserDto.class);
    }

    @Test
    void loginMethodShouldThrowAnExceptionWhenUserNameIsNotFound() {
        when(repository.findByUserName(userLoginDto.getUserName())).thenReturn(Optional.empty());

        assertThatExceptionOfType(ResourceNotFound.class)
        .isThrownBy(() -> underTest.login(userLoginDto));

        verify(repository).findByUserName(userLoginDto.getUserName());
    }

    @Test
    void loginMethodShouldThrowAnExceptionWhenUserPasswordIsIncorrect() {
        userLoginDto.setPassword("Incorrect password");
        when(repository.findByUserName(userLoginDto.getUserName())).thenReturn(Optional.of(user));
        
        assertThatExceptionOfType(InvalidCredentials.class)
        .isThrownBy(() -> underTest.login(userLoginDto));

        verify(repository).findByUserName(userLoginDto.getUserName());
    }

    @Test
    void loginMethodShouldReturnTheUserDtoWhenSuccess() {
        when(repository.findByUserName(userLoginDto.getUserName())).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        assertThat(underTest.login(userLoginDto)).isEqualTo(userDto);

        verify(repository).findByUserName(userLoginDto.getUserName());
        verify(modelMapper).map(user, UserDto.class);
    }

    @Test
    void updateMethodShouldThrowAnExceptionWhenUserNameIsNotExist() {

        when(repository.findById(1L)).thenReturn(Optional.empty());
        
        assertThatExceptionOfType(ResourceNotFound.class)
        .isThrownBy(() -> underTest.update(1L, userSignUpDto));
        
        verify(repository).findById(1L);
    }

    @Test
    void updateMethodShouldThrowAnExceptionWhenPasswordIsIncorrect() {
        userSignUpDto.setPassword("incorrect");
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        assertThatExceptionOfType(InvalidCredentials.class)
        .isThrownBy(() -> underTest.update(1L, userSignUpDto));
        
        verify(repository).findById(1L);
    }

    @Test
    void updateMethodShouldReturnTheUserDtoWhenSuccess() {

        user.setPassword("password");
        userSignUpDto.setPassword("password");
        when(repository.findById(1L)).thenReturn(Optional.of(user));

        when(modelMapper.map(userSignUpDto, User.class)).thenReturn(user);
        when(repository.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);
        
        assertThat(underTest.update(1L ,userSignUpDto)).isEqualTo(userDto);
        
        verify(repository).findById(1L);
        verify(modelMapper).map(userSignUpDto, User.class);
        verify(repository).save(user);
        verify(modelMapper).map(user, UserDto.class);
    }
    
}
