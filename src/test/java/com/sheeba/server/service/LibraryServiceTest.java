package com.sheeba.server.service;

import com.sheeba.server.domain.LibraryBookPojo;
import com.sheeba.server.domain.LibraryStudentPojo;
import com.sheeba.server.repository.BookRepository;
import com.sheeba.server.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LibraryServiceTest {
    private LibraryService libraryService;
    private final BookRepository bookRepository = Mockito.mock(BookRepository.class);
    private final StudentRepository studentRepository = Mockito.mock(StudentRepository.class);

    @BeforeEach
    void setUp() {
        libraryService = new LibraryService(studentRepository, bookRepository);
    }

    @Test
    public void testAdd() {
        LibraryBookPojo jack = new LibraryBookPojo(1, "jack", "y28", false);
        Mockito.when(bookRepository.save(jack)).thenReturn(jack);
        String output = libraryService.newAddBook(jack);
        assertEquals("success", output);

    }

    @Test
    public void testBorrowedBooks() {
        List<LibraryBookPojo> libraryBookPojos = new ArrayList<>();
        libraryBookPojos.add(new LibraryBookPojo(1, "harry potter", "123", true));
        libraryBookPojos.add(new LibraryBookPojo(2, "hello", "342", false));
        libraryBookPojos.add(new LibraryBookPojo(3, "New world", "987", false));

        Mockito.when(bookRepository.findAll()).thenReturn(libraryBookPojos);

        List<String> borrowedDetails = libraryService.getBorrowedDetails();

        assertEquals(borrowedDetails.get(0), "no of borrowed books : 1");
        assertEquals(borrowedDetails.get(1), "no of non borrowed books : 2");
        assertEquals(borrowedDetails.get(2), "total books : 3");
    }

    @Test
    public void testTotalBooks() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", true);
        LibraryBookPojo harryPotterBook1 = new LibraryBookPojo(5, "harry potter", "123", false);
        LibraryBookPojo helenBook = new LibraryBookPojo(2, "helen", "he4345", false);
        LibraryBookPojo spiderMan = new LibraryBookPojo(3, "spider man", "sp4369", false);
        LibraryBookPojo honeyBook = new LibraryBookPojo(4, "honey", "ho34702", false);
        List<LibraryBookPojo> books = new ArrayList<>();
        books.add(harryPotterBook);
        books.add(helenBook);
        books.add(spiderMan);
        books.add(honeyBook);
        books.add(harryPotterBook1);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        Map<String, Integer> totalBook = libraryService.getTotalBook();

        assertEquals(4, totalBook.size());
    }

    @Test
    public void testGetStudentsDepartment() {
        LibraryStudentPojo poojo = new LibraryStudentPojo("pooja", 011, "biology", 12);
        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 012, "computer", 13);
        LibraryStudentPojo stellaStd = new LibraryStudentPojo("stella", 013, "commerce", 12);
        LibraryStudentPojo saiStd = new LibraryStudentPojo("sai", 014, "computer", 12);
        List<LibraryStudentPojo> students = new ArrayList<>();
        students.add(poojo);
        students.add(benoStd);
        students.add(stellaStd);
        students.add(saiStd);

        Mockito.when(studentRepository.findAll()).thenReturn(students);

        Map<String, List<String>> output = libraryService.getStudentsDepartment();

        assertEquals(3, output.size());
    }

    @Test
    public void testNoOfBooks() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", true);
        LibraryBookPojo harryPotterBook1 = new LibraryBookPojo(5, "harry potter", "123", false);
        LibraryBookPojo helenBook = new LibraryBookPojo(2, "helen", "he4345", false);
        LibraryBookPojo spiderMan = new LibraryBookPojo(3, "spider man", "sp4369", false);
        LibraryBookPojo honeyBook = new LibraryBookPojo(4, "honey", "ho34702", false);
        List<LibraryBookPojo> books = new ArrayList<>();
        books.add(harryPotterBook);
        books.add(helenBook);
        books.add(spiderMan);
        books.add(honeyBook);
        books.add(harryPotterBook1);

        Mockito.when(bookRepository.findAll()).thenReturn(books);

        int output = libraryService.totalBooks();

        assertEquals(5, output);
    }

    @Test
    public void getBorrowedBooks() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", true);
        LibraryBookPojo harryPotterBook1 = new LibraryBookPojo(5, "harry potter", "123", false);
        LibraryBookPojo helenBook = new LibraryBookPojo(2, "helen", "he4345", false);
        LibraryBookPojo spiderMan = new LibraryBookPojo(3, "spider man", "sp4369", false);
        LibraryBookPojo honeyBook = new LibraryBookPojo(4, "honey", "ho34702", false);
        List<LibraryBookPojo> books = new ArrayList<>();
        books.add(harryPotterBook);
        books.add(helenBook);
        books.add(spiderMan);
        books.add(honeyBook);
        books.add(harryPotterBook1);

        LibraryStudentPojo poojo = new LibraryStudentPojo("pooja", 11, "biology", 12);
        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);
        LibraryStudentPojo stellaStd = new LibraryStudentPojo("stella", 13, "commerce", 12);
        LibraryStudentPojo saiStd = new LibraryStudentPojo("sai", 14, "computer", 12);
        List<LibraryStudentPojo> students = new ArrayList<>();
        students.add(poojo);
        students.add(benoStd);
        students.add(stellaStd);
        students.add(saiStd);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(2, "he4345")).thenReturn(Optional.of(helenBook));

        String output = libraryService.borrowBook(2, "he4345", 12);

        assertEquals("book borrowed success", output);
    }

    @Test
    public void testInvalidRollNoBorrowedBook() {
        LibraryBookPojo spiderMan = new LibraryBookPojo(3, "spider man", "sp4369", false);

        LibraryStudentPojo stellaStd = new LibraryStudentPojo("stella", 13, "commerce", 12);

        Mockito.when(studentRepository.findById(13)).thenReturn(Optional.of(stellaStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(3, "sp4369")).thenReturn(Optional.of(spiderMan));

        String output = libraryService.borrowBook(3, "sp4369", 34);

        assertEquals("invalid student rollNo ", output);
    }

    @Test
    public void testInvalidBorrowedBook() {
        LibraryBookPojo spiderMan = new LibraryBookPojo(3, "spider man", "sp4369", false);

        LibraryStudentPojo stellaStd = new LibraryStudentPojo("stella", 13, "commerce", 12);

        Mockito.when(studentRepository.findById(13)).thenReturn(Optional.of(stellaStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(3, "sp4369")).thenReturn(Optional.of(spiderMan));

        String output = libraryService.borrowBook(56, "h42769", 13);

        assertEquals("invalid book id or isbnNo", output);
    }

    @Test
    public void testAlreadyBorrowedBook() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", true);

        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(1, "123")).thenReturn(Optional.of(harryPotterBook));

        String output = libraryService.borrowBook(1, "123", 12);

        assertEquals("book already borrowed", output);
    }

    @Test
    public void testReturnBook() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", false);

        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(1, "123")).thenReturn(Optional.of(harryPotterBook));

        String borrowedBook = libraryService.borrowBook(1, "123", 12);
        String output = libraryService.returnBook(1, "123", 12);

        assertEquals("book return success", output);

    }

    @Test
    public void testInvalidRollNoReturnBook() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", false);

        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(1, "123")).thenReturn(Optional.of(harryPotterBook));

        String borrowedBook = libraryService.borrowBook(1, "123", 12);
        String output = libraryService.returnBook(1, "123", 78);

        assertEquals("invalid student rollNo", output);

    }

    @Test
    public void testInvalidIdReturnBook() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", false);

        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(1, "123")).thenReturn(Optional.of(harryPotterBook));

        String borrowedBook = libraryService.borrowBook(1, "123", 12);
        String output = libraryService.returnBook(37, "dgiu334", 12);

        assertEquals("invalid book id or isbnNo", output);

    }

    @Test
    public void testNotBorrowedReturnBook() {
        LibraryBookPojo harryPotterBook = new LibraryBookPojo(1, "harry potter", "123", false);

        LibraryStudentPojo benoStd = new LibraryStudentPojo("beno", 12, "computer", 13);

        Mockito.when(studentRepository.findById(12)).thenReturn(Optional.of(benoStd));
        Mockito.when(bookRepository.findByIdAndIsbnNo(1, "123")).thenReturn(Optional.of(harryPotterBook));

        String output = libraryService.returnBook(1, "123", 12);

        assertEquals("book is not borrowed", output);

    }
}