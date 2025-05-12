package com.example.Tutorials.Controller;

import com.example.Tutorials.Entity.Comments;
import com.example.Tutorials.Entity.Tutorials;
import com.example.Tutorials.Service.TutorialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tutorials")
public class TutorialsController {

    @Autowired
    private TutorialsService tutorialsService;

    @GetMapping("/")
    public List <Tutorials> getAllTutorials(){
        return tutorialsService.getAllTutorials();
    }

    @GetMapping("/{id}")
    public Optional <Tutorials> getTutorialsById(@PathVariable Long id){
        return tutorialsService.getTutorialsById(id);
    }

    @PostMapping
    public Tutorials CreateTutorials(@RequestBody Tutorials tutorials){
        return tutorialsService.CreateTutorials(tutorials);
    }

    @DeleteMapping("/{id}")
    public String DeleteTutorials(@PathVariable Long id){
        tutorialsService.DeleteTotorials(id);
        return "Tutorial deleted!";
    }

    @PutMapping("/{id}")
    public Tutorials UpdateTutorials(@PathVariable Long id, @RequestBody Tutorials updatedTutorials){
        return tutorialsService.UpdateTutorials(id, updatedTutorials);
    }

    @GetMapping("/{id}/comments")
    public List<Comments> getCommentsByTutoId(@PathVariable Long id){
        return tutorialsService.getCommentsByTutoId(id);
    }

    @PostMapping("/{id}/comments")
    public Comments addComment(@PathVariable Long id, @RequestBody Comments comment) {
        return tutorialsService.addCommentToTutorial(id, comment);
    }

    @PutMapping("/{tutorialId}/comments/{commentId}")
    public Comments updateComment(@PathVariable Long tutorialId,
                                  @PathVariable Long commentId,
                                  @RequestBody Comments updatedComment) {
        return tutorialsService.updateComment(tutorialId, commentId, updatedComment);
    }

    @DeleteMapping("/{tutorialId}/comments/{commentId}")
    public String deleteComment(@PathVariable Long tutorialId,
                                @PathVariable Long commentId) {
        tutorialsService.deleteComment(tutorialId, commentId);
        return "Comment deleted successfully!";
    }

}
