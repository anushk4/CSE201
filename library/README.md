# Library Management System

This project uses Object Oriented Programming (OOPs) principles to develop a Library Management System. The user can use the system either as a librarian or as a student/member.

To implement this system, three objects named `Book`, `Librarian` and `Member`. The project has 4 java files - `Library.java`, `Book.java`, `Member.java` and `Librarian.java`.

## Running the Code

After running `mvn compile` or `mvn package` in the terminal to install necessary dependencies, run the code in the `Library.java` file by pressing `F5` or clicking on `run` button.

Three options are presented first:
- Enter as a Librarian (further displays <b>`7`</b> more options. Details are mentioned below)
- Enter as a Member (further displays <b>`7`</b> more options. Details are mentioned below)
- Exit (terminates the application)

### Entering as a Librarian
After clicking on 1, Librarian object is created and <b>`7`</b> options are presented.

- <b>Register a Member: </b>Enter member's `name`, `age` and `phone number` to register them.
- <b>Remove a Member: </b> The members can be removed by either ID, name or phone number. The issued books for the removed members are added back to the library.
- <b>Add a book: </b>Enter the `book name`, `author` and the number of `copies` to create different objects with unique ids for each book and add them in the library.
- <b>Remove a book: </b>Enter the book ID to remove the book (or a copy of the book). The book ID will not be re-assigned to new books added after removing a book.
- Display information for all members along with their pending fines at the moment of calling this option.
- Display information for all books in the library (including issued and not issued).
- <b>Exit: </b>Exit the librarian menu to come back to the main menu.

### Entering as a Member
After clicking on 2, Member object is created and <b>`7`</b> options are presented.

- <b>List all books: </b>List all available books in the library that can be issued.
- <b>List issued books: </b>List all books issued by the member.
- <b>Issue a book: </b>Asks for the book ID and book name to issue the book. Also checks if any book is overdue and ensures that the member can't issue more than 2 books.
- <b>Return a book: </b>Asks for book ID and removes the book from the issued books list of each member. It also calculates fine according to the time of issue and time of return.
- <b>Calculate fine: </b>`It is an additional option added to calculate and display fine as per the guidelines`. Fine is only calculated after books have been returned.
- <b>Pay Fine: </b>Pays all dues at the same time only after all books have been returned.
- <b>Exit: </b>Exit the member menu to come back to the main menu.

## Cleanup
Run `mvn clean` to clear and delete the target directory.