package com.example.lab5q2.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Project {

    private String id;
    private String title;
    private String description;
    private boolean status;
    private String companyName;
}
