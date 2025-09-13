import java.util.LinkedList;
import java.util.Scanner;

public class Round {

    private final Game               parent;
    private       LinkedList<String> tempsuiteCase;


    public Round(Game parent ) {
        this.parent = parent;

        this.startRound();
    }

    public LinkedList<String> getSuitecaseContentFromParent(){
        return this.parent.getSuitecase();
    }

    private void startRound(){
        System.out.println("Neue Runde gestartet!");
        this.tempsuiteCase = new LinkedList<>();
        this.tempsuiteCase.addAll(getSuitecaseContentFromParent())  ;
        this.runRound();
    }

    private void runRound(){
        //first round
        if (this.tempsuiteCase.isEmpty() ){
            this.addItemToSuiteCase();
        }else{
            this.makeGuess();
        }
    }

    private void makeGuess(){
        Scanner sc = new Scanner(System.in);
        String itemPassedByPlayer;
        int itemIndex = 1;
        boolean hasError = false;

        String itemToGuess;
        try {
            itemToGuess = this.tempsuiteCase.pop();
        } catch (Exception e) {
            itemToGuess = null;
        }

        while(itemToGuess != null){
            hasError = false;

            System.out.printf("Welcher Gegenstand befindet sich an Position %d? \n", itemIndex);
            itemPassedByPlayer = sc.nextLine();
            if (itemToGuess.compareTo(itemPassedByPlayer) == 0){
                System.out.println("Korrekt!");
                itemIndex++;
            }else{
                this.parent.setFailure_amount(this.parent.getFailure_amount() - 1);
                hasError = true;
                if (this.parent.getFailure_amount() == 0){
                    //game over
                    itemToGuess = null;
                }else{
                    System.out.println("Falsch! Bitte erneut versuchen");
                    System.out.printf("Es ist/sind noch %d Fehler erlaubt \n", this.parent.getFailure_amount() );
                }
            }

            if(!hasError){
                try {
                    itemToGuess = this.tempsuiteCase.pop();
                } catch (Exception e) {
                    itemToGuess = null;
                }
            }
        }

        if (this.parent.getFailure_amount() > 1){
            this.addItemToSuiteCase();
            System.out.println("Runde vorbei!");
        }
    }

    private void addItemToSuiteCase(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcher Gegenstand soll in den Koffer gepackt werden?");
        this.parent.getSuitecase().add(sc.nextLine());

    }

}
