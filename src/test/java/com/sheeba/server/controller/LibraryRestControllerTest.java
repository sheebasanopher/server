package com.sheeba.server.controller;

import com.sheeba.server.domain.BookPojo;
import com.sheeba.server.domain.StudentPojo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LibraryRestControllerTest {
    LibraryRestController libraryRestController;

    @BeforeEach
    public void setup() {
        libraryRestController = new LibraryRestController();
    }

    @Test
    public void testAddStudent() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);

        //when
        String output = libraryRestController.addStudent(rajaStudent);

        //then
        assertEquals("raja success", output);
    }

    @Test
    public void testGetStudents() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);

        //when
        List<StudentPojo> output = libraryRestController.getStudents();

        //then
        assertEquals("arun", output.get(1).getName());
    }

    @Test
    public void testGetStudent() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);

        //when
        StudentPojo output = libraryRestController.getStudent("112");

        //then
        assertEquals("arun", output.getName());
    }

    @Test
    public void testNotGetStudent() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);

        //when
        StudentPojo output = libraryRestController.getStudent("001");

        //then
        assertNull(output);
    }

    @Test
    public void testAddBook() {
        //give
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);

        //when
        String output = libraryRestController.addBook(lifeOfPieBook);

        //then
        assertEquals("life of pie success", output);
    }

    @Test
    public void testGetBooks() {
        //give
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);

        //when
        List<BookPojo> output = libraryRestController.getBooks();

        //then
        assertEquals("honey bee", output.get(2).getName());
    }

    @Test
    public void testGetBook() {
        //give
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo gooseBlocksBook2 = new BookPojo("011", "goose blocks", "yiu5872", false);
        String addGooseBlock2 = libraryRestController.addBook(gooseBlocksBook2);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);

        //when
        List<BookPojo> output = libraryRestController.getBook("yiu5872");
        List<BookPojo> output1 = libraryRestController.getBook("fsh5782");

        //then
        assertEquals("goose blocks", output.get(0).getName());
    }

    @Test
    public void testNoGetBook() {
        //give
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);

        //when
        List<BookPojo> output = libraryRestController.getBook("hsi76287");

        //then
        assertEquals(0, output.size());
    }

    @Test
    public void testBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo lifeOfPieBook2 = new BookPojo("011", "life of pie", "afg67898", false);
        String addLifeOfPie2 = libraryRestController.addBook(lifeOfPieBook2);

        //when
        String output = libraryRestController.borrowedBook("111", "001", "afg67898");
        String op10 = libraryRestController.borrowedBook("113", "011", "afg67898");

        //then
        assertEquals("book borrowed success", output);
        assertEquals("book borrowed success", op10);
    }

    @Test
    public void testNotBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("006", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);
        BookPojo tomAndJerryBook = new BookPojo("007", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("008", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        //when
        String output = libraryRestController.borrowedBook("111", "001", "afg67898");
        String output1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op2 = libraryRestController.borrowedBook("112", "002", "yiu5872");
        String output3 = libraryRestController.borrowedBook("111", "004", "hgs6527");
        String output2 = libraryRestController.borrowedBook("111", "003", "fsh5782");
        String op4 = libraryRestController.borrowedBook("112", "006", "hs3572");
        String op5 = libraryRestController.borrowedBook("112", "005", "wdw3582");
        String op6 = libraryRestController.borrowedBook("112", "007", "djs4802");
        String op7 = libraryRestController.borrowedBook("112", "008", "hga6589");

        //then
        assertEquals("already 2 book borrowed", output2);
        assertEquals("already 2 book borrowed", op6);
        assertEquals("already 2 book borrowed", op7);
    }

    @Test
    public void testAlreadyBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);

        //when
        String output = libraryRestController.borrowedBook("111", "001", "afg67898");
        String output1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op2 = libraryRestController.borrowedBook("112", "002", "yiu5872");

        //then
        assertEquals("book already borrowed", op2);
    }

    @Test
    public void testInvalidStdRollNoBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);


        BookPojo tomAndJerryBook = new BookPojo("001", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("002", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        //when
        String op8 = libraryRestController.borrowedBook("234", "001", "djs4802");

        //then
        assertEquals("invalid student RollNo", op8);
    }

    @Test
    public void testInvalidIsbnNOBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);

        //when
        String op9 = libraryRestController.borrowedBook("113", "587", "gksr6984");

        //then
        assertEquals("invalid book id or isbnNo", op9);
    }

    @Test
    public void testGetBorrowedBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("006", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);
        BookPojo tomAndJerryBook = new BookPojo("007", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("008", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        String output0 = libraryRestController.borrowedBook("111", "001", "afg67898");
        String output1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op2 = libraryRestController.borrowedBook("112", "002", "yiu5872");
        String output3 = libraryRestController.borrowedBook("111", "004", "hgs6527");
        String output2 = libraryRestController.borrowedBook("111", "003", "fsh5782");
        String op4 = libraryRestController.borrowedBook("112", "006", "hs3572");
        String op5 = libraryRestController.borrowedBook("112", "005", "wdw3582");
        String op6 = libraryRestController.borrowedBook("112", "007", "djs4802");
        String op7 = libraryRestController.borrowedBook("112", "008", "hga6589");

        //when
        List<BookPojo> totalOutput = libraryRestController.getBorrowedBook();

        //then
        assertEquals("goose blocks", totalOutput.get(1).getName());
    }

    @Test
    public void testHavingBooks() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("006", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);
        BookPojo tomAndJerryBook = new BookPojo("007", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("008", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        String output0 = libraryRestController.borrowedBook("111", "001", "afg67898");
        String output1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op2 = libraryRestController.borrowedBook("112", "002", "yiu5872");
        String output3 = libraryRestController.borrowedBook("111", "004", "hgs6527");
        String output2 = libraryRestController.borrowedBook("111", "003", "fsh5782");
        String op4 = libraryRestController.borrowedBook("112", "006", "hs3572");
        String op5 = libraryRestController.borrowedBook("112", "005", "wdw3582");
        String op6 = libraryRestController.borrowedBook("112", "007", "djs4802");
        String op7 = libraryRestController.borrowedBook("112", "008", "hga6589");

        String return0 = libraryRestController.returnBook("111", "004", "hgs6527");
        String return1 = libraryRestController.returnBook("113", "002", "yiu5872");

        //when
        List<StudentPojo> totalOutput = libraryRestController.havingBooks();

        //then
        assertEquals("raja", totalOutput.get(0).getName());
    }

    @Test
    public void testReturnBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("006", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);
        BookPojo tomAndJerryBook = new BookPojo("007", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("008", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        String op0 = libraryRestController.borrowedBook("111", "001", "afg67898");
        String op1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op3 = libraryRestController.borrowedBook("111", "004", "hgs6527");
        String op4 = libraryRestController.borrowedBook("112", "006", "hs3572");
        String op5 = libraryRestController.borrowedBook("112", "005", "wdw3582");

        //when
        String output = libraryRestController.returnBook("113", "002", "yiu5872");
        String output1 = libraryRestController.returnBook("111", "004", "hgs6527");
        String output2 = libraryRestController.returnBook("112", "006", "hs3572");

        //then
        assertEquals("return book success", output);
    }

    @Test
    public void testNoReturnBooks() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", false);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", false);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("006", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);
        BookPojo tomAndJerryBook = new BookPojo("007", "tom & jerry", "djs4802", false);
        String addTomAndJerry = libraryRestController.addBook(tomAndJerryBook);
        BookPojo spiderManBook = new BookPojo("008", "spider", "hga6589", false);
        String AddSpiderMan = libraryRestController.addBook(spiderManBook);

        String op0 = libraryRestController.borrowedBook("111", "001", "afg67898");
        String op1 = libraryRestController.borrowedBook("113", "002", "yiu5872");
        String op3 = libraryRestController.borrowedBook("111", "004", "hgs6527");
        String op4 = libraryRestController.borrowedBook("112", "006", "hs3572");
        String op5 = libraryRestController.borrowedBook("112", "005", "wdw3582");

        //when
        String output = libraryRestController.returnBook("761", "005", "wdw3582");
        String output1 = libraryRestController.returnBook("111", "345", "gas6987");
        String output2 = libraryRestController.returnBook("113", "003", "fsh5782");

        //then
        assertEquals("not borrowed the book", output2);
    }

    @Test
    public void testInvalidStdRollNoReturnBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);

        BookPojo theHotel = new BookPojo("001", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo oliveOil = new BookPojo("002", "olive oil", "hs3572", false);
        String addOliveOil = libraryRestController.addBook(oliveOil);

        String op4 = libraryRestController.borrowedBook("112", "002", "hs3572");

        //when
        String output = libraryRestController.returnBook("761", "001", "wdw3582");

        //then
        assertEquals("invalid student rollNo", output);
    }

    @Test
    public void testInvalidIsbnNoReturnBook() {
        //give
        StudentPojo rajaStudent = new StudentPojo("raja", "111", "computer", 12);
        String addRaja = libraryRestController.addStudent(rajaStudent);
        StudentPojo arunStudent = new StudentPojo("arun", "112", "biology", 13);
        String addArun = libraryRestController.addStudent(arunStudent);
        StudentPojo priyaStudent = new StudentPojo("priya", "113", "biology", 13);
        String addPriya = libraryRestController.addStudent(priyaStudent);

        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", false);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);

        String op0 = libraryRestController.borrowedBook("111", "001", "afg67898");
        String op4 = libraryRestController.borrowedBook("112", "002", "hs3572");

        //when
        String output1 = libraryRestController.returnBook("111", "9092", "gas6987");

        //then
        assertEquals("invalid book id or isbnNo", output1);

    }

    @Test
    public void testGetNotBorrowedBook() {
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", true);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", true);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", true);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo theHotel = new BookPojo("005", "hotel", "wdw3582", false);
        String addHotel = libraryRestController.addBook(theHotel);
        BookPojo nemoBook2 = new BookPojo("006", "nemo", "hgs6527", false);
        String addNemo2 = libraryRestController.addBook(nemoBook2);
        BookPojo nemoBook3 = new BookPojo("007", "nemo", "hgs6527", false);
        String addNemo3 = libraryRestController.addBook(nemoBook3);

        int output = libraryRestController.getNotBorrowedBook("hgs6527");

        assertEquals(2, output);

    }

    @Test
    public void testGetBookStatus() {
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", true);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo honeyBeeBook = new BookPojo("003", "honey bee", "fsh5782", true);
        String addHoneyBeeBook = libraryRestController.addBook(honeyBeeBook);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", true);
        String addNemo = libraryRestController.addBook(nemoBook);

        List<String> output = libraryRestController.getBorrowedStatus();

        assertEquals("no of borrowed book : 3", output.get(0));
        assertEquals("no of non borrowed book : 1", output.get(1));
        assertEquals("total no of books : 4", output.get(2));
    }

    @Test
    public void testGetBookDetails() {
        BookPojo lifeOfPieBook = new BookPojo("001", "life of pie", "afg67898", true);
        String addLifeOfPie = libraryRestController.addBook(lifeOfPieBook);
        BookPojo gooseBlocksBook = new BookPojo("002", "goose blocks", "yiu5872", false);
        String addGooseBlock = libraryRestController.addBook(gooseBlocksBook);
        BookPojo lifeOfPieBook2 = new BookPojo("006", "life of pie", "afg67898", true);
        String addLifeOfPie2 = libraryRestController.addBook(lifeOfPieBook2);
        BookPojo nemoBook = new BookPojo("004", "nemo", "hgs6527", true);
        String addNemo = libraryRestController.addBook(nemoBook);
        BookPojo lifeOfPieBook1 = new BookPojo("005", "life of pie", "afg67898", true);
        String addLifeOfPie1 = libraryRestController.addBook(lifeOfPieBook1);
        BookPojo gooseBlocksBook1 = new BookPojo("007", "goose blocks", "yiu5872", false);
        String addGooseBlock1 = libraryRestController.addBook(gooseBlocksBook1);

        Map<String,Integer> output=libraryRestController.getBookDetails();

        assertEquals(3,output.size());
    }
}
