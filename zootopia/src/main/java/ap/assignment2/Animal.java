package ap.assignment2;

public class Animal {
    private String name;
    private static int animalID = 1000;
    private int id;
    private String history;
    private String sound;

    Animal(String name, String sound, String history) {
        this.name = name;
        this.sound = sound;
        this.history = history;
        this.id = ++animalID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void makeSound() {
        System.out.println(sound);
    }

    @Override
    public String toString() {
        return "Animal ID: " + this.id + "\nAnimal Name: " + this.name + "\nAnimal Type: "
                + this.getClass().getSimpleName() + "\nSound: " + this.sound + "\nHistory: " + this.history;
    }
}

class Mammal extends Animal {
    /*
     * Mammal class inheriting from Animal
     */
    Mammal(String name, String sound, String history) {
        super(name, sound, history);
    }
}

class Amphibian extends Animal {
    /*
     * Amphibian class inheriting from Animal
     */
    Amphibian(String name, String sound, String history) {
        super(name, sound, history);
    }
}

class Reptile extends Animal {
    /*
     * Reptile class inheriting from Animal
     */
    Reptile(String name, String sound, String history) {
        super(name, sound, history);
    }
}