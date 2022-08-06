package site.dong.junitproject.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import site.dong.junitproject.domain.Book;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookSaveRequestDto {
    private String title;
    private String author;

    public Book toEntity(){
        return Book.builder()
                .title(this.title)
                .author(this.author)
                .build();
    }
}
