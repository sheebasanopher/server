package com.sheeba.server.controller;

import com.sheeba.server.domain.BookPojo;
import com.sheeba.server.domain.Library;
import com.sheeba.server.domain.StudentPojo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class LibraryRestController {
    private Library library = new Library();

    @PostMapping("/addStudent")
    public String addStudent(@RequestBody StudentPojo studentPojo) {
        boolean addStudent = library.addStudent(studentPojo);
        if (addStudent) {
            return studentPojo.getName() + " success";
        }
        return studentPojo.getName() + " not success";
    }

    @GetMapping("/getStudents")
    public List<StudentPojo> getStudents() {
        return library.getStudentPojoList();
    }

    @GetMapping("/getStudent")
    public StudentPojo getStudent(@RequestParam String stdRollNo) {
        return library.getStudentDetail(stdRollNo);
    }

    @PostMapping("/addBook")
    public String addBook(@RequestBody BookPojo bookPojo) {
        boolean addBook = library.addBook(bookPojo);
        if (addBook) {
            return bookPojo.getName() + " success";
        }
        return bookPojo.getName() + " not success";
    }

    @GetMapping("/getBooks")
    public List<BookPojo> getBooks() {
        return library.getBookPojoList();
    }

    @GetMapping("/getBook")
    public List<BookPojo> getBook(@RequestParam String bookIsbnNo) {
        return library.getBookDetail(bookIsbnNo);

    }

    @GetMapping("/borrowedBook")
    public String borrowedBook(@RequestParam String stdRollNo, @RequestParam String bookId, @RequestParam String bookIsbnNo) {
        return library.borrowBook(stdRollNo, bookId, bookIsbnNo);

    }

    @GetMapping("/returnBook")
    public String returnBook(@RequestParam String stdRollNo1, @RequestParam String bookId, @RequestParam String bookIsbnNo) {
        return library.returnBook(stdRollNo1, bookId, bookIsbnNo);
    }

    @GetMapping("/getBorrowedBook")
    public List<BookPojo> getBorrowedBook() {
        List<BookPojo> books = library.getBookPojoList();
        List<BookPojo> borrowedBooks = new ArrayList<>();
        for (BookPojo book : books) {
            boolean isBorrowed = book.isBorrowed();
            if (isBorrowed) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    @GetMapping("/havingBooks")
    public List<StudentPojo> havingBooks() {
        List<StudentPojo> students = library.getStudentPojoList();
        List<StudentPojo> studentBorrowed = new ArrayList<>();
        for (StudentPojo student : students) {
            int bookId = student.getBookIdList().size();
            if (bookId > 0) {
                studentBorrowed.add(student);
            }
        }
        return studentBorrowed;
    }

    @GetMapping("/notBorrowed")
    public int getNotBorrowedBook(String bookIsbnNo) {
        return library.getNotBorrowed(bookIsbnNo);
    }

    @GetMapping("/getBorrowedStatus")
    public List<String> getBorrowedStatus() {
        return library.getBorrowedBook();
    }

    @GetMapping("getBookDetails")
    public Map<String,Integer> getBookDetails(){
        return library.getBookDetail();
    }
}
