package com.example.Tutorials.Controller;

import com.example.Tutorials.Entity.Comments;
import com.example.Tutorials.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @GetMapping("/")
    public List<Comments> getAllComments(){
        return commentsService.getAllComments();
    }

    @GetMapping("/{id}")
    public Optional<Comments> getCommentsById(@PathVariable Long id){
        return commentsService.getCommentsById(id);
    }

    @PostMapping
    public Comments CreateComments(@RequestBody Comments comments){
        return  commentsService.CreateComments(comments);
    }

    @DeleteMapping("/{id}")
    public String DeleteComment(@PathVariable Long id){
        commentsService.DeleteComments(id);
        return "Comment deleted";
    }

    @PutMapping("/{id}")
    public Comments UpdateComments(@PathVariable Long id, @RequestBody Comments updatedComment){
        return commentsService.UpdateComments(id, updatedComment);
    }

}
