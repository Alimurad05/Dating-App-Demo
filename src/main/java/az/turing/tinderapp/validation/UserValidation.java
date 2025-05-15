package az.turing.tinderapp.validation;

import az.turing.tinderapp.dto.UserDto;
import az.turing.tinderapp.entity.User;
import az.turing.tinderapp.exception.ArgumentException;
import az.turing.tinderapp.exception.UserAlreadyExistException;
import az.turing.tinderapp.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserValidation {
    private  final UserRepo userRepo;
    public void validate(UserDto userDto) {

        if (userDto.getEmail() == null || !userDto.getEmail().endsWith("@gmail.com")) {
            throw new ArgumentException("Email must end with @gmail.com");
        }
        if (userDto.getPassword() == null || userDto.getPassword().length() < 6) {
            throw new ArgumentException("Password must be at least 6 characters");
        }
        if (userDto.getUsername().isEmpty()) {
            throw new ArgumentException("Username is required");
        }
        List<User> users = userRepo.findAll();
        for(User user:users){
            if(user.getUsername().equals(userDto.getUsername()) || user.getEmail().equals(userDto.getEmail())){
                throw new UserAlreadyExistException("Username or gmail is already taken");
            }
        }

    }
}
