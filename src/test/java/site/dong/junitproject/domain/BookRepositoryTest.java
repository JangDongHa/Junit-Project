package site.dong.junitproject.domain;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import site.dong.junitproject.web.dto.BookSaveRequestDto;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩 (= 컨트롤러와 서비스는 메모리에 뜨지 않음)
public class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    private final String BOOK_INIT_SQL = "classpath:db/tableInit.sql";

    @BeforeEach // 각 테스트 실행 전에 실행
    public void settingData(){
        String title = "JUnit";
        String author = "GetInThere";
        Book book = Book.builder()
                .title(title)
                .author(author)
                .build(); // 컨트롤러와 서비스가 메모리에 뜨지 않으므로 직접 줘야함

        bookRepository.save(book);
    } // Test 와 다르게 Transaction 이 종료되지 않음


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
    // 하나의 Test 는 하나의 트랜잭션
    // 따라서 create 나 H2 기준으로는 연속적인 Test 메서드 실행 시 책 목록 보기 등과 같은 기능을 테스트하는데에 문제가 있을 수 있음
    // 그런 경우를 대비하여 트랜잭션이 종료되지 않게 할 수 있음 (BeforeEach)


    // 2. 책 목록 보기
    @Test
    public void selectBooksTest(){
        // Given = 받는 데이터가 없는 경우에는 아무것도 적지 않아도 됨
        String title = "JUnit";
        String author = "GetInThere";

        // When (테스트 실행)

        // When
        List<Book> books = bookRepository.findAll();
        System.out.println(books.size());
        // Then
        assertEquals(title, books.get(0).getTitle());
        assertEquals(author, books.get(0).getAuthor());
    }

    // 3. 책 1건 보기
    @Sql(BOOK_INIT_SQL)
    @Test
    public void selectBookTest(){
        // Given
        String title = "JUnit";
        String author = "GetInThere";
        // When
        Book bookPS = bookRepository.findById(1L).orElseThrow(); // Q : 0L 을 적었을 때 오류를 확인하고 해당 오류를 잡아가는 과정을 진행하도록 함

        // Then
        assertEquals(title, bookPS.getTitle());
        assertEquals(author, bookPS.getAuthor());

    }


    // 4. 책 삭제

    @Sql(BOOK_INIT_SQL)
    @Test
    public void deleteBook(){
        // given
        long id = 1L;
        String title = "JUnit";
        String author = "GetInThere";

        // when
        bookRepository.delete(bookRepository.findById(id).orElseThrow());
        // bookRepository.deleteById(id) 와 같은 문구

        // then
        assertFalse(bookRepository.findById(id).isPresent());
    }

    // 5. 책 수정

    @Sql(BOOK_INIT_SQL)
    @Test
    public void updateBook(){
        // Given
        long id = 1L;
        String title = "Dong";
        String author = "장동하";
        Book book = Book.builder()
                .id(id)
                .title(title)
                .author(author)
                .build();
        // When


        bookRepository.findAll().forEach(
                b -> {
                    System.out.println(b.getId());
                    System.out.println(b.getTitle());
                    System.out.println(b.getAuthor());
                }
        );

        Book bookPS = bookRepository.save(book);
        // Then
        assertEquals(book.getId(), bookPS.getId());
        assertEquals(book.getTitle(), bookPS.getTitle());
        assertEquals(book.getAuthor(), bookPS.getAuthor());
    }
}
