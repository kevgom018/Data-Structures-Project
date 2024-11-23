import Include.*;
import java.util.Scanner;
import java.util.ArrayList;

//  .----------------.  .----------------.  .----------------.  .-----------------.
// | .--------------. || .--------------. || .--------------. || .--------------. |
// | | ____    ____ | || |      __      | || |     _____    | || | ____  _____  | |
// | ||_   \  /   _|| || |     /  \     | || |    |_   _|   | || ||_   \|_   _| | |
// | |  |   \/   |  | || |    / /\ \    | || |      | |     | || |  |   \ | |   | |
// | |  | |\  /| |  | || |   / ____ \   | || |      | |     | || |  | |\ \| |   | |
// | | _| |_\/_| |_ | || | _/ /    \ \_ | || |     _| |_    | || | _| |_\   |_  | |
// | ||_____||_____|| || ||____|  |____|| || |    |_____|   | || ||_____|\____| | |
// | |              | || |              | || |              | || |              | |
// | '--------------' || '--------------' || '--------------' || '--------------' |
//  '----------------'  '----------------'  '----------------'  '----------------' 

public class ProgramWrapper {

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
        System.out.println("*****Enter client info*****");
        String name = "";
        String email = "";
        Long phone = Long.parseLong("123456789");
        
        boolean valid = false;
        while(!valid){
            System.out.println("Enter name: ");
            try {
                name = scan.next();
                valid = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                System.out.println("Try again.");
                System.out.println();
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter email: ");
            try {
                email = scan.next();
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
            }
        }
        
        valid = false;
        while(!valid){
            System.out.println("Enter phone number: ");
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
            }
        }        
        return new Client(name.toUpperCase(), email.toLowerCase(), phone);
    }

}
