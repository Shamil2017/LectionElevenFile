package com.example.lectionelevenfile;

import androidx.annotation.NonNull;

public class StudMark {
    private String name;
    private String subject;
    private int grade;

    public StudMark(String name, String subject, int grade) {
        this.name = name;
        this.subject = subject;
        this.grade = grade;
    }

    @NonNull
    @Override
    public String toString() {
        return name + " получил(а) по "+ subject + " оценку "+grade;
    }
}
