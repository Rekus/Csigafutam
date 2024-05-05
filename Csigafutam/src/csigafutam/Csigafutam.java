package csigafutam;

import java.util.Random;

public class Csigafutam {

    public static void main(String[] args) {
       
    }
    
}

class Csiga {
    private final String szin;
    private int helyezes;
    
    public Csiga(String szin) {
        this.szin = szin;
        helyezes = 0;
    }
    
    public void move() {
        Random rand = new Random();
        helyezes += rand.nextInt(4); // 0..3
    }
    
    public void accelerate() {
        helyezes *= 2;
    }
    
    public String getSzin() {
        return szin;
    }
    
    public int getHelyezes() {
        return helyezes;
    }
}

class Csigafutam {
    private final Csiga[] csiga;
    private String bet;

    public Csigafutam() {
        csiga = new Csiga[]{new Csiga("piros"), new Csiga("zöld"), new Csiga("kék")};
        bet = null;
    }

    public void placeBet(String csigaSzin) {
        bet = csigaSzin;
    }

    public void playKor() {
        for (Csiga csiga : csiga) {
            csiga.move();
        }
    }

    public void playGame(int kor) {
        for (int i = 0; i < 5; i++) {
            playKor();
            checkAcceleration();
            printHelyezes();
            System.out.println("-------------------");
        }
    }

    private void checkAcceleration() {
        Random rand = new Random();
        if (rand.nextDouble() < 0.2) {
            Csiga gyorsCsiga = csiga[rand.nextInt(csiga.length)];
            gyorsCsiga.accelerate();
        }
    }

    private void printHelyezes() {
        sortCsigaByHelyezes();
        for (Csiga csiga : csiga) {
            System.out.println(csiga.getSzin() + ": " + csiga.getHelyezes());
        }
    }

    private void sortCsigaByHelyezes() {
        for (int i = 0; i < csiga.length - 1; i++) {
            for (int j = i + 1; j < csiga.length; j++) {
                if (csiga[i].getHelyezes() < csiga[j].getHelyezes()) {
                    Csiga temp = csiga[i];
                    csiga[i] = csiga[j];
                    csiga[j] = temp;
                }
            }
        }
    }

    public void determineWinner() {
        sortCsigaByHelyezes();
        if (bet != null && bet.equals(csiga[0].getSzin())) {
            System.out.println("Gratulálok, nyertél!");
        } else {
            System.out.println("Sajnos nem nyertél.");
        }
    }

    public static void main(String[] args) {
        Csigafutam futam = new Csigafutam();
        futam.placeBet("piros");
        futam.playGame(5);
        futam.determineWinner();
    }
}