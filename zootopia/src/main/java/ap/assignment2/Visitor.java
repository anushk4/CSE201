package ap.assignment2;

import java.util.*;

public class Visitor implements VisitorMenu, Login {
    /*
     * Visitor class which has all methods required to perform visitor operations
     */
    private String name;
    private int age;
    private long phone;
    private double balance;
    private String email;
    private String userPwd;
    private static int VisitorID = 1000;
    private int id;
    private int membership = 0;
    private HashMap<Attraction, Integer> ticketsBought = new HashMap<>();

    public Visitor() {
        this.email = "";
        this.userPwd = "";
    }

    public Visitor(String name, int age, long phone, double balance, String email, String userPwd) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.balance = balance;
        this.email = email;
        this.userPwd = userPwd;
        this.id = ++VisitorID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMembership() {
        return membership;
    }

    public void setMembership(int membership) {
        this.membership = membership;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public boolean checkBalance(double totalCost) {
        /*
         * Checks if the balance follows below 0 by making a transaction
         * 
         * Params: double type as possible expenditure
         * 
         * Returns: boolean true if balance is above 0 and false otherwise
         */
        if (this.balance - totalCost < 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean verifyAccount(String para1, String para2) {
        /*
         * Verifies if the entered email and password of the visitor matches the already
         * registered users stored in the arraylist of all visiots
         * values hard coded in the admin class
         * 
         * Params: Two string values, one as email and other as password
         * 
         * Returns: boolean true if the input is valid and matches, false otherwise
         */
        ArrayList<Visitor> visitors = zoo.geVisitors();
        for (Visitor v : visitors) {
            if (para1.equals(v.getEmail()) && para2.equals(v.getUserPwd())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void addTickets(Attraction attraction, int tickets) {
        /*
         * Increments the number of tickets for the user's arraylist of all tickets
         * bought for the specific attraction, to keep track of the tickets the user
         * bought for specific attractions
         * 
         * Params: Attraction object for which the ticket is bought, number of tickets
         * bought
         * 
         * Returns: void
         */
        int initialTickets = (ticketsBought.get(attraction) != null) ? ticketsBought.get(attraction) : 0;
        attraction.setTicketsSold(tickets);
        ticketsBought.put(attraction, initialTickets + tickets);
    }

    @Override
    public int buyMembership(int pack, ArrayList<Discount> discounts, Scanner input) {
        /*
         * Updates the membership of visitor according to the pack bought and applies
         * discount accordingly
         * 
         * 0: No membership bought
         * 1: Basic membership bought
         * 2: Premium membership bought
         * 
         * Params: Cost of the membership bought, Arraylist of all discounts available,
         * Scanner object to take input
         * 
         * Returns: 1 if membership is bought successfully or the discount code is
         * invalid, -1 if there is not enough balance to buy membership
         */
        this.setMembership(1);
        System.out.print("\nApply Discount Code: ");
        String code = input.nextLine().toUpperCase();
        if (code.equals("NONE")) {
            double newBalance = this.balance - pack;
            if (checkBalance(pack) == false) {
                System.out.println("Not enough balance in the account");
                return -1;
            }
            zoo.setRevenue(pack);
            this.setBalance(newBalance);
            return 1;
        }
        Discount discountApplied = null;
        for (Discount discount : discounts) {
            if (discount.getCode().equals(code)) {
                discountApplied = discount;
                break;
            }
        }
        System.out.println();
        if (discountApplied == null) {
            System.out.println("Discount code not found. Try again");
            this.setMembership(0);
            return 1;
        } else {
            String category = discountApplied.getCategory().toLowerCase();
            if (category.contains("minor") || category.contains("student")) {
                if (this.getAge() < 18) {
                    if (checkBalance(pack * (1 - discountApplied.getPercent() / 100.0)) == false) {
                        System.out.println("Not enough balance in the account");
                        return -1;
                    }
                    System.out.println("Discount code applied successfully");
                    double newBalance = this.balance - (pack * (1 - discountApplied.getPercent() / 100.0));
                    zoo.setRevenue((pack * (1 - discountApplied.getPercent() / 100.0)));
                    this.setBalance(newBalance);
                } else {
                    System.out.println("Discount only applicable for age below 18. Try again");
                    this.setMembership(0);
                    return 1;
                }
            } else if (category.contains("senior")) {
                if (this.getAge() > 60) {
                    if (checkBalance(pack * (1 - discountApplied.getPercent() / 100.0)) == false) {
                        System.out.println("Not enough balance in the account");
                        return -1;
                    }
                    System.out.println("Discount code applied successfully");
                    double newBalance = this.balance - pack * (1 - discountApplied.getPercent() / 100.0);
                    zoo.setRevenue((pack * (1 - discountApplied.getPercent() / 100.0)));
                    this.setBalance(newBalance);
                } else {
                    System.out.println("Discount only applicable for age above 60. Try again");
                    this.setMembership(0);
                    return 1;
                }
            } else {
                System.out.println("Discount code applied successfully");
                if (checkBalance(pack * (1 - discountApplied.getPercent() / 100.0)) == false) {
                    System.out.println("Not enough balance in the account");
                    return -1;
                }
                double newBalance = this.balance - pack * (1 - discountApplied.getPercent() / 100.0);
                zoo.setRevenue((pack * (1 - discountApplied.getPercent() / 100.0)));
                this.setBalance(newBalance);
            }
            return 1;
        }
    }

    @Override
    public int getDiscount(ArrayList<Discount> discounts, Scanner input) {
        /*
         * Iterates through the arraylist of discounts and checks if the entered code is
         * valid or not for the age range
         * 
         * Params: Arraylist of all discounts available, Scanner object to take input
         * 
         * Returns: discount percentage if the application is successful, -1 if it is
         * invalid
         */
        System.out.print("\nApply Discount Code: ");
        String code = input.nextLine().toUpperCase();
        if (code.equals("NONE")) {
            return 0;
        }
        Discount discountApplied = null;
        for (Discount discount : discounts) {
            if (discount.getCode().equals(code)) {
                discountApplied = discount;
                break;
            }
        }
        System.out.println();
        if (discountApplied == null) {
            System.out.println("Discount code not found. Try again");
            System.out.println();
            return -1;
        } else {
            String category = discountApplied.getCategory().toLowerCase();
            if (category.contains("minor") || category.contains("student")) {
                if (this.getAge() < 18) {
                    System.out.println("Discount code applied successfully");
                } else {
                    System.out.println("Discount only applicable for age below 18. Try again");
                    return -1;
                }
            } else if (category.contains("senior")) {
                if (this.getAge() > 60) {
                    System.out.println("Discount code applied successfully");
                } else {
                    System.out.println("Discount only applicable for age above 60. Try again");
                    return -1;
                }
            } else {
                System.out.println("Discount code applied successfully");
            }
        }
        System.out.println();
        return discountApplied.getPercent();
    }

    @Override
    public void visitAnimal(Animal visiting, Scanner input) {
        /*
         * Allows the user to read or feed an animal. Feeding an animal results in
         * producing a sound stored by the Animal object and reading about the animal
         * displays the history of the animal stored in the Animal object
         * 
         * Params: Animal object that is being visited, Scanner object to take input
         * 
         * Returns: void
         */
        System.out.println("\n1. Feed animal");
        System.out.println("2. Read about animal");
        System.out.print("\nEnter your choice: ");
        int choice = 0;
        try {
            choice = input.nextInt();
        } catch (Exception e) {
            System.out.println("\nInvalid input type");
            return;
        } finally {
            input.nextLine();
        }
        if (choice == 1) {
            System.out.println();
            visiting.makeSound();
        } else if (choice == 2) {
            System.out.println();
            System.out.println(visiting.getHistory());
        } else {
            System.out.println("Enter valid option");
            return;
        }
    }

    @Override
    public void visitAttraction(Attraction visited) {
        /*
         * Decrements the tickets bought by 1 if this function is called i.e. the
         * attraction is visited. Doesn't ask the premium members for the ticket
         * 
         * Params: Attraction object being visited
         * 
         * Returns: void
         */
        int initialTickets = (ticketsBought.get(visited) != null) ? ticketsBought.get(visited) : 0;
        if (initialTickets == 0 && this.membership == 1) {
            System.out.println("Ticket not available. Basic Members need to buy separate tickets for the attractions.");
            System.out.println();
            return;
        }
        if (initialTickets == 0 && this.membership == 2) {
            visited.setTicketsSold(1);
            System.out.println("Thank you for visiting " + visited.getName() + ". Hope you enjoyed the attraction.");
            System.out.println();
            return;
        }
        initialTickets--;
        ticketsBought.put(visited, initialTickets);
        if (this.membership == 1) {
            System.out.println("1 Ticket Used.");
        }
        System.out.println("Thank you for visiting " + visited.getName() + ". Hope you enjoyed the attraction.");
        System.out.println();
    }

    @Override
    public void leaveFeedback(ArrayList<String> Feedback, Scanner input) {
        /*
         * Takes the feedback as input from the user. Slices the input to 200 characters
         * if it exceeds the character limit
         * 
         * Params: Arraylist storing all feedbacks, Scanner object to take input
         * 
         * Returns: void
         */
        System.out.println("Leave Feedback: ");
        System.out.print("Enter your feedback (max 200 characters): ");
        String feedback = input.nextLine();
        Feedback.add(feedback.substring(0, Math.min(feedback.length(), 200)));
    }
}
