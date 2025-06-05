package com.example.smartchat.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String text;

    @Column(columnDefinition = "TEXT") // 長めのテキストを扱えるように
    private String responseText;

    private LocalDateTime createdAt = LocalDateTime.now();

    // 👇これをクラスの中に！
    public String getText() {
        return this.text;
    }
}
