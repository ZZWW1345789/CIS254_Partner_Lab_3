/**
 * Description:
 *      This program provides 2 games(Guess a Number & Rock-Paper-Scissors)
 *  for the user to play
 *
 * @author Z Wang
 * @since 3/22/2025
 */

import java.util.Random;
import java.util.Scanner;


/**
 * Description:
 *      This is the main class
 */
public class GuessingGames {


    /**
     * Description:
     *      This method will print a menu
     */
    public static void menu()
    {
        System.out.println("||===============================||");
        System.out.println("||       SELECT YOUR GAME        ||");
        System.out.println("||===============================||");
        System.out.println("||      1.Guess a Number!!!      ||");
        System.out.println("||===============================||");
        System.out.println("||      2.Rock-Paper-Scissor!!!  ||");
        System.out.println("||===============================||");
        System.out.println("||            0.Exit             ||");
        System.out.println("||===============================||");
    }


    /**
     * Description:
     *      This Method runs a guessing game, it will
     * generate a random answer for player to guess,
     * by the end of the game it will display if
     * the player win or lose, then it will ask if
     * the player wants to play again, if yes
     * restart the game again, if no returning
     * to the main menu
     *
     *
     * @param in a Scanner object
     * @param rand  a Random object
     */
    public static void guessANumber(Scanner in, Random rand)
    {
        /*
            Initialization and Declaration(Variable)
         */

        final int MAX_NUM = 101;//set a constant max value of random numbers

        final int MIN_NUM = 1;  //set a constant min value of random numbers

        final int TEMP_OF_TRIES = 5;//set a constant of max tries of each game

        int answer;//this variable will store value for random number

        int playerGuess;//this variable will store player's input

        boolean win;//check flag to see if the player wins

        /*
            Game starts here
        */
        do
        {
            /*
                Initialization Game
             */
            win = false;//Initializing the flag
            answer = rand.nextInt(MIN_NUM,MAX_NUM);//Initializing the answer

            /*
                Introduction
            */
            System.out.println("\n\nWelcome to Guess a Number, I will pick a number from 1~100");
            System.out.println("and your job is to find it, you have 5 tries\n\n");

            /*
                Main Game program-(Guessing the Number)
             */
            for(int i = 0; i < TEMP_OF_TRIES; ++i)//for loop repeat for maximum 5 times
            {
                playerGuess = inputCheckerInt(in);//store user's input to playerGuess

                if(playerGuess == answer)//if player's guessing matches the answer
                {
                    win = true;//set winning to true
                    break;//then break the loop
                }
                else if(playerGuess > answer)//if the guessing is too high
                {
                    System.out.printf("Opos, your number is too high, try something smaller.(%d tries left)\n", TEMP_OF_TRIES-i-1);//prompt
                }
                else//if the guessing is too low
                {
                    System.out.printf("Opos, your number is too small, try something larger.(%d tries left)\n", TEMP_OF_TRIES-i-1);//prompt
                }
            }

            /*
                End of the game-Checking Condition(win or lose)
             */
            if(win)//if the player wins(true)
            {
                System.out.printf("\n\nCongrats, %d is the number, you just won the game!!!\n\n", answer);//prompt for win
            }
            else
            {
                System.out.printf("\n\nBoo, you just lost! The answer is %d\n\n" , answer);//prompt for lose

            }

        }
        while(playAgain(in));//ask if player wants to play again
    }


    /**
     * Description:
     *      This Method runs a Rock-Paper-Scissors game
     * the system will generate a random choice of Rock
     * Paper and Scissors, player needs to input a number
     * to choose from rock, paper, and scissors. This
     * method will determine if the player win, lose,
     * or tie in the game. After the game ended, it will
     * ask if the player wants to play again, if yes, it
     * will repeat the routine, if no, returning to the
     * main menu. (ps: a "Bonus" is hidden in this game)
     *
     *
     * @param in a Scanner object
     * @param rand a Random object
     */
    public static void rockPaperScissors(Scanner in, Random rand)
    {
        /*
            Initialization and declaration of variables
         */
        final int MAX_NUM = 4;//set a constant max value of random numbers

        final int MIN_NUM = 1;//set a constant min value of random numbers

        int answer;//this variable will store value for random number

        int playerGuess;//this variable will store player's input

        /*
            Game starts here
         */
        do
        {
            /*
                Initialization
            */
            answer = rand.nextInt(MIN_NUM,MAX_NUM);

            /*
                Introduction
            */
            System.out.println("\n\nWelcome to Rock-Paper-Scissors, please enter your choice");
            System.out.println("1.Rock, 2.Paper, 3.Scissors");


            /*
                Main Game program-(Guessing the Number)
            */
            while(true)//check if player's input is in range(1~4)
            {
                playerGuess = inputCheckerInt(in);//get an input and stored it to playerGuess

                if (playerGuess > 0 && playerGuess < 5)//if player's input is in range
                {
                    break;//break the loop
                }
                else//if out of range repeat the loop
                {
                    System.out.println("Invalid input, please select from 1~3");
                }
            }


            /*
                End of the game-Checking Condition(win, lose, or tie)
             */
            if(playerGuess == 4)//Bonus!!! try it out
            {
                System.out.printf("Gun(Your Choice) vs %s(Mine Choice)\n\n", isRockPaperScissors(answer));
                System.out.println("You cheater!!! Shame on you!!!!!!\n");
            }
            else if(playerGuess == answer)//if player's choice == answer, it is a tie
            {
                System.out.printf("\n%s(Your Choice) vs %s(Mine Choice)\n",isRockPaperScissors(playerGuess), isRockPaperScissors(answer));
                System.out.println("It's a tie!!\n");
            }
            /*
                for rock = 1, paper = 2, scissors = 3
                all 3 winning possible are 1 and -2:
                rock        -       scissors    = (1-3) = -2
                paper       -       rock        = (2-1) =  1
                scissors    -       paper       = (3-2) =  1
             */
            else if((playerGuess-answer) == 1 || (playerGuess-answer) == -2)
            {
                System.out.printf("\n%s(Your Choice) vs %s(Mine Choice)\n",isRockPaperScissors(playerGuess), isRockPaperScissors(answer));
                System.out.println("You win!!!\n");
            }
            /*
                for rock = 1, paper = 2, scissors = 3
                all 3 losing possible are -1 and 2:
                rock        -       paper       = (1-2) = -1
                paper       -       scissors    = (2-3) = -1
                scissors    -       rock        = (3-1) =  2
             */
            else if((playerGuess-answer) == -1 || (playerGuess-answer) == 2 )
            {
                System.out.printf("\n%s(Your Choice) vs %s(Mine Choice)\n",isRockPaperScissors(playerGuess), isRockPaperScissors(answer));
                System.out.println("You lose!!!\n");
            }

        }
        while(playAgain(in));//ask the player if he wants to restart
    }


