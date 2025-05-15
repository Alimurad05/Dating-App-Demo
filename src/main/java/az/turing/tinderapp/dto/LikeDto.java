package az.turing.tinderapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LikeDto {
    private Long userId;
    private Long likedUserId;
    private Boolean liked;

}