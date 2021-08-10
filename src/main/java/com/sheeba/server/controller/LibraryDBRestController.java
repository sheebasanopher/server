package com.sheeba.server.controller;

import com.sheeba.server.domain.LibraryBookPojo;
import com.sheeba.server.domain.LibraryStudentPojo;
import com.sheeba.server.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class LibraryDBRestController {

    private final LibraryService libraryService;

    @Autowired
    public LibraryDBRestController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping("/new/addStudent")
    public String newAddStudent(@RequestBody LibraryStudentPojo libraryStudentPojo) {
        return libraryService.newAddStudent(libraryStudentPojo);
    }

    @GetMapping("/new/getStudents")
    public List<LibraryStudentPojo> getStudents() {
        return libraryService.getStudents();
    }

    @PostMapping("/new/addBook")
    public String newAddBook(@RequestBody LibraryBookPojo libraryBookPojo) {
        return libraryService.newAddBook(libraryBookPojo);
    }

    @GetMapping("/new/getBooks")
    public List<LibraryBookPojo> getBooks() {
        return libraryService.getBooks();
    }

    @GetMapping("/new/getStudent")
    public LibraryStudentPojo getStudent(@RequestParam Integer rollNo) {
        return libraryService.getStudent(rollNo);
    }

    @GetMapping("/new/getStudentByName")
    public LibraryStudentPojo getStudentByName(@RequestParam String name) {
        return libraryService.getStudentByName(name);
    }

    @GetMapping("/new/getStudentNameDepartment")
    public LibraryStudentPojo getNameAndDepartment(@RequestParam String name, @RequestParam String department) {
        return libraryService.getNameAndDepartment(name, department);
    }

    @GetMapping("/new/getBook")
    public LibraryBookPojo getBook(@RequestParam Integer id) {
        return libraryService.getBook(id);
    }

    @GetMapping("/new/getBookDetails")
    public List<LibraryBookPojo> getBookDetails(@RequestParam String bookIsbnNo) {
        return libraryService.getBookDetails(bookIsbnNo);
    }

    @GetMapping("/new/getBookIdAndIsbnNo")
    public LibraryBookPojo getBookIdAndIsbnNo(@RequestParam Integer id, @RequestParam String isbnNo) {
        return libraryService.getBookIdAndIsbnNo(id, isbnNo);
    }

    @GetMapping("/new/borrowed")
    public String borrowed(@RequestParam Integer bookId, @RequestParam String isbnNo, @RequestParam Integer studentRollNo) {
        return libraryService.borrowBook(bookId, isbnNo, studentRollNo);
    }

    @GetMapping("/new/return")
    public String returnBook(@RequestParam Integer bookId, @RequestParam String isbnNo, @RequestParam Integer studentRollNo) {
        return libraryService.returnBook(bookId, isbnNo, studentRollNo);

    }

    @GetMapping("/new/getBorrowedDetails")
    public List<String> getBorrowedDetails() {
        return libraryService.getBorrowedDetails();
    }

    @GetMapping("/new/getTotalBook")
    public Map<String, Integer> getTotalBook() {
        return libraryService.getTotalBook();
    }

    @GetMapping("/new/getStudentsDepartment")
    public Map<String, List<String>> getStudentsDepartment() {
        return libraryService.getStudentsDepartment();
    }

    @GetMapping("/new/studentHavingBook")
    public Map<Integer, List<String>> getStudentHavingBook() {
        return libraryService.getStudentHavingBook();
    }

    @GetMapping("/new/totalBooks")
    public int totalBooks() {
        return libraryService.totalBooks();
    }
}