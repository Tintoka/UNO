import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void bubbleSort(int[] array){
        int n = array.length;
        for(int i = 0; i < n -1 ; i++)
            for(int j = 0; j < n - i - 1; j++)
                if(array[j] > array[j + 1]){
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Scanner playerChoice = new Scanner(System.in);
        int playerNumber;
        int direction = 1;

        do {
            System.out.print("Enter Valid Number of player : ");
            playerNumber = scanner.nextInt();
        }while(!(playerNumber > 2 && playerNumber < 6));

        Play testPlay = new Play(playerNumber);
        Random rand = new Random();
        int turnCounter = rand.nextInt(playerNumber);


        while(!testPlay.winCheck()){
            testPlay.printAllCards();
            turnCounter %= playerNumber;
            System.out.println("p" + (turnCounter + 1) + "'s turn");
            System.out.println("Enter the number of chosen card");
            if(!testPlay.availableMove(testPlay.getPlayers().get(turnCounter)))
                testPlay.giveCard(1,testPlay.getPlayers().get(turnCounter));
            else if(testPlay.getPlayers().get(turnCounter) instanceof ComputerPlayer) {
                testPlay.putCard(testPlay.getPlayers().get(turnCounter),((ComputerPlayer) testPlay.getPlayers().get(turnCounter)).choice(testPlay.getCurrentCard(), testPlay.getCurrentColor()));
            }
            else{
                int cardNum;
                do {
                    cardNum = playerChoice.nextInt();
                }while(!testPlay.typeCheck(testPlay.getPlayers().get(turnCounter).getCards().get(cardNum)));
                testPlay.putCard(testPlay.getPlayers().get(turnCounter),testPlay.getPlayers().get(turnCounter).getCards().get(cardNum));
            }
            //effect check
            if(testPlay.getCurrentCard() instanceof EffectCard){
                int i = 0;
                String[] effectList= {"ban","switchTurn","draw"};
                for(int j = 0 ; j < 3; j++){
                    if(effectList[j].equals(((EffectCard) testPlay.getCurrentCard()).getEffect()))
                        i = j;
                }
                switch(i){
                    case(0) : {
                        turnCounter += direction;
                        break;
                    }
                    case(1) : {
                        direction *= -1;
                        break;
                    }
                    case(2):{
                        System.out.println("draw card detected");
                        testPlay.giveCard(2,testPlay.getPlayers().get((turnCounter + direction )% playerNumber));
                        break;
                    }
                    default :
                        System.out.println("invalid effect in main");
                }
            }
            if(testPlay.getCurrentCard() instanceof WildCard && ((WildCard) testPlay.getCurrentCard()).getType().equals("draw"))
                testPlay.giveCard(4,testPlay.getPlayers().get((turnCounter + direction) % 3));
            turnCounter += direction;
            try{ Thread.sleep(1000);} catch(Exception e) {
                System.out.println(e);
            }
        }
        //calculate scores
        int[] arr = new int[playerNumber];
        int k = 0;
        for (Player p : testPlay.getPlayers()) {
            testPlay.scoreCalculator(p);
            arr[k] = p.getScore();
            k++;
        }
        //sort them
        bubbleSort(arr);
        //print
        int i = 0;
        for (int num: arr) {
            for (Player p: testPlay.getPlayers()) {
                if(p.getScore() == arr[num])
                    System.out.println("p" + i + ": " + p.getScore());
                i++;
            }
        }

    }
}
