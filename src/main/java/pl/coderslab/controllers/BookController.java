package pl.coderslab.controllers;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.BookService;

import java.util.List;

@RestController // dodaje do każdej akcji @ResponseBody
@RequestMapping("/books")
public class BookController {
    private BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book("9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public List<Book> getList() {
        return bookService.getBooks();
    }

    @PostMapping("")
    public void addBook(@RequestBody Book book) {
        bookService.addBook(book);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable Long id) {
        return this.bookService.getBook(id);
    }

    @PutMapping("")
    public void updateBook(@RequestBody Book book) {
        bookService.updateBook(book);
    }

    @DeleteMapping("/{id}")
    public void delBook(@PathVariable Long id) {
        bookService.delBook(id);
    }
    /* ============================ TESTY ============================ */

    @GetMapping("/add")
    public void testAddBook() {
        bookService.addBook(new Book("9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"));
    }
    @GetMapping("/del/{id}")
    public void testDelBook(@PathVariable Long id) {
        bookService.delBook(id);
    }

    @GetMapping("/update")
    public void testUpdateBook() {
        Book book = new Book("9788324631761", "Dziady", "Mickiewicz", "Helion", "Dramat");
        book.setId(1);
        bookService.updateBook(book);
    }

    /* ============================ TESTY ============================ */


}
