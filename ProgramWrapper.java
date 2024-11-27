import Include.*;
import Include.Seat.Level;
import java.util.Scanner;
import java.util.Set;

public class ProgramWrapper {
    private static Stadium stadium = new Stadium();
    private static Client curr_Client = null;
    public static boolean first= true;
    public static int seats_Grandstand=0;
    public static int seats_Field=0;
    public static int seats_Main=0;

    /**
     * Initiates and "Builds" our stadium. 
     * 
     */
    public static void create_stadium(){
       for(int i= 0; i<3 ;i++ ){
        if(i==0){
            for(int seat_num=1; seat_num<=2000; seat_num++ ){
                Seat seat= new Seat(Level.GRANDSTAND, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
        if(i==1){
            for(int seat_num=1; seat_num<=500; seat_num++ ){
                Seat seat= new Seat(Level.FIELD, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
        if(i==2){
            for(int seat_num=1; seat_num<=1000; seat_num++ ){
                Seat seat= new Seat(Level.MAIN, seat_num);
                stadium.getAvailable().add(seat);
            }
        }
    }}
    /**
     * Checks whether the given email is valid
     * 
     * Criteria: 
     * 1. email starts with a letter,
     * 2. email contains '@', 
     * 3. '@' is before '.',
     * 4. '.' is in the fourth to last position,
     * 5. email has only alphanumeric characters excluding '@' and '.'
     * 
     * @param email The email to be checked
     * 
     * @return A boolean, true if the email is valid, false otherwise
     */
    public static boolean isValidEmail(String email){
        int indexOfDot = email.length() - 4;
        if(!(Character.isLetter(email.charAt(0)) && email.contains("@") && email.indexOf("@") < indexOfDot && email.charAt(indexOfDot) == '.')){
            return false;
        }
        
        for(int i = 0; i < email.length(); i++){
            char c = email.charAt(i);
            if(!(Character.isLetterOrDigit(c) || c == '@' || c == '.')){
                return false;
            }
        }
        
        return true;
    }
    
    
    /**
     * Confirms whether the given phone number is within a valid range
     * 
     * @param phone The phone number to be checked
     * 
     * @return A boolean, true if the phone number is valid, false otherwise
     */
    public static boolean isValidPhoneNum(Long phone){
        return Long.parseLong("9999999999") >= phone && phone >= 1000000000;
    }
    
    /**
     * Asks user for Client info and makes a new Client variable with the info
     * 
     * @return A Client variable composed of the information given by the user
     */
    public static Client makeClient(Scanner scan){
        System.out.println("*****Enter client info*****\n");
        String name = "";
        String email = "";
        Long phone = Long.parseLong("123456789");
        
        boolean valid = false;
        while(!valid){
            System.out.println("Enter name: \n");
            try {
                name = scan.nextLine();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again.\n");
                System.out.println();
                scan.next();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter email: \n");
            try {
                email = scan.nextLine();
                valid = isValidEmail(email);
                if(!valid){
                    System.out.println("Invalid Email.");
                    System.out.println("Try again.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Invalid Email.");
                System.out.println();
                scan.next();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter phone number:\n");
            try {
                phone = scan.nextLong();
                valid = isValidPhoneNum(phone);
                if(!valid){
                    System.out.println("Invalid phone number.");
                    System.out.println("Try again.");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Invalid Phone Number.");
                System.out.println();
                scan.next();
            }
        }        
        return new Client(name.toUpperCase(), email.toLowerCase(), phone);
    }

    /**
     * Main Menu , Area where the user has all the available options and activities
     * 
     * the client has 6 options
     */
    public static void menu(){
        boolean valid= false; 
        Scanner scan= new Scanner(System.in);
    
        while (!valid){
        try {
            if(first){
                System.out.println("Please make an account:\n");
                curr_Client= makeClient(scan);
                first=false;
            }
             seats_Grandstand=0;
             seats_Field=0;
             seats_Main=0;
            for (Seat seat : stadium.getAvailable()) {
                if (seat.getLevel() == Level.GRANDSTAND && !stadium.isOccupied(seat)) {
                    seats_Grandstand++;
                } else if (seat.getLevel() == Level.FIELD && !stadium.isOccupied(seat)) {
                    seats_Field++;
                } else if (seat.getLevel() == Level.MAIN && !stadium.isOccupied(seat)) {
                    seats_Main++;
                }
            }
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
            System.out.println("MAIN MENU:\n");
            System.out.println("(1) Reserve Seats\n");
            System.out.println("(2) See Reserved Seats\n");
            System.out.println("(3) Cancel Reservations\n");
            System.out.println("(4) New Client\n");
            System.out.println("(5) View transactions\n");
            System.out.println("(6) Quit\n");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
            int choice= scan.nextInt();
            if(choice==6){
                valid=true;
                System.out.println("Exiting program, Goodbye!\n");
                System.exit(0);
            }
            if(choice==5){
                valid=true;
            }
            else if (choice==4){
                curr_Client= makeClient(scan);
                menu();
                valid=true;
            }
            else if (choice==3){
                cancel_reservations(curr_Client);
                valid=true;
            }
            else if(choice==2){
                see_seats(curr_Client);
                valid=true; 
                menu();
            }
            else if (choice==1){
                reserve_seats(curr_Client);
                valid=true;
                menu();
            }
            else{
                valid=false; 
                System.out.println("This is not a option, Try again:");
            }
        } catch (Exception e) {
            System.out.println("Invalid input, Try again:");
            scan.next();
        }
    }
        
    }

    /**
     * Purpose is to reserve as many seats as the client wishes. 
     * 
     * this method asks for which level the client wants their seats in , shows how many seats each level has available, how many seats they want and which specific seats.
     * if a seat is take, the client has the option to enter the waitlist or choose another seat in the level. 
     * @param Client the client currently logged in.
     * 
     */
    public static  void reserve_seats(Client client){
        Scanner scan= new Scanner(System.in);
        boolean valid= false;
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        System.out.println("Please choose the Level:\n");
        System.out.println("(F) Field        || seats available: "+seats_Field+"\n");
        System.out.println("(M) Main         || seats available: "+seats_Main+"\n");
        System.out.println("(G) Grandstand   || seats available: "+seats_Grandstand+"\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        while(!valid){
            String choice= scan.nextLine();
            try{
                if(choice.equals("G")||choice.equals("g")){//2,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        if (seat_num<1||seat_num>2000){
                            System.out.println("This number is out of range ,please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat= new Seat(Level.GRANDSTAND,seat_num);
                         if(stadium.isOccupied(seat)){
                            System.out.println("That seat is currently unavailable, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N =scan.nextLine();
                            if (Y_N.equals("YES") || Y_N.equals("yes")|| Y_N.equals("Yes")){
                                seat.addToWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Grandstand was successfully reserved!");
                         }
                    }
                    menu();
                }
                else if(choice.equals("F")||choice.equals("f")){//1,000 seats 
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                    
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        if (seat_num<1||seat_num>500){
                            System.out.println("This number is out of range ,please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat= new Seat(Level.FIELD,seat_num);
                         if(stadium.isOccupied(seat)){
                            System.out.println("That seat is currently unavailable, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N =scan.nextLine();
                            if (Y_N.equals("YES") || Y_N.equals("yes")|| Y_N.equals("Yes")){
                                seat.addToWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Field was successfully reserved!");

                         }
                    }
                    menu();
                }
                else if(choice.equals("M")||choice.equals("m")){//500 seats
                    System.out.println("How many seats would you like to reserve:\n");
                    int amount =scan.nextInt();
                   
                    for (int i = 0; i < amount; i++) {
                        System.out.println("Please enter a seat number you would like to reserve:\n");
                        int seat_num =scan.nextInt();
                        if (seat_num<1||seat_num>1000){
                            System.out.println("This number is out of range ,please choose a different seat number\n"); 
                            i--;
                            continue;
                        }
                        Seat seat= new Seat(Level.MAIN,seat_num);
                         if(stadium.isOccupied(seat)){
                            System.out.println("That seat is currently unavailable, would you like to be put on the waitlist?\n");
                            scan.next();
                            String Y_N =scan.nextLine();
                            if (Y_N.equals("YES") || Y_N.equals("yes")|| Y_N.equals("Yes")){
                                seat.addToWaitList(client);
                            }else{
                                System.out.println("Please choose a different seat number\n"); 
                                i--;
                            }
                         }
                         else{
                            stadium.reserve(client,seat);
                            System.out.println("Seat "+seat_num+" in Main was successfully reserved!\n");
                         }
                    }
                    menu();
                }

            }catch(Exception e){
                System.out.println("ERROR: incorrect value type, Sending back to menu\n");
                scan.next();
                menu();
            }
        }


    }

    /**
     * Shows the client their currently reserved seats .
     * 
     * @param client the client currently logged in. 
     * 
     */
    public static void see_seats(Client client){
        System.out.println("Your reserved seats are:");
        Set<Seat> seats= stadium.reservedHashMap.get(client);
        for(Seat seat: seats){
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
            System.out.println("Level: "+ seat.getLevel()+ "|| Seat Number: " +seat.getNumber());
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        }
    }

    /**
     * Cancels the clients reservations if the client wishes to do so
     * 
     * @param client The client currently logged in. 
     * 
     */
    public static void cancel_reservations(Client client){
        if(stadium.cancel(client)){
            System.out.println("Reservation succesfully cancled!");
            System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        }
        menu();
    }

}
