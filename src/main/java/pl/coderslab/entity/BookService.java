package pl.coderslab.entity;

import java.util.List;

public interface BookService {

    List<Book> getBooks();
    void addBook(Book book);
    Book getBook(Long id);
    void updateBook(Book id);
    void delBook(Long id);

}
