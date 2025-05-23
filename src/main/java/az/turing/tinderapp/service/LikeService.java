package az.turing.tinderapp.service;

import az.turing.tinderapp.entity.User;
import az.turing.tinderapp.exception.NotFoundException;
import az.turing.tinderapp.repo.UserRepo;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


import az.turing.tinderapp.dto.LikeDto;
import az.turing.tinderapp.entity.Like;
import az.turing.tinderapp.mapper.LikeMapper;
import az.turing.tinderapp.repo.LikeRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepo likeRepo;
    private final LikeMapper likeMapper;
    private final UserRepo userRepo;

    public List<LikeDto> getAllLikes() {
        List<Like> likes = likeRepo.findAll();
        return likes.stream().map(likeMapper::toDto).collect(Collectors.toList());
    }

    public LikeDto saveLike(LikeDto likeDto) {
        User sender = userRepo.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("Sender not found"));
        User receiver = userRepo.findById(likeDto.getLikedUserId())
                .orElseThrow(() -> new NotFoundException("Receiver not found"));
        Like like = new Like();
        like.setUserid(sender.getId());
        like.setLikedUserId(receiver.getId());
        like.setIsLiked(likeDto.getLiked());
        Like savedLike = likeRepo.save(like);
        return likeMapper.toDto(savedLike);
    }

    public LikeDto getLikeById(Long id) {
        return likeRepo.findById(id).map(likeMapper::toDto)
                .orElseThrow(() -> new NotFoundException("Like has not found"));
    }

    public void deleteLikeById(Long id) {
        likeRepo.findById(id).orElseThrow(() -> new NotFoundException("Like has not found"));
        likeRepo.deleteById(id);
    }

    public LikeDto updateLike(Long id,LikeDto likeDto) {
        Like like = likeRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Like has not found"));
        User sender = userRepo.findById(likeDto.getUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        User receiver = userRepo.findById(likeDto.getLikedUserId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        like.setUserid(sender.getId());
        like.setLikedUserId(receiver.getId());
        like.setIsLiked(likeDto.getLiked());
        Like savedLike = likeRepo.save(like);
        return likeMapper.toDto(savedLike);
    }
}
