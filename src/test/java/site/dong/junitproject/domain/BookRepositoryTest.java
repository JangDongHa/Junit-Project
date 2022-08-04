package site.dong.junitproject.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // DB와 관련된 컴포넌트만 메모리에 로딩 (= 컨트롤러와 서비스는 메모리에 뜨지 않음)
public class BookRepositoryTest {

    @Autowired // DI
    private BookRepository bookRepository;

    // 1. 책 등록
    @Test
    public void bookSaveTest(){
        System.out.println("ㅎㅇㅎㅇ");
    }

    // 2. 책 목록 보기


    // 3. 책 1건 보기


    // 4. 책 수정


    // 5. 책 삭제
}
