package ap.assignment2;

import java.util.*;

public class admin implements AdminMenu, Login {
    /*
     * Admin class which has all methods required to perform admin operations
     */
    private final String admin = "admin";
    private final String pwd = "admin123";

    admin() {
    }

    public String getAdmin() {
        return admin;
    }

    public String getPwd() {
        return pwd;
    }

    @Override
    public boolean verifyAccount(String para1, String para2) {
        /*
         * Verifies if the entered username and password of the admin matches the final
         * values hard coded in the admin class
         * 
         * Params: Two string values, one as username and other as password
         * 
         * Returns: boolean true if the input is valid and matches, false otherwise
         */
        if (this.admin.equals(para1) && this.pwd.equals(para2)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Attraction createAttraction(Scanner input) {
        /*
         * Takes the attraction name and description as input and creates a new
         * Attraction object
         * 
         * Params: Scanner object to take input
         * 
         * Returns: Newly formed Attraction object
         */
        System.out.print("Enter Attraction Name: ");
        String name = input.nextLine();
        System.out.print("Enter Attraction Description: ");
        String description = input.nextLine();
        return new Attraction(name, description);
    }

    @Override
    public Attraction modifyAttraction(Attraction toModify, Scanner input) {
        /*
         * Modifies the name or description of the specified Attraction Object by using
         * setter functions
         * 
         * Params: Attraction object to be modified, Scanner object to take input
         * 
         * Returns: Modified Attraction object
         */
        System.out.println();
        System.out.println(toModify);
        System.out.println();
        while (true) {
            System.out.println();
            System.out.println("1. Modify name");
            System.out.println("2. Modify description");
            System.out.println("3. Exit");
            int choice = 0;
            System.out.println();
            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input type");
                continue;
            } finally {
                input.nextLine();
            }
            System.out.println();
            if (choice == 1) {
                System.out.print("Enter new name: ");
                String name = input.nextLine();
                toModify.setName(name);
            } else if (choice == 2) {
                System.out.print("Enter new description: ");
                String detail = input.nextLine();
                toModify.setDescription(detail);
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Enter valid option (1-3)");
            }
        }
        return toModify;
    }

    @Override
    public Attraction removeAttraction(ArrayList<Attraction> attractions, int id) {
        /*
         * Searches for the id of the Attraction object to be deleted and removes it
         * from the ArrayList of attractions
         * 
         * Params: ArrayList of attractions available, id of the object to be deleted
         * 
         * Returns: Removed Attraction object from the ArrayList
         */
        Attraction found = null;
        for (Attraction attraction : attractions) {
            if (attraction.getId() == id) {
                found = attraction;
            }
        }
        if (found != null) {
            attractions.remove(found);
        }
        return found;
    }

    @Override
    public Animal addAnimal(Scanner input) {
        /*
         * Takes input for all the required details of Animal class and changes the
         * count of animals accordingly
         * Type allowed are only mammals, amphibians and reptiles in singular
         * 
         * Params: Scanner object to take input
         * 
         * Returns: Newly created Animal object and its subclasses and 'null' if invalid
         * type is entered
         */
        System.out.print("Enter Animal Name: ");
        String name = input.nextLine();
        System.out.print("Enter Animal Type (Singular): ");
        String type = input.nextLine().toLowerCase();
        if (!(type.equals("mammal") || type.equals("reptile") || type.equals("amphibian"))) {
            return null;
        }
        System.out.print("Enter sound for animal: ");
        String sound = input.nextLine();
        System.out.print("Enter history for animal: ");
        String history = input.nextLine();
        int count = 0;
        if (type.equals("mammal")) {
            count = zoo.getMammals();
            zoo.setMammals(++count);
            return new Mammal(name, sound, history);
        } else if (type.equals("amphibian")) {
            count = zoo.getAmphibians();
            zoo.setAmphibians(++count);
            return new Amphibian(name, sound, history);
        } else if (type.equals("reptile")) {
            count = zoo.getReptiles();
            zoo.setReptiles(++count);
            return new Reptile(name, sound, history);
        } else {
            return null;
        }
    }

    @Override
    public Animal modifyAnimal(Animal toModify, Scanner input) {
        /*
         * Modifies the attributes of the specific Animal and its subclasses objects. If
         * the type is changed, new subclass object is created. The count and the id is
         * modified accordingly
         * 
         * Params: Animal object to be modified, Scanner object to take input
         * 
         * Returns: Modified Animal object
         */
        System.out.println();
        System.out.println(toModify);
        String type = toModify.getClass().getSimpleName().toLowerCase();
        String name = toModify.getName();
        String sound = toModify.getSound();
        String history = toModify.getHistory();
        int temp = 0;
        if (type.equals("mammal")) {
            temp = zoo.getMammals();
            zoo.setMammals(--temp);
        } else if (type.equals("amphibian")) {
            temp = zoo.getAmphibians();
            zoo.setAmphibians(--temp);
        } else if (type.equals("reptile")) {
            temp = zoo.getReptiles();
            zoo.setReptiles(--temp);
        }
        while (true) {
            System.out.println();
            System.out.println("1. Modify name");
            System.out.println("2. Modify type");
            System.out.println("3. Modify sound");
            System.out.println("4. Modify history");
            System.out.println("5. Exit");
            int choice = 0;
            System.out.println();
            System.out.print("Enter your choice: ");
            try {
                choice = input.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid input type");
                continue;
            } finally {
                input.nextLine();
            }
            System.out.println();
            if (choice == 1) {
                System.out.print("Enter new name: ");
                name = input.nextLine();
                toModify.setName(name);
            } else if (choice == 2) {
                System.out.print("Enter new type: ");
                type = input.nextLine().toLowerCase();
                if (!(type.equals("mammal") || type.equals("amphibian") || type.equals("reptile"))) {
                    System.out.println("Animal cannot be added");
                    System.out.println("Enter only mammals, amphibians, reptiles");
                    continue;
                }
            } else if (choice == 3) {
                System.out.print("Enter new sound: ");
                sound = input.nextLine();
                toModify.setSound(sound);
            } else if (choice == 4) {
                System.out.print("Enter new history: ");
                history = input.nextLine();
                toModify.setSound(history);
            } else if (choice == 5) {
                break;
            } else {
                System.out.println("Enter valid option (1-3)");
            }
        }
        int save = toModify.getId();
        int count;
        if (type.equals("mammal")) {
            count = zoo.getMammals();
            zoo.setMammals(++count);
            toModify = new Mammal(name, sound, history);
        } else if (type.equals("amphibian")) {
            count = zoo.getAmphibians();
            zoo.setAmphibians(++count);
            toModify = new Amphibian(name, sound, history);
        } else if (type.equals("reptile")) {
            count = zoo.getReptiles();
            zoo.setReptiles(++count);
            toModify = new Reptile(name, sound, history);
        }
        toModify.setId(save);
        return toModify;
    }

    @Override
    public Animal removeAnimal(ArrayList<Animal> zooAnimals, int id) {
        /*
         * Searches for the specific Animal object in the ArrayList of all animals and
         * removes it.
         * 
         * Params: ArrayList of all zoo animals, id of the specific Animal Object to be
         * removed
         * 
         * Returns: Removed Animal object
         */
        Animal found = null;
        for (Animal animals : zooAnimals) {
            if (animals.getId() == id) {
                found = animals;
            }
        }
        if (found != null) {
            zooAnimals.remove(found);
            String type = found.getClass().getSimpleName().toLowerCase();
            int count = 0;
            if (type.equals("mammal")) {
                count = zoo.getMammals();
                zoo.setMammals(--count);
            } else if (type.equals("amphibian")) {
                count = zoo.getAmphibians();
                zoo.setAmphibians(--count);
            } else if (type.equals("reptile")) {
                count = zoo.getReptiles();
                zoo.setReptiles(--count);
            }
        }
        return found;
    }

    @Override
    public void setAttractionPrice(ArrayList<Attraction> attractions, Scanner input) {
        /*
         * Uses the setter function to set the price of the attraction taken as input
         * from the user
         * 
         * Params: ArrayList of all attractions in the zoo, Scanner object to take input
         * 
         * Returns: Void
         */
        System.out.print("Enter ID of attraction to set ticket price: ");
        int id;
        try {
            id = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input type");
            return;
        } finally {
            input.nextLine();
        }
        Attraction found = null;
        for (Attraction attraction : attractions) {
            if (attraction.getId() == id) {
                found = attraction;
            }
        }
        if (found == null) {
            System.out.println("Attraction with given ID not found");
            return;
        } else {
            System.out.print("Enter ticket price: ");
            double price = 0;
            try {
                price = input.nextDouble();
            } catch (Exception e) {
                System.out.println("Invalid input type");
                return;
            } finally {
                input.nextLine();
            }
            if (price <= 0) {
                System.out.println("Invalid price entered");
                return;
            }
            found.setBasicCost(price);
        }
        System.out.println("Price set successfully");
    }

    @Override
    public void modifyStatus(ArrayList<Attraction> attractions, Scanner input) {
        /*
         * Toggles the status between 'OPEN' and 'CLOSE' of the specified attraction
         * 
         * Params: ArrayList of all attractions in the zoo, Scanner object to take input
         * 
         * Returns: void
         */
        System.out.print("Enter ID of attraction to change status: ");
        int id;
        try {
            id = input.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input type");
            return;
        } finally {
            input.nextLine();
        }
        Attraction found = null;
        for (Attraction attraction : attractions) {
            if (attraction.getId() == id) {
                found = attraction;
            }
        }
        if (found == null) {
            System.out.println("Attraction with given ID not found");
            return;
        } else {
            String current = found.getStatus();
            if (current.equals("OPEN")) {
                found.setStatus("CLOSE");
            } else {
                found.setStatus("OPEN");
            }
        }
        System.out.println("Status changed successfully");
    }

    @Override
    public void setDiscount(ArrayList<Discount> discounts, Scanner input) {
        /*
         * Adds, modifies and removes discount from the ArrayList of discounts. Takes
         * category and percentage discount as input and sets the discount code
         * accordingly
         * 
         * Params: ArrayList of all discounts, Scanner object to take input
         * 
         * Returns: void
         */
        while (true) {
            System.out.println("\nSet Discounts:");
            System.out.println("1. Add Discount");
            System.out.println("2. Modify Discount");
            System.out.println("3. Remove Discount");
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
                System.out.print("Enter Discount Category: ");
                String category = input.nextLine();
                System.out.print("Enter Discount Percentage: ");
                int discount = 0;
                try {
                    discount = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input type");
                    continue;
                } finally {
                    input.nextLine();
                }
                if (discount <= 0) {
                    System.out.println("Invalid discount entered");
                    return;
                }
                Discount newDiscount = new Discount(category, discount);
                discounts.add(newDiscount);
                System.out.println("\nDiscount added successfully");
            } else if (adminControl == 2) {
                System.out.print("Enter the code of discount to modify: ");
                String code = input.nextLine().toUpperCase();
                Discount found = null;
                for (Discount discount : discounts) {
                    if (discount.getCode().equals(code)) {
                        found = discount;
                        break;
                    }
                }
                if (found != null) {
                    System.out.println();
                    System.out.println("1. Modify percentage");
                    System.out.println("2. Modify category\n");
                    System.out.print("Enter your choice: ");
                    int modified = 0;
                    try {
                        modified = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    System.out.println();
                    if (modified == 1) {
                        System.out.print("Enter new discount percentage: ");
                        int discount = 0;
                        try {
                            discount = input.nextInt();
                        } catch (Exception e) {
                            System.out.println("Invalid input type");
                            continue;
                        } finally {
                            input.nextLine();
                        }
                        if (discount <= 0) {
                            System.out.println("Invalid discount entered");
                            return;
                        }
                        found.setPercent(discount);
                    } else if (modified == 2) {
                        System.out.print("Enter new category: ");
                        String newCode = input.nextLine();
                        found.setCategory(newCode);
                    } else {
                        System.out.println("Enter valid options (1-2)");
                        continue;
                    }
                    System.out.println("Discount modified successfully");
                } else {
                    System.out.println("Discount not found");
                    continue;
                }
            } else if (adminControl == 3) {
                System.out.print("Enter discount code to remove: ");
                String code = input.nextLine().toUpperCase();
                Discount found = null;
                for (Discount discount2 : discounts) {
                    if (discount2.getCode().equals(code)) {
                        found = discount2;
                        break;
                    }
                }
                if (found == null) {
                    System.out.println("Discount not found");
                    continue;
                } else {
                    discounts.remove(found);
                    System.out.println("Discount removed successfully");
                }
            } else if (adminControl == 4) {
                break;
            } else {
                System.out.println("Enter valid option (1-4)");
                continue;
            }
        }
    }

    @Override
    public void setSpecialDeal(HashMap<Integer, Integer> specialDeals, Scanner input) {
        /*
         * Adds, modifies and removes a special deal from the hashmap of all deals.
         * Takes the minimum attraction number for eligibility of deals and percentage
         * discount
         * as input
         * 
         * Params: Hashmap of all special deals with key as Integer object of minimum
         * attractions and value as the Integer object of discount available, Scanner
         * object
         * to take input
         * 
         * Returns: void
         */
        while (true) {
            System.out.println("\nSpecial Deals: ");
            System.out.println("1. Add a deal");
            System.out.println("2. Modify a deal");
            System.out.println("3. Remove a deal");
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
                System.out.print("Enter minimum attractions: ");
                int minimum = 0;
                try {
                    minimum = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input type");
                    continue;
                } finally {
                    input.nextLine();
                }
                if (minimum <= 0) {
                    System.out.println("Invalid attractions entered");
                    return;
                }
                System.out.print("Enter discount percentage: ");
                int discount = 0;
                try {
                    discount = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input type");
                    continue;
                } finally {
                    input.nextLine();
                }
                if (discount <= 0) {
                    System.out.println("Invalid discount entered");
                    return;
                }
                specialDeals.put(minimum, discount);
                System.out.println("Deal added successfully");
            } else if (adminControl == 2) {
                System.out.print("Enter number of attractions to modify deal: ");
                int minimum = 0;
                try {
                    minimum = input.nextInt();
                } catch (Exception e) {
                    System.out.println("Invalid input type");
                    continue;
                } finally {
                    input.nextLine();
                }
                if (specialDeals.containsKey(minimum)) {
                    System.out.print("Enter new discount percentage: ");
                    int discount = 0;
                    try {
                        discount = input.nextInt();
                    } catch (Exception e) {
                        System.out.println("Invalid input type");
                        continue;
                    } finally {
                        input.nextLine();
                    }
                    if (discount <= 0) {
                        System.out.println("Invalid discount entered");
                        return;
                    }
                    specialDeals.put(minimum, discount);
                    System.out.println("Deal modified successfully");
                } else {
                    System.out.println("Deal not found");
                    continue;
                }
            } else if (adminControl == 3) {
                System.out.print("Enter minimum deal to remove: ");
                int deal = input.nextInt();
                if (specialDeals.remove(deal) == null) {
                    System.out.println("Deal not found");
                    continue;
                } else {
                    System.out.println("Deal removed successfully");
                }
            } else if (adminControl == 4) {
                break;
            } else {
                System.out.println("Enter valid options (1-4)");
            }
        }
    }
}