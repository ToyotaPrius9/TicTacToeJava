

import java.util.*; //some basic imports

public class Main{

    static ArrayList<Integer>playerPositions = new ArrayList<Integer>(); //store position of player
    static ArrayList<Integer>cpuPositions = new ArrayList<Integer>(); //store position of cpu


    public static void main(String[] args) {
        char [] [] gameboard = { {' ', '|',' ','|',' '}, //gameboard, and some epic art for it
                {'-', '+','-','+','-'},
                {' ', '|',' ','|',' '},
                {'-', '+','-','+','-'},
                {' ', '|',' ','|',' '} };

        printgameboard(gameboard); //use the function created for printing the board

        while(true){ //while condition to keep the game running
            Scanner scan = new Scanner(System.in); //create a scanner
            System.out.println("Enter an int 0-9"); //prompt user to enter a placement
            int pos = scan.nextInt(); //scan the input, and store it to a variable
            while(playerPositions.contains(pos)|| cpuPositions.contains(pos)){ //while statement to check overlap of positions, and ask again if overlapped placement
                System.out.println("No -_-"); // no
                pos = scan.nextInt(); //scan, again...
            }

            placePiece(gameboard,pos,"player");// use the function created to place the X on the board


            Random rand = new Random(); // new random stored
            int cpuPos = rand.nextInt(9)+1; // create a new random between 1 and 9, and store inside variable

            while(playerPositions.contains(cpuPos)|| cpuPositions.contains(cpuPos)){ //while loop for case of overlap

                cpuPos = rand.nextInt(9)+1;
            }
            placePiece(gameboard,cpuPos,"cpu"); //place cpu



            printgameboard(gameboard); //print the gameboard
            checkwinner();

        }

    }
    public static void printgameboard(char[][] gameboard){ //function to print the game board
        for  (char[] row : gameboard){ //for loop, to print out the board
            for (char c : row){
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placePiece(char[] [] gameboard, int pos, String user){ //function to place pieces into positions
        char symbol = ' ';

        if(user.equals("player")){ // if statement to check if it's a user input. If so, make symbol X and register
            symbol = 'X';
            playerPositions.add(pos);
        }

        else if (user.equals("cpu")){ // same thing but for the cpu this time
            symbol = 'O';
            cpuPositions.add(pos);
        }



        switch(pos){ // alot of cases with positions of the gameboard, for placement of symbol according to input
            case 1:
                gameboard[0][0] = symbol;
                break;
            case 2:
                gameboard[0][2] = symbol;
                break;
            case 3:
                gameboard[0][4] = symbol;
                break;
            case 4:
                gameboard[2][0] = symbol;
                break;
            case 5:
                gameboard[2][2] = symbol;
                break;
            case 6:
                gameboard[2][4] = symbol;
                break;

            case 7:
                gameboard[4][0] = symbol;
                break;
            case 8:
                gameboard[4][2] = symbol;
                break;
            case 9:
                gameboard[4][4] = symbol;
                break;
        }
    }
    public static String checkwinner(){ // function to check for a winner or a tie

        List topRow = Arrays.asList(1,2,3); // create all scenarios of winning, and store them in respective lists
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);

        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List cross1 = Arrays.asList(1,5,9);
        List cross2 = Arrays.asList(7,5,3);

        List<List> winningCon = new ArrayList<List>(); //store all these lists in one list
        winningCon.add(topRow);
        winningCon.add(midRow);
        winningCon.add(botRow);

        winningCon.add(leftCol);
        winningCon.add(midCol);
        winningCon.add(rightCol);

        winningCon.add(cross1);
        winningCon.add(cross2);

        for(List l : winningCon){ //for loop, which prints out a message according to conditions
            if (playerPositions.containsAll(l)){
                System.out.println("You Win!\n:)");
                break;
            }

            else if (cpuPositions.containsAll(l)){
                System.out.println("Skill Issue\n:(");
                break;
            }

            if (playerPositions.size() + cpuPositions.size() == 9){
                System.out.println("Tie...\n:/");
                break;
            }
        }

        //the "break" does not work sadly... does not stop the loop successfully...

        return "";
    }
}

