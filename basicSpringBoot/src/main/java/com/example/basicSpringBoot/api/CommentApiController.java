package com.example.basicSpringBoot.api;

import com.example.basicSpringBoot.dto.CommentDto;
import com.example.basicSpringBoot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // CREATE
    @PostMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<CommentDto> create(@PathVariable Long articleId, @RequestBody CommentDto dto){
        CommentDto createDto = commentService.create(articleId, dto);
        return ResponseEntity.ok(createDto);
    }

    // READ
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dto = commentService.comments(articleId);
        return ResponseEntity.ok(dto);
    }
    // UPDATE
    @PatchMapping("/api/comments/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentDto dto){
        CommentDto updatedDto = commentService.update(id, dto);
        return ResponseEntity.ok(updatedDto);
    }
    // DELETE
}
