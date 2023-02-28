package com.example.client.Entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class BookEntity {
    public Long id;
    public String title;
    public String author;
    public String publishing;
    public String year;
    public String kind;
}
