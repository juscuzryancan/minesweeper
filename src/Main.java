import java.util.Scanner;

import static java.lang.Integer.parseInt;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isPlaying = true;
        Grid grid;

        while (isPlaying) {
            System.out.println("Welcome to Minesweeper!");
            System.out.println("Let's initialize the board");
            System.out.println("Please enter how many rows you would like");
            System.out.print("Rows: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            int rows = scanner.nextInt();

            System.out.println("Please enter how many columns you would like");
            System.out.print("Columns: ");
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number: ");
                scanner.next();
            }
            int cols = scanner.nextInt();

            System.out.println("Please enter how many mines you would like");
            System.out.print("Mines: ");
            // not working
            while (!scanner.hasNextInt()) {
                System.out.print("Please input a number that is less than the number of cells within the Board: ");
                scanner.next();
            }

            int numOfMines = scanner.nextInt();
            System.out.println();
            if (numOfMines >= (rows * cols)) {
                System.out.println("You've entered a number greater than the max allowed"); // change this print to something more accurate
                System.out.println("Setting the number of mines to 1 less than the max");
                System.out.println("Good Luck on trying to win :)");
            }

            grid = new Grid(rows, cols, numOfMines);
            grid.printBoard();

            while(!grid.isGameOver()) {
                System.out.println("Please enter an X coordinate");
                System.out.print("X-Coordinate: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Please input a number: ");
                    scanner.next();
                }
                int x = scanner.nextInt();

                System.out.println("Please enter an Y coordinate");
                System.out.print("Y-Coordinate: ");
                while (!scanner.hasNextInt()) {
                    System.out.print("Please input a number: ");
                    scanner.next();
                }
                int y = scanner.nextInt();

                grid.check(x, y);
                grid.printBoard();

            }

        }
        scanner.close();
    }
}
/*
/*
Board
import java.util.*;

/**
 * Board class is to print out board only
 * actually print board is at game class
 * @author GWCP
 *

public class Board {
    private int width;
    private int length;

    public String [][] boardArr;

    public Board(int w, int l) {
        width = w+1;
        length =l+1;

        // +1 because need space for the wall; only board needs it
        // when check the position need to -1
        boardArr = new String[w+1][l+1];

    }
    public void initialBoard(String[][]arr) {


        for(int i=0; i<arr.length; i++){
            for(int i2=0; i2<arr[i].length; i2++) {
                if((i==0) ||( i2==0) || (i2== boardArr.length-1) || (i==boardArr.length-1)) {
                    boardArr[i][i2] = "*";
                }else {
                    boardArr[i][i2]="O";

                }
                System.out.print(arr[i][i2] + " ");
            }
            System.out.println("");
        }

    }

    public void updateBoard(int x, int y) {
        int num = Mine.numMineAround(x,y);

        boardArr[x][y] = String.valueOf(num);

    }
    public void printBoard(String[][]arr) {
        for(int i=0; i<arr.length; i++){
            for(int i2=0; i2<arr[i].length; i2++) {

                System.out.print(arr[i][i2]+" ");
            }
            System.out.println("");
        }

    }
    public static void main(String[]args) {
        //Board b = new Board(4, 4);
        //b.initialBoard();
    }


}

Mine class
import java.util.*;
        import java.util.Random;


/**
 * extends Board class
 *
 * later:
 * change mines to random, it  always changes the num of mines
 * @author GWCP
 *
 */
