package az.turing.tinderapp.repo;

import az.turing.tinderapp.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepo extends JpaRepository<Like,Long> {
}
