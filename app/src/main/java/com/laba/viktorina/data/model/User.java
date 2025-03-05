package com.laba.viktorina.data.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "users")
public class User implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private Long id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "score")
    private Integer score;
    @ColumnInfo(name = "difficulty")
    private DifficultyLevel difficulty;

    public User(String name, Integer score, DifficultyLevel difficulty) {
        this.name = name;
        this.score = score;
        this.difficulty = difficulty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public DifficultyLevel getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(DifficultyLevel difficulty) {
        this.difficulty = difficulty;
    }
}
