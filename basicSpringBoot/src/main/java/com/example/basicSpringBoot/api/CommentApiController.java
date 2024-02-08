package com.example.basicSpringBoot.api;

import com.example.basicSpringBoot.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentApiController {
    @Autowired
    private CommentService commentService;
}
