package QuesOne;

interface Rotatable {
    public void rotate();
}

interface RotateAndFly extends Rotatable {
    public void fly();
}

class Rotator implements Rotatable {
    @Override
    public void rotate() {
        System.out.println("Rotating");
    }
}

class Flyer implements RotateAndFly {
    @Override
    public void rotate() {
        System.out.println("Rotating");
    }

    @Override
    public void fly() {
        System.out.println("Flying");
    }
}

class Helicopter implements RotateAndFly {
    @Override
    public void rotate() {
        System.out.println("Rotating");
    }

    @Override
    public void fly() {
        System.out.println("Flying");
    }

    public void drive(Rotatable r) {
        System.out.println(r.getClass());
    }
}

public class Ques1 {
    public static void main(String[] args) {
        Rotator r1 = new Rotator();
        Flyer f1 = new Flyer();
        Helicopter h1 = new Helicopter();
        // Argument for drive of type rotatable
        h1.drive(r1); // prints Rotator
        h1.drive(f1); // Flyer
        h1.drive(h1); // Helicopter
    }
}
