package ap.assignment2;

import java.util.*;

public class zoo {
    private static ArrayList<Attraction> attractions = new ArrayList<>();
    private static ArrayList<Animal> zooAnimals = new ArrayList<>();
    private static ArrayList<Visitor> visitors = new ArrayList<>();
    private static int mammals = 0;
    private static int reptiles = 0;
    private static int amphibians = 0;
    private static ArrayList<Discount> Discounts = new ArrayList<>();
    private static HashMap<Integer, Integer> specialDeals = new HashMap<>();
    private static double revenue = 0;
    private static ArrayList<String> Feedback = new ArrayList<>();
    private static final int basic = 20;
    private static final int premium = 50;

    public static ArrayList<Visitor> geVisitors() {
        return visitors;
    }

    private static boolean validateEmail(String email) {
        /*
         * Checks if the format of the email is correct or not i.e the format of
         * username@domain.com
         * Simply checks if the index of '.' is after '@'
         * 
         * Params: String i.e. the email to check
         * 
         * Returns: Boolean value. True if the format is followed and false otherwise.
         */
        if (email == null) {
            return false;
        }
        int atIndex = email.indexOf('@');
        int dotIndex = email.lastIndexOf('.');
        return atIndex > 0 && dotIndex > atIndex;
    }

    private static int validatePhoneNo(long phone) {
        /*
         * Checks the the phone number is a valid phone number i.e it is positive and 10
         * digits long
         * 
         * Params: Phone number with data type 'long'
         * 
         * Returns: 1 if the phone number is valid, 0 otherwise
         */
        if (phone <= 0) {
            return 0;
        }
        int validity = 1;
        char[] numbers = Long.toString(phone).toCharArray();
        if (numbers.length != 10) {
            validity = 0;
        }
        return validity;
    }

    public static void setRevenue(double revenue) {
        zoo.revenue += revenue;
    }

    public static int getMammals() {
        return mammals;
    }

    public static void setMammals(int mammals) {
        zoo.mammals = mammals;
    }

    public static int getReptiles() {
        return reptiles;
    }

    public static void setReptiles(int reptiles) {
        zoo.reptiles = reptiles;
    }

    public static int getAmphibians() {
        return amphibians;
    }

    public static void setAmphibians(int amphibians) {
        zoo.amphibians = amphibians;
    }

