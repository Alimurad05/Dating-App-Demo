package az.turing.tinderapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
@Builder
@Data
public class Like {
    @Id
    @GeneratedValue
    private Long id;
    private Long userid;
    private Long likedUserId;
    private Boolean isLiked;
}
