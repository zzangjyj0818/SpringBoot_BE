package com.example.basicSpringBoot.api;

import com.example.basicSpringBoot.dto.CommentDto;
import com.example.basicSpringBoot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;

    // CREATE

    // READ
    @GetMapping("/api/articles/{articleId}/comments")
    public ResponseEntity<List<CommentDto>> comments(@PathVariable Long articleId){
        List<CommentDto> dto = commentService.comments(articleId);
        return ResponseEntity.ok(dto);
    }
    // UPDATE
    // DELETE
}
