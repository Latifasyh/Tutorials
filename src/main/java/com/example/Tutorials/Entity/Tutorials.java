package com.example.Tutorials.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Tutorials {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private Boolean published;

    @OneToMany(mappedBy = "tutorials")
    private List <Comments> comments = new ArrayList<>();
    //getter and setter
    public List <Comments> getComments(){
        return comments;
    }
    public void setComments(List <Comments> comments){
        this.comments=comments;
    }

}
