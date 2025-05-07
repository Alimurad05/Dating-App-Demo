package az.turing.tinderapp.service;

import az.turing.tinderapp.dto.UserDto;
import az.turing.tinderapp.entity.User;
import az.turing.tinderapp.mapper.UserMapper;
import az.turing.tinderapp.repo.UserRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;
@Service
@RequiredArgsConstructor
public class  UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    public List<UserDto> getAllUsers(){
        List<User> users = userRepo.findAll();
        return users.stream().map(userMapper::toDto).collect(Collectors.toList());
    }
    public UserDto saveUser(UserDto userDto){
        User user= new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        User savedUser = userRepo.save(user);
        return userMapper.toDto(savedUser);
    }
    public UserDto getUserById(Long id){
        return userRepo.findById(id).map(userMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }
    public void deleteUserById(Long id){
        userRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        userRepo.deleteById(id);
    }

}
