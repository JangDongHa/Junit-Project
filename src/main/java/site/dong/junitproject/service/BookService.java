package site.dong.junitproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.dong.junitproject.domain.Book;
import site.dong.junitproject.domain.BookRepository;
import site.dong.junitproject.web.dto.BookResponseDto;
import site.dong.junitproject.web.dto.BookSaveRequestDto;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class BookService {
    private final BookRepository bookRepository;
    // 책 등록
    @Transactional(rollbackFor = RuntimeException.class) // runtimeException 이 발생하면 Rollback 하겠다는 것
    public BookResponseDto saveBook(BookSaveRequestDto bookSaveRequestDto){
        Book book = bookSaveRequestDto.toEntity();
        Book bookPS = bookRepository.save(book);
        // 영속화된 객체를 open-in-view: true, 즉 지연로딩 상태에서 컨트롤러로 보내게 되면 (기본적으로는 서비스->컨트롤러로 가기 전에 종료됨)
        // 이런 경우 여러가지 문제가 발생할 수 있으므로 ResponseDto 를 따로 만들어서 보내줘야한다.
        return BookResponseDto.toDto(bookPS);
    }
    // 책 목록 보기
    public List<BookResponseDto> selectBooks(){

        return bookRepository.findAll().stream() // stream 변환 시 object 타입으로 갯수만큼 나눠짐 (stream 쓸 때 원래는 stream -> filter -> map -> collect 로 주로 사용)
                .map(BookResponseDto::toDto).toList(); // map : 다른 타입으로 mapping
    }


    // 책 1건 보기
    // 책 삭제
    // 책 수정
}
