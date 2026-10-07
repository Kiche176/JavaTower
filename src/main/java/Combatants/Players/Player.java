import java.util.Scanner;
import java.util.Collection;

public class Player extends Combatant {
    List<Weapon> weapons;
    Scanner scanner;

    public Player() {
        this.weapons = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public Player(
        Job job, Weapon weapon, 
        int STR, int ART, int AGI, int DEF, int RES, 
        int stamina, int mana, int health
    ) {
        this.weapons = new ArrayList<>();
        scanner = new Scanner(System.in);

        setJob(job);
        addWeapon(weapon); equipWeapon(weapon);
        this.STR += STR;
        this.ART += ART;
        this.AGI += AGI;
        this.DEF += DEF;
        this.RES += RES;

        levelStamina(stamina);
        levelMana(mana);
        levelHealth(health);
    }

    public void inputName() {
        String ending = (this.job == null) ? "" : ", young " + this.job;
        String sentence = "What is your name" + ending + "? ";
        while (true) {
            System.out.println(sentence);
            String name = this.scanner.nextLine();

            if (name.length() < 2) {
                System.out.println("Name is too short");
            } else if (name.length() > 20) {
                System.out.println("Name is too long");
            } else {
                this.name = name; break;
            }
        }
    }

    // Levelling UP

    public void levelUP() { this.LVL++; }

    public void levelSTR(int points) { this.STR += points; }
    public void levelART(int points) { this.ART += points; }
    public void levelAGI(int points) { this.AGI += points; }
    public void levelDEF(int points) { this.DEF += points; }
    public void levelRES(int points) { this.RES += points; }

    // add a stat manager class to calculate the amount of each stat you should have per lvl of that stat.
    // e.g. 5 levels of stamina may be 20 stamina but 10 may be 50 stamina.
    public void levelStamina(int points) {
        int dif = this.maxStamina - this.currentStamina;
        this.maxStamina += (points * 4); // the current formula
        this.currentStamina = this.maxStamina - difference;
    }

    public void levelMana(int points) {
        int dif = this.maxMana - this.currentMana;
        this.maxMana += (points * 4);
        this.currentMana = this.maxMana - difference;
    }

    public void levelHealth(int points) {
        int dif = this.maxHealth - this.currentHealth;
        this.maxHealth += (points * 4);
        this.currentHealth = this.maxHealth - difference;
    }


    public void setJOb(Job job) {
        this.job = job;
    }

    public void addWeapon(Weapon weapon) {
        this.weapons.add(weapon);
    }

    public boolean removeWeapon(Weapon weapon) {
        for (int i = 0; i < this.weapons.length(); i++) {
            if (this.weapons.get(i).equals(weapon)) {
                this.weapons.remove(i);
                return true;
            }
        }
        return false;
    }

    private boolean weaponStatCheck(Weapon weapon) {
        return (
            this.STR >= weapon.getRequiredSTR() &&
            this.ART >= weapon.getRequiredART() &&
            this.AGI >= weapon.getRequiredAGI() &&
            this.DEF >= weapon.getRequiredDEF() &&
            this.RES >= weapon.getRequiredRES()
        );
    }

    public boolean equipWeapon(Weapon weapon) {
        for (Weapon w : this.weapons) {
            if (w.equals(weapon)) {
                if (weaponStatCheck(weapon)) {
                    this.weapon = weapon;
                    return true;
                }
            }
        }
        return false;
    }



    public void addAbility(Ability ability) {
        this.abilities.add(ability);
    }

    public void addAbilities(Ability[] abilities) {
        for (Ability a : abilities) {
            this.abilities.add(a);
        }
    }

    public void addAbilities(Collection<Ability> abilities) {
        this.abilities.addAll(abilities);
    }


    public void inputAllocation(int points) {
        String[] statNames = {"Strength", "Arts", "Agility", "Defense", "Resistance", "Stamina", "Mana", "Health"};
        int[] allocations = new int[8];
        int i = 0;

        while (points > 0) {
            System.out.print("How many points would you like to allocate to " + statNames[i] + "? ");
            String ans = this.scanner.nextLine();
            if (ans.isEmpty()) continue;

            int n;
            try {
                n = Integer.parseInt(ans);
            } catch (NumberFormatExcption e) {
                System.out.println("Please enter a valid number");
                continue;
            }

            if (n < 0) {
                System.out.println("Cannot allocate a negative amount of points");
                continue;
            }
            else if (n > points) {
                System.out.println("You don't have that many points available.");
                continue;
            } 
            else {
                allocations[i++] += n;
                i = i % 8;
                points -= n;
            }
            System.out.println("You have " + points + " points left to allocate. ");
        }
        allocateStats(allocations);
    }

    private void allocateStats(int[] stats) {
        levelSTR(stats[0]);
        levelART(stats[1]);
        levelAGI(stats[2]);
        levelDEF(stats[3]);
        levelRES(stats[4]);

        levelStamina(stats[5]);
        levelMana(stats[6]);
        levelHealth(stats[7]);
    }
}