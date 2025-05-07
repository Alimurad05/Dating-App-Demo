package az.turing.tinderapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;


import az.turing.tinderapp.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {


}
