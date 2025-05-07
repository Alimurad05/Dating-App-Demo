package az.turing.tinderapp.controller;

import az.turing.tinderapp.dto.LikeDto;
import az.turing.tinderapp.service.LikeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{likeId}")
    public ResponseEntity<LikeDto> getLikeById(@PathVariable Long likeId) {
        LikeDto likeDto = likeService.getLikeById(likeId);
        return ResponseEntity.ok(likeDto);
    }
    @PostMapping
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto likedto){
        return ResponseEntity.ok(likeService.saveLike(likedto));
    }
    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLikeById(likeId);
        return ResponseEntity.noContent().build();
    }
    @PutMapping(value = "/{likeId}")
    public ResponseEntity<LikeDto> updateLike(@RequestBody LikeDto likedto){
        return ResponseEntity.ok(likeService.saveLike(likedto));
    }
    @GetMapping
    public ResponseEntity<List<LikeDto>> getAllLikes() {
        List<LikeDto> likeDtos = likeService.getAllLikes();
        return ResponseEntity.ok(likeDtos);
    }

}