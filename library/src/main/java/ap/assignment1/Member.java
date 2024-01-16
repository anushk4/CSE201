package ap.assignment1;

import java.util.ArrayList;

public class Member {
    private int memberID;
    private static int latestmemberID = 1;
    private String name;
    private int age;
    private long phoneNo;
    private ArrayList<Book> myBooks;
    private long fine = 0;
    private Book LastIssued = null;

    Member(String name, long phone, int age) {
        this.name = name;
        this.phoneNo = phone;
        this.age = age;
        this.memberID = latestmemberID++;
        this.myBooks = new ArrayList<Book>();
    }

    public int getID() {
        return this.memberID;
    }

    public long getPhoneNo() {
        return this.phoneNo;
    }

    public String getName() {
        return this.name;
    }

    public void setFine(long days) {
        if (days <= 10) {
            System.out.println(" No fine added for timely return.");
        } else {
            long lateDay = days - 10;
            this.fine += 3 * lateDay;
            System.out.println(3 * lateDay + " Rupees has been added for a delay of " + lateDay + " days.");
        }
    }

    public void getFine() {
        System.out.println("Fine pending: Rs. " + this.fine);
    }

    public Book getLastIssued() {
        return this.LastIssued;
    }

    public void setLastIssued(Book last) {
        this.LastIssued = last;
    }

    public void getInfo() {
        System.out.println("Member ID: " + this.memberID);
        System.out.println("Name: " + this.name);
        System.out.println("Age: " + this.age);
        System.out.println("Phone no: " + this.phoneNo);
        System.out.print("Issued books: ");
        if (myBooks.size() == 0) {
            System.out.print("None");
        } else {
            for (Book book : myBooks) {
                System.out.print(book.getTitle());
                System.out.print("    ");
            }
        }
        System.out.println();
        long totalfine = this.fine + momentaryFine();
        System.out.println("Fine pending: " + totalfine);
    }

    private long momentaryFine() {
        long fine = 0;
        for (Book book : myBooks) {
            fine += book.fineAtMoment();
        }
        return fine;
    }

    public void displayMyBooks() {
        if (myBooks.size() == 0) {
            System.out.println("No books issued yet");
            System.out.println("Start by issuing a book first!");
        } else {
            System.out.print("Books issued till now:\n");
            for (Book book : myBooks) {
                book.displayInfo();
                System.out.println();
            }
        }
    }

    public void returnAllBooks(ArrayList<Book> Books) {
        for (Book book : myBooks) {
            book.setAvailable(1);
            book.setIssue(null);
            book.setReturn(null);
        }
        myBooks.clear();
    }

    public void addBook(Book issue) {
        myBooks.add(issue);
    }

    public Book returnBook(int id) {
        Book found = null;
        for (Book book : myBooks) {
            if (book.getID() == id) {
                try {
                    if (myBooks.get(1).equals(book)) {
                        setLastIssued(myBooks.get(0));
                    }
                } catch (Exception e) {
                    ;
                }
                found = book;
                break;
            }
        }
        myBooks.remove(found);
        return found;
    }

    public long returnFine() {
        return this.fine;
    }

    public void payFine() {
        if (this.fine == 0) {
            System.out.println("No fine pending to pay");
        } else {
            System.out.println("Your fine of Rs. " + this.fine + " has been paid successfully!");
            this.fine = 0;
        }
    }

    public int issuedSize() {
        return myBooks.size();
    }
}