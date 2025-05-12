package com.example.Tutorials.Service;

import com.example.Tutorials.Entity.Comments;
import com.example.Tutorials.Entity.Tutorials;
import com.example.Tutorials.Repository.CommentsRepository;
import com.example.Tutorials.Repository.TutorialsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class TutorialsService {
    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public List<Tutorials> getAllTutorials(){
        return tutorialsRepository.findAll();
    }

    public Optional <Tutorials> getTutorialsById(Long id){
        return tutorialsRepository.findById(id);
    }

    public Tutorials CreateTutorials(Tutorials tutorials){
        return tutorialsRepository.save(tutorials);
    }

    public void DeleteTotorials(Long id){
        if (tutorialsRepository.existsById(id)){
            tutorialsRepository.deleteById(id);
        }else{
            throw new RuntimeException("Toto not find!");
        }
    }

    public Tutorials  UpdateTutorials(Long id, Tutorials updatedTutorials){
        Optional <Tutorials> optionalTutorials=tutorialsRepository.findById(id);

        if (optionalTutorials.isPresent()){
            Tutorials existingTutorials= optionalTutorials.get();
            existingTutorials.setTitle(updatedTutorials.getTitle());
            existingTutorials.setDescription(updatedTutorials.getDescription());
            existingTutorials.setPublished(updatedTutorials.getPublished());

            return tutorialsRepository.save(existingTutorials);
        }else {
            throw new RuntimeException("tuto not found!");
        }

    }

    public List<Comments>getCommentsByTutoId(Long TutoId){
        return tutorialsRepository.findById(TutoId).map(Tutorials::getComments)
                .orElse(Collections.emptyList());
    }


    public Comments addCommentToTutorial(Long tutorialId, Comments comment) {
        Tutorials tutorial = tutorialsRepository.findById(tutorialId)
                .orElseThrow(() -> new RuntimeException("Tutoriel non trouvé avec ID : " + tutorialId));

        comment.setTutorials(tutorial);
        return commentsRepository.save(comment);
    }

    public Comments updateComment(Long tutorialId, Long commentId, Comments updatedComment) {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getTutorials().getId().equals(tutorialId)) {
            throw new RuntimeException("This comment does not belong to the specified tutorial");
        }

        comment.setContent(updatedComment.getContent());
        return commentsRepository.save(comment);
    }

    public void deleteComment(Long tutorialId, Long commentId) {
        Comments comment = commentsRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getTutorials().getId().equals(tutorialId)) {
            throw new RuntimeException("This comment does not belong to the specified tutorial");
        }

        commentsRepository.delete(comment);
    }
}
