import Include.Client;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main {
    public static void main(String[] args){
       Scanner scanner=new Scanner(System.in);
       main obj= new main();

       try {
        //Welcome
        System.out.println("WELCOME TO AREA 51 STADIUM : Ticket reservation system\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        Client new_client=ProgramWrapper.makeClient(scanner);
        ProgramWrapper.menu();

       } catch (InputMismatchException e) {

       }
    }
}
