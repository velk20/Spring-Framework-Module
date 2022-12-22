package bg.softuni.books.service;

import bg.softuni.books.model.dto.BookDTO;
import bg.softuni.books.model.entity.AuthorEntity;
import bg.softuni.books.model.entity.BookEntity;
import bg.softuni.books.repository.AuthorRepository;
import bg.softuni.books.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper mapper;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, ModelMapper mapper) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.mapper = mapper;
    }

    public Optional<BookDTO> getBookById(Long bookId) {
        return bookRepository
                .findById(bookId)
                .map(this::map);
    }

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Transactional
    @Transient
    public Long createBook(BookDTO bookDTO) {
        BookEntity bookEntity = mapper.map(bookDTO, BookEntity.class);
        AuthorEntity authorEntity = authorRepository.save(new AuthorEntity().setName(bookDTO.getAuthor().getName()).setBooks(new ArrayList<>()));

        BookEntity save = bookRepository.save(bookEntity.setAuthor(authorEntity));

        return save.getId();
    }
    public void deleteBookById(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    private BookDTO map(BookEntity book) {
        BookDTO bookDTO = mapper.map(book, BookDTO.class);
        return bookDTO.setAuthor(bookDTO.getAuthor());
    }

}
