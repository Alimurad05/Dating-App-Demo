package az.turing.tinderapp.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LikeDto {
    private Long userId;
    private Long likedUserId;
    private Boolean liked;

}