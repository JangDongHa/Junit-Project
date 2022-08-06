package site.dong.junitproject.web.dto;

import lombok.*;
import site.dong.junitproject.domain.Book;

import javax.persistence.Column;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookResponseDto {
    private long id;

    private String title;
    private String author;

    public static BookResponseDto toDto(Book bookPS){
        return BookResponseDto.builder()
                .id(bookPS.getId())
                .author(bookPS.getAuthor())
                .title(bookPS.getTitle())
                .build();
    }
}
