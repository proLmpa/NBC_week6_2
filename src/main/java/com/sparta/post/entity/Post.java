package com.sparta.post.entity;

import com.sparta.post.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 수 있는 Entity 클래스 지정
@Getter
@Setter
@Table(name = "post") // 매핑할 테이블의 이름을 지정
@NoArgsConstructor
public class Post extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "contents", nullable = false)
    private String contents;

    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }

    public void update(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.author = requestDto.getAuthor();
        this.password = requestDto.getPassword();
        this.contents = requestDto.getContents();
    }
}