    /**
     *Description:
     *  This method will convert numbers to
     *  a line of String of rock paper or scissors
     *
     * @param num an int variable
     * @return a String
     */
    public static String isRockPaperScissors(int num)
    {
        if(num == 1)//if the number is 1

        {
            return "Rock!";//it is a Rock!
        }
        else if(num == 2)//if the number is 2
        {
            return  "Paper!!";//it is a Paper!!
        }
        else
        {
            return  "Scissors!!!";//has to be a Scissor then
        }
    }



    /**
     * Description:
     *      This Method will ask player if they
     * want to start the game again, if yes the
     * program will return true, if no the program
     * will return false, any invalid input will
     * be detected and ask for valid input.
     *
     * @param in a Scanner object
     * @return boolean(ture-if player wants to repeat, false if no repeat)
     */
    public static boolean playAgain(Scanner in)
    {

        String choice;//to store user's choice

       do
       {
           System.out.println("do you want to play again? (Y/N): ");//prompt

           choice = in.nextLine();//store user's input

           if(choice.equalsIgnoreCase("Y"))//if player typed "Y"
           {
               System.out.println("\n\nStarting an other game...");//prompt
               return true;//return true and break the loop
           }
           else if(choice.equalsIgnoreCase("N"))//if player typed "N"
           {
               System.out.println("\n\nReturning to the main menu...");//prompt
               return false;//return false and break the loop
           }
           else//if nor "Y" or "N" detected
           {
               System.out.print("Invalid input please enter Y/N, ");//loop doesn't break(start over)
           }
       }
       while(true);//infinite loop, only breaks if user input the valid value
    }


    /**
     * Description:
     *      This method will check if the user input the correct
     * data type, if the input is valid, it will return the user's
     * input, if not it will keep ask the user to input the
     * correct input.
     *
     * @param   in a Scanner object
     * @return  an int
     */
    public static int inputCheckerInt(Scanner in)
    {
        int val;

        System.out.print("please enter a number: ");//prompt

        if(!in.hasNextInt())//if the next input is not int
        {
            System.out.print("Invalid input, ");//prompt invalid input
            in.nextLine();//clean the buffer
            return inputCheckerInt(in);//recall the method
        }
        else//if Scanner has an int
        {
            val = in.nextInt();//store input in val
            in.nextLine();//clean the buffer \n
            return val;//return val
        }
    }


    /**
     * The Program starts here
     *
     * @param args a String argument
     */
    public static void main(String[] args)
    {

        //Random
        Random random = new Random();

        //Scanner
        Scanner input = new Scanner(System.in);//declare Scanner input

        //int
        int choice; //to store user's choice

        do
        {
            menu();//invoke the menu
            choice = inputCheckerInt(input);//ask user for input

            switch (choice)
            {
                case 0://exit

                    input.close();//close the object before exit
                    random = null;//reclaim the memory

                    System.exit(0);//exit here

                case 1://game 1: Guess a Number
                    guessANumber(input,random);
                    break;

                case 2://game 2: Rock-Paper-Scissors
                    rockPaperScissors(input,random);
                    break;

                /*
                    if user's input is out of range, go back to the
                    notify the user and go back to the beginning of the
                    loop
                 */
                default:
                    System.out.println("Invalid input, please select from 0~2");//prompt
            }


            //print separate line for each routine
            System.out.println("\n\n-----------separate line-------------------\n\n");
        }
        while(true);//infinite loop, user's can break it inside
    }
}


