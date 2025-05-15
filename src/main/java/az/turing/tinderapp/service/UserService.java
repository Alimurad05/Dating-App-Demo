package az.turing.tinderapp.service;

import az.turing.tinderapp.dto.UserDto;
import az.turing.tinderapp.entity.User;
import az.turing.tinderapp.exception.NotFoundException;
import az.turing.tinderapp.mapper.UserMapper;
import az.turing.tinderapp.repo.UserRepo;

import az.turing.tinderapp.validation.UserValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
@Service
@RequiredArgsConstructor
public class  UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final UserValidation userValidation;
    public List<UserDto> getAllUsers(){
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
    public UserDto saveUser(UserDto userDto){
        userValidation.validate(userDto);
        User user= new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }
    public UserDto getUserById(Long id){
        return userRepo.findById(id).map(userMapper::toDto)
                .orElseThrow(()->new NotFoundException("User has not found"));
    }
    public void deleteUserById(Long id){
        userRepo.findById(id).orElseThrow(()->new NotFoundException("User has not found"));
        userRepo.deleteById(id);
    }
    public UserDto updateUser(Long id, UserDto userDto){
        userValidation.validate(userDto);
        User user = userRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setUsername(userDto.getUsername());
        user.setPassword(user.getPassword());
        user.setEmail(userDto.getEmail());
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }

}
