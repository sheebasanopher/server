package com.sheeba.server.service;

import com.sheeba.server.domain.LibraryBookPojo;
import com.sheeba.server.domain.LibraryStudentPojo;
import com.sheeba.server.repository.BookRepository;
import com.sheeba.server.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryService {
    private final StudentRepository studentRepository;
    private final BookRepository bookRepository;

    @Autowired
    public LibraryService(StudentRepository studentRepository, BookRepository bookRepository) {
        this.studentRepository = studentRepository;
        this.bookRepository = bookRepository;
    }

    public String newAddStudent(LibraryStudentPojo libraryStudentPojo) {
        LibraryStudentPojo output = studentRepository.save(libraryStudentPojo);
        System.out.println("this is save");
        return "success";
    }

    public List<LibraryStudentPojo> getStudents() {
        List<LibraryStudentPojo> actualList = new ArrayList<>();
        Iterable<LibraryStudentPojo> iterator = studentRepository.findAll();
        for (LibraryStudentPojo libraryStudentPojo : iterator) {
            actualList.add(libraryStudentPojo);
        }
        return actualList;
    }

    public LibraryStudentPojo getStudent(Integer rollNo) {
        Optional<LibraryStudentPojo> student = studentRepository.findById(rollNo);
        if (student.isPresent()) {
            return student.get();
        } else {
            return null;
        }
    }

    public LibraryStudentPojo getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public LibraryStudentPojo getNameAndDepartment(String name, String department) {
        return studentRepository.findByNameAndDepartment(name, department);
    }

    public String newAddBook(LibraryBookPojo libraryBookPojo) {
        LibraryBookPojo output = bookRepository.save(libraryBookPojo);
        return "success";
    }

    public List<LibraryBookPojo> getBooks() {
        List<LibraryBookPojo> actualList = new ArrayList<>();
        Iterable<LibraryBookPojo> iterator = bookRepository.findAll();
        for (LibraryBookPojo libraryBookPojo : iterator) {
            actualList.add(libraryBookPojo);
        }
        return actualList;
    }

    public LibraryBookPojo getBook(Integer id) {
        Optional<LibraryBookPojo> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        } else {
            return null;
        }
    }

    public List<LibraryBookPojo> getBookDetails(String bookIsbnNo) {
        List<LibraryBookPojo> output = new ArrayList<>();
        Iterable<LibraryBookPojo> iterable = bookRepository.findByIsbnNo(bookIsbnNo);
        for (LibraryBookPojo book : iterable) {
            output.add(book);
        }
        return output;
    }

    public LibraryBookPojo getBookIdAndIsbnNo(Integer id, String isbnNo) {
        Optional<LibraryBookPojo> book = bookRepository.findByIdAndIsbnNo(id, isbnNo);
        if (book.isPresent()) {
            return book.get();

        } else {
            return null;
        }
    }

    public String borrowBook(Integer bookId, String isbnNo, Integer studentRollNo) {
        Optional<LibraryStudentPojo> student = studentRepository.findById(studentRollNo);
        if (!student.isPresent()) {
            return "invalid student rollNo ";
        }
        Optional<LibraryBookPojo> book = bookRepository.findByIdAndIsbnNo(bookId, isbnNo);
        if (!book.isPresent()) {
            return "invalid book id or isbnNo";
        } else if (book.get().isBorrowed()) {
            return "book already borrowed";
        } else {
            LibraryBookPojo libraryBookPojo = book.get();
            libraryBookPojo.setBorrowed(true);
            libraryBookPojo.setRollNo(studentRollNo);
            bookRepository.save(libraryBookPojo);
            return "book borrowed success";
        }
    }

    public String returnBook(Integer bookId, String isbnNo, Integer studentRollNo) {
        Optional<LibraryStudentPojo> student = studentRepository.findById(studentRollNo);
        if (!student.isPresent()) {
            return "invalid student rollNo";
        }
        Optional<LibraryBookPojo> book = bookRepository.findByIdAndIsbnNo(bookId, isbnNo);
        if (!book.isPresent()) {
            return "invalid book id or isbnNo";
        } else if (!book.get().isBorrowed()) {
            return "book is not borrowed";
        } else {
            LibraryBookPojo library = book.get();
            library.setBorrowed(false);
            library.removeRollNo(studentRollNo);
            bookRepository.save(library);
            return "book return success";
        }
    }

    public List<String> getBorrowedDetails() {
        List<String> output = new ArrayList<>();
        List<LibraryBookPojo> noOfBorrowedBooks = new ArrayList<>();
        List<LibraryBookPojo> noOfNonBorrowedBooks = new ArrayList<>();
        List<LibraryBookPojo> totalBooks = getBooks();
        for (LibraryBookPojo book : totalBooks) {
            if (book.isBorrowed()) {
                noOfBorrowedBooks.add(book);
            } else {
                noOfNonBorrowedBooks.add(book);
            }
        }
        output.add("no of borrowed books : " + noOfBorrowedBooks.size());
        output.add("no of non borrowed books : " + noOfNonBorrowedBooks.size());
        output.add("total books : " + totalBooks.size());
        return output;
    }

    public Map<String, Integer> getTotalBook() {
        Map<String, Integer> countBookMap = new HashMap<>();
        Iterable<LibraryBookPojo> iterable = bookRepository.findAll();
        for (LibraryBookPojo book : iterable) {
            String name = book.getName();
            boolean isBookPresent = countBookMap.containsKey(name);
            if (isBookPresent) {
                Integer output = countBookMap.get(name);
                int count = 0;
                count = output + 1;
                countBookMap.put(name, count);
            } else {
                int count = 0;
                count++;
                countBookMap.put(name, count);
            }
        }
        return countBookMap;
    }

    public Map<String, List<String>> getStudentsDepartment() {
        Map<String, List<String>> departmentByRollNoMap = new HashMap<>();
        Iterable<LibraryStudentPojo> iterable = studentRepository.findAll();
        for (LibraryStudentPojo student : iterable) {
            String department = student.getDepartment();
            int rollNo = student.getRollNo();
            String name = student.getName();
            boolean isDepartmentPresent = departmentByRollNoMap.containsKey(department);
            if (isDepartmentPresent) {
                List<String> output = departmentByRollNoMap.get(department);
                output.add(rollNo + " : " + name);
                departmentByRollNoMap.put(department, output);
            } else {
                List<String> output = new ArrayList<>();
                output.add(rollNo + " : " + name);
                departmentByRollNoMap.put(department, output);
            }
        }
        return departmentByRollNoMap;
    }

    public Map<Integer, List<String>> getStudentHavingBook() {
        Map<Integer, List<String>> studentsByBookMap = new HashMap<>();
        Iterable<LibraryBookPojo> iterable = bookRepository.findAll();
        for (LibraryBookPojo book : iterable) {
            int stdRollNo = book.getStdRollNo();
            String name = book.getName();
            String isbnNo = book.getIsbnNo();
            int rollNo = book.getStdRollNo();
            boolean isRollNoPresent = studentsByBookMap.containsKey(stdRollNo);
            if (isRollNoPresent && rollNo > 0) {
                List<String> output = studentsByBookMap.get(stdRollNo);
                output.add(isbnNo + " : " + name);
                studentsByBookMap.put(stdRollNo, output);
            } else if (rollNo > 0) {
                List<String> output = new ArrayList<>();
                output.add(isbnNo + " : " + name);
                studentsByBookMap.put(stdRollNo, output);
            }
        }
        return studentsByBookMap;
    }

    public int totalBooks() {
        Iterable<LibraryBookPojo> iterable = bookRepository.findAll();
        int count = 0;
        for (LibraryBookPojo libraryBookPojo : iterable) {
            count++;
        }
        return count;
    }
}
