package ap.assignment1;

import java.util.*;

public class Librarian {
    public void addBook(ArrayList<Book> bookList, Book newBook) {
        bookList.add(newBook);
    }

    public int removeBook(ArrayList<Book> bookList, int id) {
        int foundBook = 0;
        for (Book book : bookList) {
            if (book.getID() == id) {
                bookList.remove(book);
                foundBook = 1;
                System.out.println("---------------------------------");
                System.out.println("Book Removed Successfully");
                break;
            }
        }
        return foundBook;
    }

    public int removeMember(ArrayList<Member> members, int id, ArrayList<Book> Books) {
        int foundMember = 0;
        for (Member member : members) {
            if (member.getID() == id) {
                members.remove(member);
                member.returnAllBooks(Books);
                foundMember = 1;
                System.out.println("---------------------------------");
                System.out.printf("%s is removed successfully.\n", member.getName());
                break;
            }
        }
        return foundMember;
    }

    public int removeMember(ArrayList<Member> members, String name, ArrayList<Book> Books) {
        int foundMember = 0;
        for (Member member : members) {
            if (member.getName().equals(name)) {
                members.remove(member);
                member.returnAllBooks(Books);
                foundMember = 1;
                System.out.println("---------------------------------");
                System.out.printf("%s is removed successfully.\n", member.getName());
                break;
            }
        }
        return foundMember;
    }

    public int removeMember(ArrayList<Member> members, long phone, ArrayList<Book> Books) {
        int foundMember = 0;
        for (Member member : members) {
            if (member.getPhoneNo() == phone) {
                members.remove(member);
                member.returnAllBooks(Books);
                foundMember = 1;
                System.out.println("---------------------------------");
                System.out.printf("%s is removed successfully.\n", member.getName());
                break;
            }
        }
        return foundMember;
    }

    public void displayMembers(ArrayList<Member> members) {
        if (members.size() == 0) {
            System.out.println("No members added yet.");
        } else {
            for (Member member : members) {
                member.getInfo();
                System.out.println();
            }
        }
    }

    public void addMember(ArrayList<Member> members, Member newMember) {
        members.add(newMember);
        System.out.println("---------------------------------");
        System.out.println("Member Successfully Registered with member ID \"" + newMember.getID() + "\" !");
    }
}