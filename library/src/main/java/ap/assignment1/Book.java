package ap.assignment1;

import java.time.Duration;
import java.time.Instant;

public class Book {
    private int bookID;
    private static int latestbookID = 1;
    private String title;
    private String author;
    private Instant issue = null;
    private Instant returned = null;
    private int available;

    Book(String title, String author) {
        this.bookID = bookID++;
        this.title = title;
        this.author = author;
        this.bookID = latestbookID++;
        this.available = 1;
    }

    public int checkAvailable() {
        return this.available;
    }

    public void setAvailable(int status) {
        this.available = status;
    }

    public int getID() {
        return this.bookID;
    }

    public String getTitle() {
        return this.title;
    }

    public Instant getIssue() {
        return issue;
    }

    public void setIssue(Instant time) {
        if (time != null) {
            issue = Instant.ofEpochSecond(time.getEpochSecond(), time.getNano());
        } else {
            issue = null;
        }
    }

    public Instant getReturn() {
        return returned;
    }

    public void setReturn(Instant time) {
        if (time != null) {
            returned = Instant.ofEpochSecond(time.getEpochSecond(), time.getNano());
        } else {
            returned = null;
        }
    }

    public void displayInfo() {
        System.out.println("Book ID: " + this.bookID);
        System.out.println("Title: " + this.title);
        System.out.println("Author: " + this.author);
    }

    public long fineAtMoment() {
        long fine = 0;
        if (returned == null && issue != null) {
            Instant issueTime = issue;
            Instant dueTime = issueTime.plusSeconds(10);
            Instant currentTime = Instant.now();
            if (currentTime.isAfter(dueTime)) {
                fine += (long) (Duration.between(issueTime, currentTime).getSeconds() - 10) * 3;
            }
        }
        return fine;
    }
}