package ap.assignment2;

import java.util.*;

public interface AdminMenu {
    public Attraction createAttraction(Scanner input);

    public Attraction modifyAttraction(Attraction toModify, Scanner input);

    public Attraction removeAttraction(ArrayList<Attraction> attractions, int id);

    public Animal addAnimal(Scanner input);

    public Animal modifyAnimal(Animal toModify, Scanner input);

    public Animal removeAnimal(ArrayList<Animal> zooAnimals, int id);

    public void setAttractionPrice(ArrayList<Attraction> attractions, Scanner input);

    public void modifyStatus(ArrayList<Attraction> attractions, Scanner input);

    public void setDiscount(ArrayList<Discount> discounts, Scanner input);

    public void setSpecialDeal(HashMap<Integer, Integer> specialDeals, Scanner input);
}
