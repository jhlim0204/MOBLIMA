import java.util.ArrayList;

/**
 * This class is used to display seat
 */
public class DisplaySeat{
    
    /**
	 * This static method will print the seat layout
	 * @param seatLayout seatLayout to print
	 */
    public static void displaySeat(ArrayList<ArrayList<Seat>> seatLayout){
            int seatCounterPerRow = 0;
            
            // printing Screen
            int screenWidth = seatLayout.get(0).size() * 3;
            System.out.printf("[");
            for(int k = 0;k<screenWidth;k++){
                System.out.printf("-");
            }
            System.out.printf("Screen");
            for(int k = 0;k<screenWidth;k++){
                System.out.printf("-");
            }
            System.out.printf("]\n");
            

            // access every seats in the arrayList of Seat Layout
            for(int i = 0;i<seatLayout.size();i++){
                for(int j = 0;j<seatLayout.get(i).size();j++){
                    // to print out the walkway
                    if(seatCounterPerRow == 6){
                        System.out.printf("   ");
                        seatCounterPerRow = 0;
                    }
                    
                    if(seatLayout.get(i).get(j).getSeatType() == Seat.SeatType.NORMAL){
                        // for normal Seats
                        seatCounterPerRow++;
                        if(seatLayout.get(i).get(j).isEmpty()){
                            System.out.printf("["+seatLayout.get(i).get(j).getSeatIDAlphabet()+String.format("%3d", seatLayout.get(i).get(j).getSeatIDNum())+"]");
                        }
                        else{
                            System.out.printf("[ XX ]");
                        }
                    }
                    else{
                        // for Couple seats
                        seatCounterPerRow+=2;
                        if(seatLayout.get(i).get(j).isEmpty()){
                            System.out.printf("[   "+seatLayout.get(i).get(j).getSeatIDAlphabet()+String.format("%3d", seatLayout.get(i).get(j).getSeatIDNum())   +"   ]");
                        }
                        else{
                            System.out.printf("[  XX  XX  ]");
                        }
                    }
                }
                seatCounterPerRow = 0;
                System.out.print("\n");
            }
    }
}