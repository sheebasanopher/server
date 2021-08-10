package com.sheeba.server.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<StudentPojo> studentPojoList = new ArrayList<>();
    private List<BookPojo> bookPojoList = new ArrayList<>();

    public Library() {

    }

    public List<StudentPojo> getStudentPojoList() {
        return studentPojoList;
    }

    public boolean addStudent(StudentPojo studentPojo) {
        return studentPojoList.add(studentPojo);
    }

    public List<BookPojo> getBookPojoList() {
        return bookPojoList;
    }

    public boolean addBook(BookPojo bookPojo) {
        return bookPojoList.add(bookPojo);
    }

    public StudentPojo getStudentDetail(String stdRollNo) {
        for (StudentPojo student : getStudentPojoList()) {
            String rollNo = student.getRollNo();
            if (stdRollNo.equals(rollNo)) {
                return student;
            }
        }
        return null;
    }

    public List<BookPojo> getBookDetail(String bookIsbnNo) {
        List<BookPojo> output = new ArrayList<>();
        for (BookPojo book : getBookPojoList()) {
            String isbnNo = book.getIsbnNo();
            if (bookIsbnNo.equals(isbnNo)) {
                output.add(book);

            }
        }
        return output;
    }

    public BookPojo getBooksDetail(String bookId, String bookIsbNo) {
        for (BookPojo book : getBookPojoList()) {
            String id = book.getId();
            String isbnNo = book.getIsbnNo();
            if (bookId.equals(id) && bookIsbNo.equals(isbnNo)) {
                return book;
            }
        }
        return null;
    }

    public String borrowBook(String stdRollNo, String bookId, String bookIsbnNo) {
        BookPojo book = getBooksDetail(bookId, bookIsbnNo);
        StudentPojo student = getStudentDetail(stdRollNo);
        String output = "";
        if (book == null) {
            output = "invalid book id or isbnNo";
        } else if (student == null) {
            output = "invalid student RollNo";
        } else if (book.isBorrowed()) {
            output = "book already borrowed";
        } else if (student.getBookIdList().size() < 2 && !book.isBorrowed()) {
            book.setBorrowed(true);
            book.setRollNo(stdRollNo);
            student.addIdBook(bookId);
            output = "book borrowed success";
        } else {
            output = "already 2 book borrowed";
        }
        return output;
    }

    public String borrowBookNew(String stdRollNo, String bookId, String bookIsbnNo) {
        BookPojo book = getBooksDetail(bookId, bookIsbnNo);

        if (book == null) {
            return "invalid book isbnNo";
        } else if (book.isBorrowed()) {
            return "book already borrowed";
        }

        StudentPojo student = getStudentDetail(stdRollNo);

        if (student == null) {
            return "invalid student RollNo";
        } else if (student.getBookIdList().size() >= 2) {
            return "already 2 book borrowed";
        } else {
            book.setBorrowed(true);
            book.setRollNo(stdRollNo);
            return "book borrowed success";
        }
    }

    public String returnBook(String stdRollNo1, String bookId, String bookIsbnNo) {
        StudentPojo student = getStudentDetail(stdRollNo1);
        BookPojo book = getBooksDetail(bookId, bookIsbnNo);
        String output = "";
        if (student == null) {
            output = "invalid student rollNo";
        } else if (book == null) {
            output = "invalid book id or isbnNo";
        } else if (book.isBorrowed()) {
            book.setBorrowed(false);
            book.removeRollNo(stdRollNo1);
            student.removeId(bookId);
            output = "return book success";
        } else {
            output = "not borrowed the book";
        }
        return output;
    }

    public int getNotBorrowed(String isbnNo) {
        List<BookPojo> output = new ArrayList<>();
        List<BookPojo> books = getBookDetail(isbnNo);
        for (BookPojo book : books) {
            boolean isBorrowed = book.isBorrowed();
            if (!isBorrowed) {
                output.add(book);
            }
        }
        return output.size();
    }

    public List<String> getBorrowedBook() {
        List<String> output = new ArrayList<>();
        List<BookPojo> noOfBorrowedBook = new ArrayList<>();
        List<BookPojo> noOfNonBorrowedBook = new ArrayList<>();
        List<BookPojo> books = getBookPojoList();
        for (BookPojo book : books) {
            boolean isBorrowed = book.isBorrowed();
            if (isBorrowed) {
                noOfBorrowedBook.add(book);
            } else {
                noOfNonBorrowedBook.add(book);
            }
        }
        output.add("no of borrowed book : " + noOfBorrowedBook.size());
        output.add("no of non borrowed book : " + noOfNonBorrowedBook.size());
        output.add("total no of books : " + books.size());
        return output;
    }

    public Map<String, Integer> getBookDetail() {
        Map<String, Integer> bookCountMap = new HashMap<>();
        List<BookPojo> books = getBookPojoList();
        for (BookPojo book : books) {
            String name = book.getName();
            boolean isNamePresent = bookCountMap.containsKey(name);
            if (isNamePresent) {
                int count = 0;
                Integer output = bookCountMap.get(name);
                count = output + 1;
                bookCountMap.put(name, count);
            } else {
                int count = 0;
                count = count + 1;
                bookCountMap.put(name, count);
            }
        }
        return bookCountMap;
    }
}