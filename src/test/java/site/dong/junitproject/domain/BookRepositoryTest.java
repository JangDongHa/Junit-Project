package site.dong.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import site.dong.junitproject.web.dto.BookSaveRequestDto;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩 (= 컨트롤러와 서비스는 메모리에 뜨지 않음)
public class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    // 1. 책 등록
    @Test
    public void bookSaveTest(){
        // Given (데이터 준비)
        String title = "JUnit5";
        String author = "장동하";
        Book book = Book.builder().title(title).author(author).build(); // 컨트롤러와 서비스가 메모리에 뜨지 않으므로 직접 줘야함

        // When (테스트 실행)
        Book bookPS = bookRepository.save(book); // ps = persistence

        // then (검증)
        assertEquals(title, bookPS.getTitle()); // assertEquals(기대값, 설정값)
        assertEquals(author, bookPS.getAuthor());
    }

    // 2. 책 목록 보기


    // 3. 책 1건 보기


    // 4. 책 수정


    // 5. 책 삭제
}