    public static void main(String[] args) {
        /*
         * Main function which maintains the main flow of the program
         */
        System.out.println("Welcome to ZOOtopia!");
        Scanner input = new Scanner(System.in);
        int option;
        while (true) {
            System.out.println("\n1. Enter as Admin");
            System.out.println("2. Enter as a Visitor");
            System.out.println("3. View Special Deals");
            System.out.println("4. Exit\n");
            System.out.print("Enter your choice: ");
            try {
                option = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input type");
                continue;
            } finally {
                input.nextLine();
                System.out.println();
            }
            if (option == 1) {
                System.out.print("Enter Admin Username: ");
                String user_input = input.nextLine();
                System.out.print("Enter Admin Password: ");
                String pwd_input = input.nextLine();
                System.out.println();
                admin Admin = new admin();
                if (!(Admin.verifyAccount(user_input, pwd_input))) {
                    System.out.println("Invalid username/password\nTry again");
                    continue;
                } else {
                    System.out.println("Logged in as Admin");
                    specialDeals.put(1, 0);
                }
                while (true) {
                    System.out.println();
                    System.out.println("Admin Menu:");
                    System.out.println("1. Manage Attractions");
                    System.out.println("2. Manage Animals");
                    System.out.println("3. Schedule Events");
                    System.out.println("4. Set Discounts");
                    System.out.println("5. Set Special Deal");
                    System.out.println("6. View Visitor Stats");
                    System.out.println("7. View Feedback");
                    System.out.println("8. Exit\n");
                    int choice = -1;
                    System.out.print("Enter your choice: ");
                    try {
                        choice = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    if (choice == 1) {
                        while (true) {
                            System.out.println("\nManage Attractions: ");
                            System.out.println("1. Add Attraction");
                            System.out.println("2. View Attractions");
                            System.out.println("3. Modify Attraction");
                            System.out.println("4. Remove Attraction");
                            System.out.println("5. Exit");
                            System.out.println();
                            int adminControl = 0;
                            System.out.print("Enter your choice: ");
                            try {
                                adminControl = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            System.out.println();
                            if (adminControl == 1) {
                                Attraction newAttraction = Admin.createAttraction(input);
                                attractions.add(newAttraction);
                                System.out.println("Attraction added successfully");
                            } else if (adminControl == 2) {
                                if (attractions.size() == 0) {
                                    System.out.println("No attractions added yet");
                                    continue;
                                }
                                for (Attraction attr : attractions) {
                                    System.out.println(attr);
                                    System.out.println();
                                }
                            } else if (adminControl == 3) {
                                System.out.print("Enter ID of attraction to modify: ");
                                int id;
                                try {
                                    id = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                Attraction toModify = null;
                                for (Attraction attr : attractions) {
                                    if (attr.getId() == id) {
                                        toModify = attr;
                                        break;
                                    }
                                }
                                Attraction modified;
                                if (toModify != null) {
                                    modified = Admin.modifyAttraction(toModify, input);
                                } else {
                                    System.out.println();
                                    System.out.println("Attraction with given ID not found");
                                    continue;
                                }
                                System.out.println();
                                if (modified != null) {
                                    toModify = modified;
                                    System.out.println("Attraction modified successfully");
                                } else {
                                    System.out.println("Error in modifying attraction");
                                }
                            } else if (adminControl == 4) {
                                System.out.print("Enter ID of attraction to remove: ");
                                int id;
                                try {
                                    id = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                Attraction removed = Admin.removeAttraction(attractions, id);
                                if (removed == null) {
                                    System.out.println("Attraction with given ID not found");
                                    continue;
                                } else {
                                    System.out.println(removed.getName() + " removed successfully");
                                }
                            } else if (adminControl == 5) {
                                break;
                            } else {
                                System.out.println("Enter valid option (1-5)");
                            }
                        }
                    } else if (choice == 2) {
                        while (true) {
                            System.out.println("\nManage Animals:");
                            System.out.println("1. Add Animal");
                            System.out.println("2. Update Animal Details");
                            System.out.println("3. Remove Animal");
                            System.out.println("4. Exit\n");
                            int adminControl = 0;
                            System.out.print("Enter your choice: ");
                            try {
                                adminControl = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            System.out.println();
                            if (adminControl == 1) {
                                Animal newAnimal = Admin.addAnimal(input);
                                if (newAnimal != null) {
                                    zooAnimals.add(newAnimal);
                                    System.out.println("Animal added to the zoo");
                                } else {
                                    System.out.println("\nAnimal cannot be added");
                                    System.out.println("Enter only mammals, amphibians, reptiles");
                                    continue;
                                }
                            } else if (adminControl == 2) {
                                if (zooAnimals.size() == 0) {
                                    System.out.println("No animals added yet");
                                    continue;
                                }
                                for (Animal animal : zooAnimals) {
                                    System.out.println(animal);
                                    System.out.println();
                                }
                                System.out.print("Enter ID of animal to modify: ");
                                int id;
                                try {
                                    id = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                Animal toModify = null;
                                for (Animal animal : zooAnimals) {
                                    if (animal.getId() == id) {
                                        toModify = animal;
                                        break;
                                    }
                                }
                                Animal modified;
                                if (toModify != null) {
                                    modified = Admin.modifyAnimal(toModify, input);
                                } else {
                                    System.out.println();
                                    System.out.println("Animal with given ID not found");
                                    continue;
                                }
                                if (modified != null) {
                                    for (int i = 0; i < zooAnimals.size(); i++) {
                                        if (zooAnimals.get(i).equals(toModify)) {
                                            zooAnimals.set(i, modified);
                                            break;
                                        }
                                    }
                                    System.out.println("Animal modified successfully");
                                } else {
                                    System.out.println("Error in modifying animal");
                                }
                            } else if (adminControl == 3) {
                                for (Animal animal : zooAnimals) {
                                    System.out.println(animal);
                                    System.out.println();
                                }
                                System.out.print("Enter ID of animal to remove: ");
                                int id;
                                try {
                                    id = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                Animal removed = Admin.removeAnimal(zooAnimals, id);
                                if (removed == null) {
                                    System.out.println("Animal with given ID not found");
                                    continue;
                                } else {
                                    System.out.println(removed.getName() + " removed successfully");
                                }
                            } else if (adminControl == 4) {
                                if (mammals < 2 || amphibians < 2 || reptiles < 2) {
                                    System.out.println("Enter atleast 2 animals of each type before exiting");
                                    System.out.printf("Current count:\nMammals: %d\nAmphibians: %d\nReptiles: %d\n",
                                            mammals, amphibians, reptiles);
                                    continue;
                                }
                                break;
                            } else {
                                System.out.println("Enter valid option (1-4)");
                            }
                        }
                    } else if (choice == 3) {
                        while (true) {
                            System.out.println("\nSchedule Events:");
                            System.out.println("1. Set price of ticket");
                            System.out.println("2. Set status of attraction");
                            System.out.println("3. View tickets sold for each");
                            System.out.println("4. View events details");
                            System.out.println("5. Exit\n");
                            int adminControl = 0;
                            System.out.print("Enter your choice: ");
                            try {
                                adminControl = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            System.out.println();
                            if (adminControl == 1) {
                                Admin.setAttractionPrice(attractions, input);
                            } else if (adminControl == 2) {
                                Admin.modifyStatus(attractions, input);
                            } else if (adminControl == 3) {
                                if (attractions.size() == 0) {
                                    System.out.println("No attraction added yet");
                                    continue;
                                }
                                for (Attraction attraction : attractions) {
                                    System.out.println(attraction.getName());
                                    System.out.println("Tickets sold: " + attraction.getTicketsSold());
                                    System.out.println();
                                }
                            } else if (adminControl == 4) {
                                if (attractions.size() == 0) {
                                    System.out.println("No attraction added yet");
                                    continue;
                                }
                                for (Attraction attraction : attractions) {
                                    System.out.println(attraction);
                                    System.out.println("Status: " + attraction.getStatus());
                                    System.out.print("Ticket price for basic membership: ");
                                    if (attraction.getBasicCost() == 0) {
                                        System.out.println("NOT SET");
                                    } else {
                                        System.out.println(attraction.getBasicCost());
                                    }
                                    System.out.println();
                                }
                            } else if (adminControl == 5) {
                                break;
                            } else {
                                System.out.println("Enter valid options (1-5)");
                                continue;
                            }
                        }
                    } else if (choice == 4) {
                        Admin.setDiscount(Discounts, input);
                    } else if (choice == 5) {
                        Admin.setSpecialDeal(specialDeals, input);
                    } else if (choice == 6) {
                        System.out.println("\nVisitor Statistics:");
                        System.out.println("- Total Visitors: " + visitors.size());
                        System.out.println("- Total revenue: " + revenue);
                        System.out.print("- Most Popular Attraction: ");
                        if (attractions.size() == 0) {
                            System.out.println("NONE");
                            continue;
                        }
                        Collections.sort(attractions, new AttractionComparator());
                        if (attractions.get(0).getTicketsSold() == 0) {
                            System.out.println("NONE");
                            continue;
                        }
                        for (Attraction attraction : attractions) {
                            if (attraction.getTicketsSold() < attractions.get(0).getTicketsSold()) {
                                break;
                            }
                            System.out.print(attraction.getName() + "  ");
                        }
                        System.out.println();
                    } else if (choice == 7) {
                        if (Feedback.size() == 0) {
                            System.out.println("No feedback added yet");
                            continue;
                        }
                        int l = 1;
                        for (String string : Feedback) {
                            System.out.println(l + ". " + string);
                            l++;
                        }
                    } else if (choice == 8) {
                        System.out.println("\nLogged Out");
                        break;
                    } else {
                        System.out.println("Enter valid options (1-8)");
                    }
                }
            } else if (option == 2) {
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("\nEnter your choice: ");
                int choice;
                try {
                    choice = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input type");
                    continue;
                } finally {
                    input.nextLine();
                    System.out.println();
                }
                if (choice == 1) {
                    System.out.print("Enter Visitor name: ");
                    String name = input.nextLine();
                    System.out.print("Enter Visitor Age: ");
                    int age = 0;
                    try {
                        age = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    if (age <= 0) {
                        System.out.println("Invalid age entered");
                        continue;
                    }
                    System.out.print("Enter Visitor Phone number: ");
                    long phone = 0;
                    try {
                        phone = input.nextLong();
                    } catch (Exception e) {
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    if (validatePhoneNo(phone) == 0) {
                        System.out.println("Invalid phone number. Enter 10 digits");
                        continue;
                    }
                    System.out.print("Enter Visitor Balance: ");
                    double balance = 0;
                    try {
                        balance = input.nextDouble();
                    } catch (Exception e) {
                        System.out.println();
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    if (balance < 0) {
                        System.out.println("Invalid balance amount");
                        continue;
                    }
                    System.out.print("Enter Visitor Email: ");
                    String email = input.nextLine().toLowerCase();
                    if (!(validateEmail(email))) {
                        System.out.println("Invalid email format\nUse: username@domain.com");
                        continue;
                    }
                    int exist = 0;
                    for (Visitor v : visitors) {
                        if (v.getEmail().equals(email)) {
                            System.out.println("Account with this email id already exists. Try again");
                            exist = 1;
                            break;
                        }
                    }
                    if (exist == 1) {
                        continue;
                    }
                    System.out.print("Enter Visitor Password: ");
                    String pwd = input.nextLine();
                    Visitor newVisitor = new Visitor(name, age, phone, balance, email, pwd);
                    visitors.add(newVisitor);
                    System.out.println("\nRegistration is successful.");
                } else if (choice == 2) {
                    System.out.print("Enter Visitor Email: ");
                    String email = input.nextLine().toLowerCase();
                    if (!(validateEmail(email))) {
                        System.out.println("Invalid email format\nUse: username@domain.com");
                        continue;
                    }
                    System.out.print("Enter Visitor Password: ");
                    String pwd = input.nextLine();
                    Visitor visitor = new Visitor();
                    if (visitor.verifyAccount(email, pwd)) {
                        for (Visitor v : visitors) {
                            if (email.equals(v.getEmail()) && pwd.equals(v.getUserPwd())) {
                                visitor = v;
                                break;
                            }
                        }
                    } else {
                        System.out.println("Visitor with given credentials not found. Try again");
                        continue;
                    }
                    System.out.println("\nLogin Successful");
                    while (true) {
                        System.out.println("\nVisitor Menu:");
                        System.out.println("1. Explore the Zoo");
                        System.out.println("2. Buy Membership");
                        System.out.println("3. Buy Tickets");
                        System.out.println("4. View Discounts");
                        System.out.println("5. View Special Deals");
                        System.out.println("6. Visit Animals");
                        System.out.println("7. Visit Attractions");
                        System.out.println("8. Leave Feedback");
                        System.out.println("9. Log Out\n");
                        int opt = -1;
                        System.out.print("Enter your choice: ");
                        try {
                            opt = input.nextInt();
                        } catch (Exception e) {
                            System.out.println("Invalid input type");
                            continue;
                        } finally {
                            input.nextLine();
                        }
                        if (opt == 1) {
                            while (true) {
                                System.out.println("\nExplore the Zoo");
                                System.out.println("1. View Attractions");
                                System.out.println("2. View Animals");
                                System.out.println("3. Exit\n");
                                int visitorControl = 0;
                                System.out.print("Enter your choice: ");
                                try {
                                    visitorControl = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                System.out.println();
                                if (visitorControl == 1) {
                                    if (attractions.size() == 0) {
                                        System.out.println("No attractions added to the zoo yet");
                                        continue;
                                    }
                                    System.out.println("Attraction in the Zoo:");
                                    for (int i = 0; i < attractions.size(); i++) {
                                        System.out.printf("%d. %s\n", i + 1, attractions.get(i).getName());
                                    }
                                    System.out.print("\nEnter your choice: ");
                                    int attr = 0;
                                    try {
                                        attr = input.nextInt();
                                    } catch (Exception e) {
                                        System.out.println("Invalid input type");
                                        continue;
                                    } finally {
                                        input.nextLine();
                                    }
                                    System.out.println(attractions.get(attr - 1).getDescription());
                                } else if (visitorControl == 2) {
                                    if (zooAnimals.size() == 0) {
                                        System.out.println("No animals added to the zoo yet");
                                        continue;
                                    }
                                    System.out.println("Animals in the Zoo:");
                                    for (int i = 0; i < zooAnimals.size(); i++) {
                                        System.out.printf("%d. %s\n", i + 1, zooAnimals.get(i).getName());
                                    }
                                    System.out.print("\nEnter your choice: ");
                                    int attr = 0;
                                    try {
                                        attr = input.nextInt();
                                    } catch (Exception e) {
                                        System.out.println("Invalid input type");
                                        continue;
                                    } finally {
                                        input.nextLine();
                                    }
                                    System.out.println("Type: " + zooAnimals.get(attr - 1).getClass().getSimpleName());
                                } else if (visitorControl == 3) {
                                    break;
                                } else {
                                    System.out.println("Enter valid option (1-3)");
                                }
                            }
                        } else if (opt == 2) {
                            System.out.println("\nBuy Membership:");
                            System.out.printf("1. Basic Membership (Rs %d)\n", basic);
                            System.out.printf("2. Premium Membership (Rs %d)\n", premium);
                            System.out.print("\nEnter your choice: ");
                            int choose = 0;
                            try {
                                choose = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            if (choose == 1) {
                                int possible = visitor.buyMembership(basic, Discounts, input);
                                if (possible != -1 && visitor.getMembership() == 1) {
                                    System.out.println("Basic membership purchased successfully. Your balance is now "
                                            + visitor.getBalance());
                                }
                            } else if (choose == 2) {
                                int possible = visitor.buyMembership(premium, Discounts, input);
                                if (possible != -1 && visitor.getMembership() == 1) {
                                    visitor.setMembership(2);
                                    System.out.println("Premium membership purchased successfully. Your balance is now "
                                            + visitor.getBalance());
                                }
                            } else {
                                System.out.println("Enter a valid option (1-2)");
                            }
                        } else if (opt == 3) {
                            if (visitor.getMembership() == 0) {
                                System.out.println("Membership not bought yet. Buy membership first");
                                continue;
                            }
                            if (attractions.size() == 0) {
                                System.out.println("No attraction added yet");
                                continue;
                            }
                            System.out.println("\nBuy Tickets:");
                            System.out.print("Enter number of tickets to buy: ");
                            int tickets = 0;
                            try {
                                tickets = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            if (tickets <= 0) {
                                System.out.println("No ticket bought");
                                continue;
                            }
                            System.out.println("\nSelect an Attraction to Buy a Ticket:");
                            for (int j = 0; j < attractions.size(); j++) {
                                System.out.printf("%d. %s (Rs %f)\n", j + 1, attractions.get(j).getName(),
                                        attractions.get(j).getBasicCost());
                            }
                            System.out.print("\nEnter your choice: ");
                            int ticket = 0;
                            try {
                                ticket = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            if (!(ticket >= 1 && ticket <= attractions.size())) {
                                System.out.println("\nEnter valid options");
                                continue;
                            }
                            Attraction bought = attractions.get(ticket - 1);
                            if (bought.getStatus().equals("CLOSE")) {
                                System.out.println("Attraction currently closed. Cannot buy ticket");
                                continue;
                            }
                            if (visitor.getMembership() == 1) {
                                int temp = tickets;
                                while (!(specialDeals.containsKey(temp))) {
                                    temp--;
                                }
                                int offer = specialDeals.get(temp);
                                double saveBasic = bought.getBasicCost();
                                int available = visitor.getDiscount(Discounts, input);
                                if (available != -1) {
                                    bought.setBasicCost(bought.getBasicCost() * (1 - available / 100.0));
                                } else {
                                    continue;
                                }
                                double totalCost = bought.getBasicCost() * tickets * (1 - offer / 100.0);
                                bought.setBasicCost(saveBasic);
                                if (visitor.checkBalance(totalCost) == false) {
                                    System.out.println("Not enough balance in the account");
                                    continue;
                                }
                                if (temp != 1) {
                                    System.out.printf("Special deal for %d tickets applied\n", temp);
                                    System.out.printf("You save %d percent on them!\n", specialDeals.get(temp));
                                    System.out.println();
                                }
                                visitor.addTickets(bought, tickets);
                                revenue += totalCost;
                                visitor.setBalance(visitor.getBalance() - totalCost);
                                System.out.printf(
                                        "The ticket for %s was purchased successfully. Your balance is now Rs %f\n",
                                        bought.getName(), visitor.getBalance());
                            } else if (visitor.getMembership() == 2) {
                                if (bought.getStatus().equals("CLOSE")) {
                                    System.out.println("Attraction currently closed. Cannot buy ticket");
                                    continue;
                                }
                                visitor.addTickets(bought, tickets);
                                System.out.printf(
                                        "The ticket for %s was purchased successfully. Your balance is now Rs %f\n",
                                        bought.getName(), visitor.getBalance());
                            }
                        } else if (opt == 4) {
                            if (Discounts.size() == 0) {
                                System.out.println("\nNo discount available");
                                continue;
                            }
                            System.out.println("\nView Discounts:");
                            for (int i = 0; i < Discounts.size(); i++) {
                                System.out.printf("%d. %s (%d%% discount) - %s\n", i + 1,
                                        Discounts.get(i).getCategory(), Discounts.get(i).getPercent(),
                                        Discounts.get(i).getCode());
                            }
                        } else if (opt == 5) {
                            if (specialDeals.size() == 0) {
                                System.out.println("\nNo special deal added");
                                continue;
                            }
                            System.out.println("\nSpecial Deals:");
                            int k = 0;
                            for (Map.Entry<Integer, Integer> entry : specialDeals.entrySet()) {
                                if (entry.getKey().equals(1)) {
                                    continue;
                                }
                                System.out.printf("%d. Buy %d tickets and get %d%% off\n", ++k, entry.getKey(),
                                        entry.getValue());
                            }
                        } else if (opt == 6) {
                            if (visitor.getMembership() == 0) {
                                System.out.println("Membership not bought yet. Buy membership first");
                                continue;
                            }
                            if (zooAnimals.size() == 0) {
                                System.out.println("No animals added to the zoo yet");
                                continue;
                            }
                            System.out.println("\nVisit Animal: ");
                            for (int j = 0; j < zooAnimals.size(); j++) {
                                System.out.printf("%d. %s\n", j + 1, zooAnimals.get(j).getName());
                            }
                            System.out.print("\nEnter your choice: ");
                            int animal = 0;
                            try {
                                animal = input.nextInt();
                            } catch (Exception e) {
                                System.out.println("Invalid input type");
                                continue;
                            } finally {
                                input.nextLine();
                            }
                            if (!(animal >= 1 && animal <= zooAnimals.size())) {
                                System.out.println("\nEnter valid options");
                                continue;
                            }
                            Animal visiting = zooAnimals.get(animal - 1);
                            visitor.visitAnimal(visiting, input);
                        } else if (opt == 7) {
                            if (visitor.getMembership() == 0) {
                                System.out.println("Membership not bought yet. Buy membership first");
                                continue;
                            }
                            if (attractions.size() == 0) {
                                System.out.println("No attractions added to the zoo yet");
                                continue;
                            }
                            while (true) {
                                System.out.println("Select an Attraction to visit:");
                                for (int j = 0; j < attractions.size(); j++) {
                                    System.out.printf("%d. %s (Rs %f)\n", j + 1, attractions.get(j).getName(),
                                            attractions.get(j).getBasicCost());
                                }
                                System.out.println(attractions.size() + 1 + ". Exit");
                                System.out.print("\nEnter your choice: ");
                                int ticket = 0;
                                try {
                                    ticket = input.nextInt();
                                } catch (Exception e) {
                                    System.out.println("Invalid input type");
                                    continue;
                                } finally {
                                    input.nextLine();
                                }
                                if (ticket == attractions.size() + 1) {
                                    break;
                                }
                                if (!(ticket >= 1 && ticket <= attractions.size())) {
                                    System.out.println("\nEnter valid options");
                                    continue;
                                }
                                Attraction visited = attractions.get(ticket - 1);
                                if (visited.getStatus().equals("CLOSE")) {
                                    System.out.println("Attraction currently closed. Cannot visit");
                                    System.out.println();
                                    continue;
                                }
                                visitor.visitAttraction(visited);
                            }
                        } else if (opt == 8) {
                            if (visitor.getMembership() == 0) {
                                System.out.println("Membership not bought yet. Buy membership first");
                                continue;
                            }
                            visitor.leaveFeedback(Feedback, input);
                            System.out.println("\nThank you for your feedback!");
                        } else if (opt == 9) {
                            System.out.println("\nLogged Out");
                            break;
                        } else {
                            System.out.println("\nEnter valid option (1-9)");
                        }
                    }
                } else {
                    System.out.println("Enter a valid option (1-2)");
                    continue;
                }
            } else if (option == 3) {
                if (specialDeals.size() == 0) {
                    System.out.println("No special deals added yet");
                    continue;
                }
                System.out.println("SPECIAL DEALS TODAY!!!");
                for (Map.Entry<Integer, Integer> entry : specialDeals.entrySet()) {
                    if (entry.getKey().equals(1)) {
                        continue;
                    }
                    System.out.println("Deal: Minimum " + entry.getKey() + "\nDiscount: " + entry.getValue());
                    System.out.println();
                }
            } else if (option == 4) {
                System.out.println("Thank you for visiting!");
                break;
            } else {
                System.out.println("Enter a valid option: 1, 2, 3, 4");
            }
        }
        input.close();
    }
}
