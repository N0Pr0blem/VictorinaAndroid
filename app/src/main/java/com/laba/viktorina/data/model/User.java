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
    @ColumnInfo(name = "category")
    private Integer score;

    public User(String name, Integer score) {
        this.name = name;
        this.score = score;
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
}
