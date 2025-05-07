package az.turing.tinderapp.mapper;

import az.turing.tinderapp.dto.LikeDto;
import az.turing.tinderapp.entity.Like;
import org.springframework.stereotype.Service;

@Service
public class LikeMapper implements EntityMapper<Like, LikeDto>{
    @Override
    public Like toEntity(LikeDto likedto) {
        return Like.builder()
                .userid(likedto.getUserId())
                .likedUserId(likedto.getLikedUserId())
                .isLiked(likedto.getLiked())
                .build();
    }

    @Override
    public LikeDto toDto(Like like) {
        return LikeDto.builder()
                .userId(like.getUserid())
                .likedUserId(like.getLikedUserId())
                .liked(like.getIsLiked())
                .build();
    }
}
