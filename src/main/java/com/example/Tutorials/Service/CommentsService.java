package com.example.Tutorials.Service;

import com.example.Tutorials.Entity.Comments;
import com.example.Tutorials.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    public List <Comments> getAllComments(){
        return commentsRepository.findAll();
    }

    public Optional <Comments> getCommentsById(Long id){
        return commentsRepository.findById(id);
    }

    public Comments CreateComments(Comments comments){
        return commentsRepository.save(comments);
    }

    public void DeleteComments(Long id){
        if (commentsRepository.existsById(id)){
            commentsRepository.deleteById(id);
        }else {
            throw new RuntimeException("comments not found");
        }
    }

    public Comments UpdateComments(Long id, Comments updatedComments){
        Optional <Comments> optionalComments=commentsRepository.findById(id);
        if (optionalComments.isPresent()){
            Comments existingComments=optionalComments.get();
            existingComments.setContent(updatedComments.getContent());

            return commentsRepository.save(existingComments);
        }else{
            throw new RuntimeException("Comment not found!");
        }
    }




}
