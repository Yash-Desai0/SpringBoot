package com.example.book.controller;

import com.example.book.entity.Book;
import com.example.book.Repository.BookRepository;
import com.example.book.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","id",id));
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PostMapping("/upload")
    public void checkFileUpload(@RequestParam("file") MultipartFile file) throws IOException
    {
        String fileName = file.getOriginalFilename();
        String filePath = "src/main/resources/uploads/"+fileName;

        File newFile = new File(filePath);
        newFile.getParentFile().mkdirs();
        FileOutputStream fos = new FileOutputStream(newFile);
        fos.write(file.getBytes());
        fos.close();

        String message = "File '"+ fileName + "' uploaded at '"+filePath+"' successfully";
        System.out.println(message);
    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book", "id", id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setYear(bookDetails.getYear());

        return bookRepository.save(book);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        Book book = bookRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Book","id",id));
        bookRepository.delete(book);
        return ResponseEntity.ok().build();
    }

}

