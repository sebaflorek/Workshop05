package pl.coderslab.entity;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MockBookService implements BookService {

    private List<Book> books;

    public MockBookService() {
        books = new ArrayList<>();
        books.add(new Book("9788324631766", "Thiniking	in	Java", "Bruce	Eckel", "Helion", "programming"));
        books.add(new Book("9788324627738", "Rusz	glowa	Java.", "Sierra	Kathy,	Bates	Bert", "Helion", "programming"));
        books.add(new Book("9780130819338", "Java	2.	Podstawy", "Cay	Horstmann,	Gary	Cornell", "Helion", "programming"));
    }

    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public Book getBook(Long id) {
        // Optional<Book> book = books.stream().filter(b -> b.getId().equals(id)).findFirst();
        if (isBookPresent(id)) {
            return books.stream().filter(b -> b.getId().equals(id)).collect(Collectors.toList()).get(0);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @Override
    public void updateBook(Book book) {
        if (isBookPresent(book.getId())) {
            int bookIndex = books.indexOf(getBook(book.getId()));
            books.set(bookIndex, book);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    @Override
    public void delBook(Long id) {
        if (isBookPresent(id)) {
            books.removeIf(book -> book.getId().equals(id));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
        }
    }

    private boolean isBookPresent(Long id) {
        return books.stream().anyMatch(b -> b.getId().equals(id));
    }
}
