import Include.Client;
import Include.Stadium;
import Include.Seat;
import Include.Seat.Level;
import java.util.InputMismatchException;
import java.util.Scanner;
public class main {
    public static void main(String[] args){
    //    Stadium Stadium=new Stadium();
    //    for(int i= 0; i<3 ;i++ ){
    //     if(i==0){
    //         for(int seat_num=1; seat_num<=2000; seat_num++ ){
    //             Seat seat= new Seat(Level.GRANDSTAND, seat_num);
    //             Stadium.getAvailable().add(seat);
    //         }
    //     }
    //     if(i==2){
    //         for(int seat_num=1; seat_num<=500; seat_num++ ){
    //             Seat seat= new Seat(Level.FIELD, seat_num);
    //             Stadium.getAvailable().add(seat);
    //         }
    //     }
    //     if(i==3){
    //         for(int seat_num=1; seat_num<=1000; seat_num++ ){
    //             Seat seat= new Seat(Level.FIELD, seat_num);
    //             Stadium.getAvailable().add(seat);
    //         }
    //     }
    //    }
       try {
        //Welcome
        System.out.println("WELCOME TO AREA 51 STADIUM : Ticket reservation system\n");
        System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*\n");
        ProgramWrapper.create_stadium();
        ProgramWrapper.menu();

       } catch (InputMismatchException e) {

       }
    }
}

