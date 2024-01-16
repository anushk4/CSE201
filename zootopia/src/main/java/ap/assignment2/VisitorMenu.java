package ap.assignment2;

import java.util.*;

public interface VisitorMenu {
    public void addTickets(Attraction attraction, int tickets);

    public int buyMembership(int pack, ArrayList<Discount> discounts, Scanner input);

    public int getDiscount(ArrayList<Discount> discounts, Scanner input);

    public void visitAnimal(Animal visiting, Scanner input);

    public void visitAttraction(Attraction visited);

    public void leaveFeedback(ArrayList<String> Feedback, Scanner input);
}
