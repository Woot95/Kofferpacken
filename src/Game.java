import java.util.LinkedList;
import java.util.Scanner;

public class Game {

    public LinkedList<String> suitecase;
    int failure_amount;

    public Game() {
        suitecase  = new LinkedList<>();

        this.start();
    }

    public int getFailure_amount() {
        return failure_amount;
    }

    public void setFailure_amount(int failure_amount) {
        this.failure_amount = failure_amount;
    }

    public LinkedList<String> getSuitecase() {
        return suitecase;
    }

    public void setSuitecase(LinkedList<String> suitecase) {
        this.suitecase = suitecase;
    }

    void start(){
        this.failure_amount = this.welcomePlayer();
        this.run();
    }

    void run(){
        String item;
        Round currentRound;

        while(getFailure_amount() >= 1){
            currentRound = new Round(this);
        }

        if (getFailure_amount() == 0){
            System.out.println("Game over");
        }
    }


    private int welcomePlayer(){
        System.out.println("Willkommen zum Koffer packen Spiel");
        System.out.println("Wie viele Fehler sind erlaubt?");
        return new Scanner(System.in).nextInt();
    }

    public static void main(String[] args) {
    }
}
