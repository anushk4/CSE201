package ap.assignment1;

import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class Library {
    private static ArrayList<Book> bookList = new ArrayList<Book>();
    private static ArrayList<Member> MemberList = new ArrayList<Member>();

    private static int validatePhoneNo(long phone) {
        int validity = 1;
        char[] numbers = Long.toString(phone).toCharArray();
        if (numbers.length != 10) {
            validity = 0;
        }
        return validity;
    }

    private static Member findMember(String name, Long phone) {
        Member found = null;
        for (Member member : MemberList) {
            if (member.getName().equalsIgnoreCase(name.toLowerCase()) && member.getPhoneNo() == phone) {
                found = member;
            }
        }
        return found;
    }

    private static Book findBook(int id, String name) {
        Book found = null;
        for (Book book : bookList) {
            if (book.getTitle().equalsIgnoreCase(name.toLowerCase()) && book.getID() == id) {
                found = book;
            }
        }
        return found;
    }

    private static void displayAllBooks(ArrayList<Book> bookList) {
        if (bookList.size() == 0) {
            System.out.println("No books added yet.");
        } else {
            for (Book book : bookList) {
                book.displayInfo();
                System.out.println();
            }
        }
    }

    private static void displayAvailableBooks(ArrayList<Book> bookList) {
        if (bookList.size() == 0) {
            System.out.println("No books added yet.");
        } else {
            for (Book book : bookList) {
                if (book.checkAvailable() == 0) {
                    continue;
                }
                book.displayInfo();
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Library Portal Initialized...");
        int choice = 0;
        Scanner input = new Scanner(System.in);
        while (choice != 3) {
            System.out.println("---------------------------------");
            System.out.println("1   Enter as a librarian");
            System.out.println("2.  Enter as a member");
            System.out.println("3.  Exit");
            System.out.println("---------------------------------");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                input.nextLine();
                System.out.println("---------------------------------");
                System.out.println("Invalid input. Try again");
                continue;
            }
            input.nextLine();
            if (choice == 1) {
                Librarian YourRole = new Librarian();
                int roleChoice = 0;
                while (roleChoice != 7) {
                    System.out.println("---------------------------------");
                    System.out.println("1.  Register a member");
                    System.out.println("2.  Remove a member");
                    System.out.println("3.  Add a book");
                    System.out.println("4.  Remove a book");
                    System.out.println("5.  View all members along with their books and fines to be paid");
                    System.out.println("6.  View all books");
                    System.out.println("7.  Back");
                    System.out.println("---------------------------------");
                    try {
                        roleChoice = input.nextInt();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println("---------------------------------");
                        System.out.println("Invalid input. Try again");
                        continue;
                    }
                    input.nextLine();
                    if (roleChoice == 1) {
                        System.out.println("---------------------------------");
                        System.out.print("Name: ");
                        String name;
                        int age;
                        try {
                            name = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        System.out.print("Age: ");
                        try {
                            age = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        System.out.print("Phone no: ");
                        long phone;
                        try {
                            phone = input.nextLong();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        if (validatePhoneNo(phone) == 0) {
                            System.out.println("Invalid phone number. Enter 10 digit phone number");
                            System.out.println("Try again!");
                            continue;
                        }
                        int duplicate = 0;
                        for (Member mem : MemberList) {
                            if (mem.getPhoneNo() == phone) {
                                System.out.println("Account with this phone number already exists.");
                                System.out.println("Try again");
                                duplicate = 1;
                                break;
                            }
                        }
                        if (duplicate == 1) {
                            continue;
                        }
                        Member newMember = new Member(name, phone, age);
                        YourRole.addMember(MemberList, newMember);
                    } else if (roleChoice == 2) {
                        int removeChoice = 0;
                        System.out.println("---------------------------------");
                        System.out.println("1.  Remove by Member ID");
                        System.out.println("2.  Remove by Member Name");
                        System.out.println("3.  Remove by Member Phone No");
                        System.out.println("---------------------------------");
                        try {
                            removeChoice = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        int memberFound = 0;
                        System.out.println("---------------------------------");
                        if (removeChoice == 1) {
                            System.out.print("Enter Member ID: ");
                            int id;
                            try {
                                id = input.nextInt();
                            } catch (Exception e) {
                                input.nextLine();
                                System.out.println("---------------------------------");
                                System.out.println("Invalid input. Try again");
                                continue;
                            }
                            input.nextLine();
                            memberFound = YourRole.removeMember(MemberList, id, bookList);
                        } else if (removeChoice == 2) {
                            String name;
                            System.out.print("Enter Member Name: ");
                            try {
                                name = input.nextLine();
                            } catch (Exception e) {
                                System.out.println("---------------------------------");
                                System.out.println("Invalid input. Try again");
                                continue;
                            }
                            memberFound = YourRole.removeMember(MemberList, name, bookList);
                        } else if (removeChoice == 3) {
                            long phone;
                            System.out.print("Enter Member Phone No: ");
                            try {
                                phone = input.nextLong();
                            } catch (Exception e) {
                                input.nextLine();
                                System.out.println("---------------------------------");
                                System.out.println("Invalid input. Try again");
                                continue;
                            }
                            input.nextLine();
                            if (validatePhoneNo(phone) == 0) {
                                System.out.println("Invalid phone number. Enter 10 digit phone number");
                                System.out.println("Try again!");
                                continue;
                            }
                            memberFound = YourRole.removeMember(MemberList, phone, bookList);
                        } else {
                            System.out.println("Enter a valid choice");
                            System.out.println("Try again");
                            continue;
                        }
                        if (memberFound == 0) {
                            System.out.println("Member not found");
                            System.out.println("Try Again");
                            continue;
                        }
                    } else if (roleChoice == 3) {
                        String title, author;
                        int copy;
                        System.out.println("---------------------------------");
                        System.out.print("1.  Book Title: ");
                        try {
                            title = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        System.out.print("2.  Author: ");
                        try {
                            author = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        System.out.print("3.  Copies: ");
                        try {
                            copy = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        for (int i = 0; i < copy; i++) {
                            Book newBook = new Book(title, author);
                            YourRole.addBook(bookList, newBook);
                        }
                        System.out.println("---------------------------------");
                        System.out.println("Book Added Successfully");
                    } else if (roleChoice == 4) {
                        int id;
                        System.out.println("---------------------------------");
                        System.out.print("Enter Book ID: ");
                        try {
                            id = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        int bookFound = YourRole.removeBook(bookList, id);
                        if (bookFound == 0) {
                            System.out.println("Book not found");
                            System.out.println("Try Again");
                            continue;
                        }
                    } else if (roleChoice == 5) {
                        System.out.println("---------------------------------");
                        YourRole.displayMembers(MemberList);
                    } else if (roleChoice == 6) {
                        System.out.println("---------------------------------");
                        displayAllBooks(bookList);
                    } else if (roleChoice == 7) {
                        break;
                    } else {
                        System.out.println("---------------------------------");
                        System.out.println("Enter valid choice.");
                        System.out.println("Options available: 1, 2, 3, 4, 5, 6, 7");
                    }
                }
            } else if (choice == 2) {
                String name;
                long phone;
                System.out.println("---------------------------------");
                System.out.print("Name: ");
                try {
                    name = input.nextLine();
                } catch (Exception e) {
                    System.out.println("---------------------------------");
                    System.out.println("Invalid input. Try again");
                    continue;
                }
                System.out.print("Phone No: ");
                try {
                    phone = input.nextLong();
                } catch (Exception e) {
                    input.nextLine();
                    System.out.println("---------------------------------");
                    System.out.println("Invalid input. Try again");
                    continue;
                }
                input.nextLine();
                if (validatePhoneNo(phone) == 0) {
                    System.out.println("Invalid phone number. Enter 10 digit phone number");
                    System.out.println("Try again!");
                    continue;
                }
                Member YourRole = findMember(name, phone);
                if (YourRole == null) {
                    System.out.printf("Member with Name: %s and Phone No: %d doesn't exist.\n", name, phone);
                    continue;
                }
                System.out.println("Welcome " + YourRole.getName() + ". Member ID: " + YourRole.getID());
                int roleChoice = 0;
                while (roleChoice != 7) {
                    Instant issueDay = Instant.now();
                    Instant returnDay = Instant.now();
                    System.out.println("---------------------------------");
                    System.out.println("1.  List Available Books");
                    System.out.println("2.  List My Books");
                    System.out.println("3.  Issue book");
                    System.out.println("4.  Return book");
                    System.out.println("5.  Calculate fine");
                    System.out.println("6.  Pay Fine");
                    System.out.println("7.  Back");
                    System.out.println("---------------------------------");
                    try {
                        roleChoice = input.nextInt();
                    } catch (Exception e) {
                        input.nextLine();
                        System.out.println("---------------------------------");
                        System.out.println("Invalid input. Try again");
                        continue;
                    }
                    input.nextLine();
                    if (roleChoice == 1) {
                        System.out.println("---------------------------------");
                        displayAvailableBooks(bookList);
                    } else if (roleChoice == 2) {
                        System.out.println("---------------------------------");
                        YourRole.displayMyBooks();
                    } else if (roleChoice == 3) {
                        System.out.println("---------------------------------");
                        if (bookList.size() == 0) {
                            System.out.println("No books added in the library yet.");
                            System.out.println("Try again later");
                        }
                        if (YourRole.issuedSize() == 2) {
                            System.out.println("You cannot issue more than 2 books at the same time");
                            continue;
                        }
                        Instant currentTime = Instant.now();
                        Book lastIssuedBook = YourRole.getLastIssued();
                        if (lastIssuedBook != null && lastIssuedBook.getIssue() != null) {
                            Instant issueTime = lastIssuedBook.getIssue();
                            Instant dueTime = issueTime.plusSeconds(10);
                            if (currentTime.isAfter(dueTime)) {
                                System.out.println("Last Issued book is overdue");
                                System.out.println("Clear all fines before issuing another book");
                                continue;
                            }
                        }
                        if (YourRole.returnFine() != 0) {
                            YourRole.getFine();
                            System.out.println("Pay pending dues before issuing a book");
                            continue;
                        }
                        int id;
                        String bname;
                        System.out.print("Book ID: ");
                        try {
                            id = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        System.out.print("Book Name: ");
                        try {
                            bname = input.nextLine();
                        } catch (Exception e) {
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        Book issue = findBook(id, bname);
                        if (issue == null) {
                            System.out.println("Book with these credentials doesn't exist.");
                            System.out.println("Try issuing another book");
                            continue;
                        }
                        YourRole.addBook(issue);
                        issue.setAvailable(0);
                        issueDay = Instant.now();
                        issue.setIssue(issueDay);
                        System.out.println("Book Issued Successfully!");
                        YourRole.setLastIssued(issue);
                    } else if (roleChoice == 4) {
                        int id;
                        System.out.println("---------------------------------");
                        System.out.print("Enter Book ID: ");
                        try {
                            id = input.nextInt();
                        } catch (Exception e) {
                            input.nextLine();
                            System.out.println("---------------------------------");
                            System.out.println("Invalid input. Try again");
                            continue;
                        }
                        input.nextLine();
                        Book rBook = YourRole.returnBook(id);
                        if (rBook == null) {
                            System.out.println("You haven't issued this book yet.");
                            System.out.println("Please check the details and try again");
                            continue;
                        }
                        rBook.setAvailable(1);
                        returnDay = Instant.now();
                        rBook.setReturn(returnDay);
                        long totalDays = (long) Duration.between(rBook.getIssue(), rBook.getReturn()).getSeconds();
                        System.out.print("Book ID: " + rBook.getID() + " successfully returned. ");
                        rBook.setIssue(null);
                        rBook.setReturn(null);
                        YourRole.setFine(totalDays);
                    } else if (roleChoice == 5) {
                        System.out.println("---------------------------------");
                        YourRole.getFine();
                    } else if (roleChoice == 6) {
                        System.out.println("---------------------------------");
                        if (YourRole.issuedSize() != 0) {
                            System.out.println("Return all books before paying the fine");
                            continue;
                        }
                        YourRole.payFine();
                    } else if (roleChoice == 7) {
                        break;
                    } else {
                        System.out.println("---------------------------------");
                        System.out.println("Enter valid choice.");
                        System.out.println("Options available: 1, 2, 3, 4, 5, 6, 7");
                    }
                }
            } else if (choice == 3) {
                System.out.println("---------------------------------");
                System.out.println("Thanks for visiting!");
                System.out.println("---------------------------------");
                break;
            } else {
                System.out.println("---------------------------------");
                System.out.println("Enter valid choice.");
                System.out.println("Options available: 1, 2, 3");
            }
        }
        input.close();
    }
}