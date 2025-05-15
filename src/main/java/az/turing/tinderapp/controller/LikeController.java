package az.turing.tinderapp.controller;

import az.turing.tinderapp.dto.LikeDto;
import az.turing.tinderapp.service.LikeService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @GetMapping("/{likeId}")
    public ResponseEntity<LikeDto> getLikeById(@PathVariable Long likeId) {
        return ResponseEntity.ok(likeService.getLikeById(likeId));
    }
    @PostMapping
    public ResponseEntity<LikeDto> createLike(@RequestBody LikeDto likedto){
        return ResponseEntity.ok(likeService.saveLike(likedto));
    }
    @DeleteMapping("/{likeId}")
    public ResponseEntity<Void> deleteLike(@PathVariable Long likeId) {
        likeService.deleteLikeById(likeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<LikeDto>> getAllLikes() {
        return ResponseEntity.ok(likeService.getAllLikes());
    }
    @PutMapping("/{likeId}")
    public ResponseEntity<LikeDto> updateLike(@PathVariable Long likeId, @RequestBody LikeDto likedto) {
        return ResponseEntity.ok(likeService.updateLike(likeId,likedto));
    }


}