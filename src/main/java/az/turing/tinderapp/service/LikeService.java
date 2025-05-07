package az.turing.tinderapp.service;

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

    public List<LikeDto> getAllLikes() {
        List<Like> likes = likeRepo.findAll();
        return likes.stream().map(likeMapper::toDto).collect(Collectors.toList());

    }

    public LikeDto saveLike(LikeDto likeDto) {
        Like like = new Like();
        like.setUserid(likeDto.getUserId());
        like.setLikedUserId(likeDto.getLikedUserId());
        like.setIsLiked(likeDto.getLiked());
        Like savedLike = likeRepo.save(like);
        return likeMapper.toDto(savedLike);
    }

    public LikeDto getLikeById(Long id) {
        return likeRepo.findById(id).map(likeMapper::toDto)
                .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteLikeById(Long id) {
        likeRepo.findById(id).orElseThrow(EntityNotFoundException::new);
        likeRepo.deleteById(id);
    }
}