//public class Mine extends Board{
//    public static int totalMineNum;
//    public static int numMine;
//    public int col;
//    public int row;
//    public static String[][] mineArr ;
//    Random rand = new Random();
//
//    public Mine(int r, int c, int mineNum) {
//
//        super(r+1, c+1);
//        // add a wall
//        row=r+1;
//        col=c+1;
//        this.totalMineNum =  mineNum;
//        numMine = 0;
//
//        mineArr = new String[row][col];
//
//    }
//    /**
//     * this initials Mine board
//     * different than "board"
//     * when check Mine is use mineArr instead of boardArr in board. boardArr just for display
//     *
//     * finished
//     * only print is the game ends
//     */
//    public void initialMineBoard() {
//
//        for (int i = 0; i < mineArr.length; i++) {
//            for (int j = 0; j < mineArr[i].length; j++) {
//                mineArr[i][j] = "O";
//
//            }
//        }
//        for(int n=0; n<totalMineNum; n++) {
//            mineArr[rand.nextInt(row-2)+1 ][rand.nextInt(col-2)+1] = "X";
//
//        }
//
//        // just for checking
//        super.printBoard(mineArr);
//    }
//    /**
//     *
//     * to see if the user steps on a mine
//     * @param newMine
//     * @return true: steps on a mine
//     * 			false: did not step on a mine
//     */
//    public static boolean isMine(int x, int y) {
//        if(mineArr[x][y] .equalsIgnoreCase("X")) {
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * may not need it
//     * @param num
//     */
//	/*public void setMineNum(int num ) {
//		 mineNum = num;
//    }*/
//    /**
//     * must check if there is a mine at the player's position before this method
//     * predict that the position is safe and is not at the edge
//     *
//     * @param x position x of the player
//     * @param y position y of the player
//     * @return
//     */
//
//    public static  int numMineAround(int x, int y){
//
//        if(mineArr[x-1][y-1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x-1][y].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x-1][y+1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x][y-1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x][y+1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x+1][y-1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x+1][y].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        if (mineArr[x+1][y+1].equalsIgnoreCase("X")) {
//            numMine++;
//        }
//        return numMine;
//
//    }
//    // temp. Main method. Just for checking. Shouldn’t have one
//    public static void main(String[]args) {
//        Scanner in = new Scanner(System.in);
//        int normalRow = 18, normalCol = 18; // 16 X 16
//        Mine b = new Mine(8, 8, 10);
//        b.initialMineBoard();
//
//
//        System.out.print("x: ");
//        int x = in.nextInt();
//        System.out.print("y: ");
//        int y = in.nextInt();
//
//        System.out.println("num around ");
//        int num = b.numMineAround(x,y);
//        System.out.print(num);
//    }
//
//}
//
//
//Player class
///**
// * Player.java
// * @author
// * @author
// */
//
//public final class Player extends Character{
//    private int score;
//    int xPos, yPos;
//    /**
//     * Player default constructor
//     * Gives the player a starting
//     * position of [0,0] on the board
//     * and sets score to 0
//     */
//    public Player() {
//        xPos =0;
//        yPos = 0;
//        score = 0;
//
//
//    }
//    public int getX() {
//        return xPos;
//    }
//    public int getY() {
//        return yPos;
//    }
//    public static void setPos(int x, int y) {
//        //define in Game?
//        Board b = new Board(6, 6);
//
//        // update Board
//        b.updateBoard(x,y);
//    }
//    /**
//     * Returns the player's current score
//     * @return the score
//     */
//    public int getScore() {
//        return score;
//    }
//
//    /**
//     * Updates the player's score
//     */
//    public void updateScore() {
//        score++;
//        return;
//    }
//}
//
//
//Register class
///**
// * Register.java
// * @author
// * CIS 36B
// *
// */
//
//import java.util.ArrayList;
//        import java.util.Scanner;
//        import java.io.File;
//        import java.io.FileNotFoundException;
//        import java.io.FileOutputStream;
//        import java.io.IOException;
//        import java.io.PrintWriter;
//
//public class Register {
//
//    static ArrayList<User> userList = new ArrayList<User>();
//    static User user = new User();
//    static int retry = 2;
//
//    /**
//     * Searches for a user whose name and phone number match
//     * those currently stored in the users ArrayList
//     * @param name the name that was input
//     * @param phone the phone number that was input
//     * @param users the ArrayList storing customers on file
//     * @return the index of the user or -1 if not found or -2 the phone number is not correct
//     */
//    public static int searchUserList(String name, String phone, ArrayList<User> userList) {
//        if (name == null || phone == null || userList == null) {
//            return -1;
//        }
//        for (User user : userList) {
//            if (user.getName().matches(name)) {
//                if (user.getPhone().matches(phone)) {
//                    return userList.indexOf(user);
//                }
//                return -2;
//            }
//        }
//        return -1;
//    }
//
//    /**
//     *  Scan the user name and phone number in "./names.txt" and put them in an ArrayList.
//     */
//    private static void updateCredentials() {
//        File names = new File("./names.txt");
//        Scanner namesScanner = null;
//        try {
//            namesScanner = new Scanner(names);
//        } catch (FileNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        String line;
//        String data[];
//        String name, phone;
//        userList.clear();
//        while (namesScanner.hasNextLine()) {
//            line = namesScanner.nextLine();
//            data = line.split(" ");
//            if (data == null || data.length < 2) {
//                System.out.println("The format of names.txt is not correct.");
//                return;
//            }
//            name = data[0];
//            phone = data[1];
//            User n = new User();
//            n.setName(name);
//            n.setPhone(phone);
//            userList.add(n);
//        }
//        namesScanner.close();
//    }
//
//    /**
//     * Check user credentials and create an new account if it's necessary.
//     * @param user the User instance which includes name and phone data.
//     * @param userList the userList ArrayList to search
//     * @return index of the user credentials, or -1 the user doesn't exist, or -2 the credentials are not correct
//     */
//    private static int checkUserCredentials(User user, ArrayList<User> userList) {
//        int index = searchUserList(user.getName(), user.getPhone(), userList);
//        /* The user doesn't exist. Ask the user to create an account. */
//        if(index == -1){
//            System.out.println("Sorry! We don't have your account on file.\nLet's sign up for this game!");
//            System.out.println("Please input your name and phone number.\n");
//            PrintWriter printWriter = null;
//            try {
//                /* Enable append mode in PrintWriter. */
//                printWriter = new PrintWriter (new FileOutputStream(
//                        new File("names.txt"),
//                        true));
//            } catch (FileNotFoundException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            user.inputUserData();
//
//            printWriter.println(user.getName() + " " + user.getPhone());
//            System.out.println("Thank you!\nYour account has now been created.\n");
//            System.out.println("Please try again to login.");
//            printWriter.close();
//            updateCredentials();
//            retry = 2;
//            /* The user exists, but the phone number is not correct. */
//        } else if (index == -2) {
//            System.out.println("Wrong phone number. Please try again.\n");
//        }
//        /* The user credentials are correct. */
//        else {
//            System.out.println("Welcome back, " + userList.get(index).getName() + "!\nLet's play Minesweeper!");
//        }
//        return index;
//    }
//
//
//    /**
//     * Start login procedure
//     */
//    public void startLogin() throws IOException {
//        /* Open names.txt file and create it if it doesn't exist. */
//        File names = new File("./names.txt");
//        if (!names.exists()) {
//            System.out.println("names.txt doesn't exist. Create a new one.");
//            if(!names.createNewFile()) {
//                names.canWrite();
//                names.canRead();
//                System.out.println("names.txt can't be created!");
//                return;
//            }
//        }
//
//        /* Scan the user name and phone number in "./names.txt" and update them in an ArrayList. */
//        updateCredentials();
//
//        System.out.println("Welcome!\n");
//
//        /* Ask the user to input the credentials and he/she has three opportunities. */
//        int index = 0;
//        User user = new User();
//        do {
//
//            user.inputUserData();
//
//            /* If the user just created the account, the user has three more opportunities to login. */
//            if (index == -2 || index == -1) {
//                retry--;
//            }
//            /* Check if the user credentials are correct. */
//            /*
//             * index > 0  : the index of user credentials.
//             * index = -1 : the user doesn't exist
//             * index = -2 : the phone number is not correct.
//             *
//             * */
//        } while (((index = checkUserCredentials(user, userList)) == -1 || index == -2 ) && retry > 0);
//
//        if (index < 0) {
//            System.out.println("Sorry, you don't have the correct user credentials to sign in.");
//        }
//
//
//    }
//    public static void main(String[] args) throws IOException {
//        Register register = new Register();
//        register.startLogin();
//    }
//}
//
//
//User class
//    import java.util.Scanner;
//
///**
// *
// * @author
// * CIS 36B
// *
// */
//
//public class User {
//
//    private String name;
//    private String phone;
//
//    /**
//     * Default constructor - assigns
//     * all member variables to the value
//     * "<variable>  unknown"
//     * e.g. "name unknown"
//     */
//    public User() {
//        name = "name unknown";
//        phone = "phone unknown";
//
//    }
//
//    /**
//     * Constructor for the User class
//     * @param theName the User name
//     * @param theGender the User gender
//     * @param thePhone the User phone
//     * @param theEmail the User email
//     * @param thePassword the User password
//     */
//    public User(String theName,  String thePhone)     {
//        name = theName;
//        phone = thePhone;
//    }
//
//    /**
//     * The user can input name and phone number.
//     * Null string and zero or negative string length are not allowed.
//     */
//    public void inputUserData() {
//        Scanner input = new Scanner(System.in);
//        String name;
//        String phone;
//        while (true) {
//            System.out.print("Enter your name: ");
//            name = input.nextLine();
//            if (name == null || name.length() <= 0) {
//                System.out.println("Wrong format of input name.");
//                continue;
//            }
//            this.name = name;
//            System.out.print("Enter your phone: ");
//            phone = input.nextLine();
//            if (phone == null || phone.length() <= 0) {
//                System.out.println("Wrong format of input phone number.");
//                continue;
//            }
//            this.phone = phone;
//            break;
//        }
//    }
//
//    /**
//     * Returns the first and last name
//     * @return the user name
//     */
//    public String getName() {
//        return name;
//    }
//
//    /**
//     * Returns the phone number
//     * @return the phone number
//     */
//    public String getPhone() {
//        return phone;
//    }
//
//    /**
//     * Returns a phone number formatted
//     * in the style (XXX)XXX-XXXX
//     * @return the formatted phone number
//     */
//    public String getFormattedPhone() {
//
//        String fir=phone.substring(0,4);
//        String sec=phone.substring(4,8);
//        String t=phone.substring(8,12);
//        return "("+fir+")"+sec+"-"+t;
//    }
//    /**
//     * Verifies that phone equals the
//     * phone on file for the user
//     * @param userInput the phone number entered
//     * @return whether the number entered matches
//     * the password stored
//     */
//    public boolean verifyPassword(String userInput) {
//        return userInput.equals(phone);
//    }
//
//    /**
//     * Assigns the user a first and last name
//     * @param user_name the name of the user
//     */
//    public void setName(String user_name) {
//        name = user_name;
//    }
//
//
//    /**
//     * Assigns a phone number to the phone field
//     * @param user_phone the phone number to assign
//     */
//    public void setPhone(String user_phone) {
//        phone=user_phone;
//
//    }
//
//
//
//    /**
//     * Returns the formatted User info as a String
//     */
//    @Override public String toString() {
//        return "Name: " + name
//                + "\nPhone: " + phone;
//
//    }
//
//
//
//}
//
//
//
//
//output :
//
//        We need a names.txt to read and store user information
//        Store user info in same line, like -- 1. jilly 1234567890
//        2. joanna 0987654321
//        3. ryan 5432109876
//
//        Output1:
//
//        Welcome!
//
//        Enter your name: jilly
//        Enter your phone: 1234567890
//        Welcome back, jilly!
//        Let's play Minesweeper!
//
//        output2:
//        Welcome!
//
//        Enter your name: may
//        Enter your phone: 66320
//        Sorry! We don't have your account on file.
//        Let's sign up for this game!
//        Please input your name and phone number.
//
//        Enter your name: may
//        Enter your phone: 6543876542
//        Thank you!
//        Your account has now been created.
//
//        Please try again to login.
//        Enter your name: may
//        Enter your phone: 6543876542
//        Welcome back, may!
//        Let's play Minesweeper!
//
//        Output3:
//        Welcome!
//
//        Enter your name: jilly
//        Enter your phone: 4321876590 //Wrong number
//        Wrong phone number. Please try again.
//
//        Enter your name: jilly
//        Enter your phone: 1234567890
//        Welcome back, jilly!
//        Let's play Minesweeper!
//
//
//
//
//
//
//        import java.util.Scanner;
//public class Game {
//    public static void main(String[]args) {
//        Scanner in = new Scanner(System.in);
//        Board a = new Board();
//        int row;
//        int col;
//        System.out.print("Welcome to MineSweeper");
//        System.out.println("Enter in an X and Y Coordinate to reveal a bomb \nor a blank space ");
//        System.out.println("The top left spot of the board starts at (1, 1)");
//
//        a.initializeBoard();
//        a.showBoard();
//        while(!a.gameOver()) {
//            System.out.print("X-Coordinate: ");
//            col = in.nextInt();
//            System.out.print("Y-Coordinate: ");
//            row = in.nextInt();
//            a.reveal( row-1, col-1);
//            a.showBoard();
//        }
//        System.out.println("Game Over!");
//    }
//}
//
//import java.util.*;
///**
// * game main class
// * game starts here; only run this class.  so others class can not have main.
// *
// *
// * need to improve. "Need to insert the part to see if the user is avaliable in the list. "
// *
// * @author GWCP
// *
// */
//public class Game {
//    //final static int normalRow = 18, normalCol = 18; // 16 X 16
//    final static int normalRow = 6, normalCol = 6; // 4X4  testing
//    public static int row, col;
//    public static ArrayList<User> userArr= new ArrayList<User>();
//    public static void main(String[]args) {
//        Scanner in = new Scanner(System.in);
//        String isMember, username, password;
//        String want;
//        System.out.println("***Welcome to 1.0 version MineSweeper***\n");
//        System.out.print("Have you played this game before(Are you a member)? (y/n): ");
//        isMember=in.next();
//        if(!isMember.equalsIgnoreCase("y")) {
//
//            System.out.print(""
//                    +"\nYou must be a member to play this game! "
//                    + "\nDo you want to be a member?(y/n): ");
//            want = in.next();	// if user wants, call register class to help member register
//            if(want.equalsIgnoreCase("y")) {
//                //Register r = new Register();
//                System.out.println("----------Register----------");
//                User newUser = new User();
//                newUser.inputUserData();			// input users data. problem: haven't store in array = haven't store in the user data file
//                //newUser.setName(newUser.getName());
//
//
//
//
//                gameStart();
//                System.out.println("register (testing)");
//            }else {
//                System.out.println("\nSorry, only member can play this game!"
//                        +"\n\nExit...");
//            }
//        }else {
//            in.nextLine();
//            System.out.print("\n\n----------Information Comfirm-----------"
//                    +"\n\nUsername: ");
//            username = in.nextLine();
//            System.out.print("Phone Number: ");
//            password = in.nextLine();
//
//
//
//            gameStart();
//        }
//        //System.out.println("\nGoodbye!");
//    }
//
//    public static void gameStart() {
//        Player p = new Player();
//        Board b = new Board(normalRow, normalCol);
//
//        //b.initialBoard();
//        System.out.println("Here is a 16X16 game board!"
//                + "\nPlease start to choose a position to begin!");
//        b.initialBoard();
//        userEnter();
//        b.updateBoard(row, col);
//    }
//    public static void userEnter() {
//
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please choose a row: ");
//        row= in.nextInt();
//        System.out.println("Please choose a column: ");
//        col=in.nextInt();
//
//    }
//}