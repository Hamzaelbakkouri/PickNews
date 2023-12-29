package ma.youcode.candlelight.services.implementations;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import ma.youcode.candlelight.exceptions.ResourceAlreadyExist;
import ma.youcode.candlelight.models.dto.user.UserSignUpDto;
import ma.youcode.candlelight.repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    
    @Mock
    private ModelMapper modelMapper;
    
    @Mock
    private UserRepository repository;
    
    @InjectMocks
    private UserServiceImpl underTest;


    private UserSignUpDto userSignUpDto;

    @BeforeEach
    void setUp() {
        userSignUpDto = UserSignUpDto.builder()
                                            .userName("test")
                                            .phoneNumber("0707271102")
                                            .email("email@example.com")
                                            .build();
    }

    @Test
    void createMethodShouldThrowAnExceptionWhenUserNameIsAlreadyTaken() {
        when(repository.existsById(userSignUpDto.getUserName())).thenReturn(true);
        
        assertThrows(ResourceAlreadyExist.class, () -> underTest.create(userSignUpDto));
        
        verify(repository.existsById(userSignUpDto.getUserName()));
    }

    @Test
    void createMethodShouldThrowAnExceptionWhenEmailIsAlreadyTaken() {
        when(repository.existsById(userSignUpDto.getUserName())).thenReturn(false);
        when(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail())).thenReturn(true);
        
        assertThatExceptionOfType(ResourceAlreadyExist.class)
        .isThrownBy(() -> underTest.create(userSignUpDto));
        
        verify(repository.existsById(userSignUpDto.getUserName()));
        verify(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail()));
    }

    @Test
    void createMethodShouldThrowAnExceptionWhenPhoneNumberIsAlreadyTaken() {
        when(repository.existsById(userSignUpDto.getUserName())).thenReturn(false);
        when(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail())).thenReturn(false);
        when(repository.existsByPhoneNumber(userSignUpDto.getPhoneNumber())).thenReturn(true);

        assertThatExceptionOfType(ResourceAlreadyExist.class)
        .isThrownBy(() -> underTest.create(userSignUpDto));

        verify(repository.existsById(userSignUpDto.getUserName()));
        verify(repository.existsByEmailIgnoreCase(userSignUpDto.getEmail()));
        verify(repository.existsByPhoneNumber(userSignUpDto.getPhoneNumber()));
    }
    
}
