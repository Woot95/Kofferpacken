import java.util.LinkedList;
import java.util.Scanner;

public class Round {

    private final Game               parent;
    private       LinkedList<String> tempsuiteCase;


    public Round(Game parent ) {
        this.parent = parent;

        this.startRound();
    }

    private Game getGame(){
        return this.parent;
    }

    private void setTempsuiteCase(LinkedList<String> tempsuiteCase){
        this.tempsuiteCase = new LinkedList<>();
        this.tempsuiteCase.addAll(tempsuiteCase);
    }

    private LinkedList<String> getTempsuiteCase(){
        return this.tempsuiteCase;
    }

    public LinkedList<String> getSuitecaseContentFromParent(){
        return getGame().getSuitecase();
    }

    private void startRound(){
        System.out.println("Neue Runde gestartet!");
        setTempsuiteCase(getSuitecaseContentFromParent());
        runRound();
    }

    private void runRound(){
        //first round
        if (getTempsuiteCase().isEmpty() ){
            addItemToSuiteCase();
        }else{
            makeGuess();
        }
    }

    private void makeGuess(){
        Scanner sc = new Scanner(System.in);
        String itemPassedByPlayer;
        int itemIndex = 1;
        boolean hasError = false;

        String itemToGuess;
        try {
            itemToGuess = getTempsuiteCase().pop();
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
                getGame().setFailure_amount(getGame().getFailure_amount() - 1);
                hasError = true;
                if (getGame().getFailure_amount() == 0){
                    //game over
                    itemToGuess = null;
                }else{
                    System.out.println("Falsch! Bitte erneut versuchen");
                    System.out.printf("Es ist/sind noch %d Fehler erlaubt \n", getGame().getFailure_amount() );
                }
            }

            if(!hasError){
                try {
                    itemToGuess = getTempsuiteCase().pop();
                } catch (Exception e) {
                    itemToGuess = null;
                }
            }
        }

        if (getGame().getFailure_amount() > 0){
            this.addItemToSuiteCase();
            System.out.println("Runde vorbei!");
        }
    }

    private void addItemToSuiteCase(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcher Gegenstand soll in den Koffer gepackt werden?");
        getGame().getSuitecase().add(sc.nextLine());

    }

}
