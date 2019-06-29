package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Theatre {
    private String TheatreName;
    private List<Seat> seatList;

    public Theatre(String name, int numOfRows, int numOfCols) {
        // At most 26 rows
        if(numOfRows > 26) {
            throw new IllegalArgumentException("There is at most 26 rows");
        }
        this.TheatreName = name;
        this.seatList = new ArrayList<Seat>();

        for(char i = 'A'; i < 'A' + numOfRows; i++) {
            for(int j = 1; j <= numOfCols; j++) {
                if(i < 'A' + 5) {
                    Seat s = new Seat(i + String.format("%02d", j), 100.00);
                    seatList.add(s);
                }
                else if(i >= 'A' + 5 && i < 'A' + 10) {
                    Seat s = new Seat(i + String.format("%02d", j), 70.00);
                    seatList.add(s);
                }
                else if(i >= 'A' + 10) {
                    Seat s = new Seat(i + String.format("%02d", j), 35.00);
                    seatList.add(s);
                }
            }
        }
    }

    public String getTheatreName() {
        return TheatreName;
    }

    public boolean reserveSeat(String seatNum) {
        int left = 0;
        int right = seatList.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            Seat midSeat = seatList.get(mid);
            // if cmp is less than 0 means mid is smaller than target
            int cmp = midSeat.getSeatNum().compareTo(seatNum);
            if(cmp == 0) {
                return seatList.get(mid).reserve();
            }
            else if(cmp < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println("There is no such seat " + seatNum);
        return false;
    }


    public boolean cancelReservation(String seatNum) {
        Seat target = new Seat(seatNum, 0.00);

        int left = 0;
        int right = seatList.size() - 1;
        while(left <= right) {
            int mid = left + (right - left) / 2;
            Seat midSeat = seatList.get(mid);
            // if cmp is less than 0 means mid is smaller than target
            int cmp = midSeat.compareTo(target);
            if(cmp == 0) {
                return seatList.get(mid).cancel();
            }
            else if(cmp < 0) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        System.out.println("There is no such seat " + seatNum);
        return false;
    }


    public void printAllSeat() {
        for(Seat s : seatList) {
            System.out.print(s.getSeatNum() + " ");
        }
        System.out.println();
    }

    public void checkAvailableSeatsByPrice(double price) {
        List<Seat> result = new ArrayList<Seat>();
        for(Seat s : seatList) {
            if(s.getSeatPrice() <= price && !s.reserved) {
                result.add(s);
            }
        }
        if(result.size() == 0) {
            System.out.println("There is no result matches your price.");
            return ;
        }
        System.out.println("The seats matches your search:");
        for(Seat seat : result) {
            System.out.print(seat.getSeatNum() + " $" + seat.getSeatPrice() + ", ");
        }
        System.out.println();
    }

    public void checkAvailableSeatByRow(char row) {
        List<Seat> result = new ArrayList<Seat>();
        for(Seat s : seatList) {
            if(s.getSeatNum().charAt(0) == row && !s.reserved) {
                result.add(s);
            }
        }
        if(result.size() == 0) {
            System.out.println("There is no result matches your price.");
            return ;
        }
        System.out.println("The seats matches your search:");
        for(Seat seat : result) {
            System.out.print(seat.getSeatNum() + " $" + seat.getSeatPrice() + ", ");
        }
        System.out.println();
    }

    public void resetAllSeats() {
        for(Seat s : seatList) {
            s.reserved = false;
        }
        System.out.println("All seats are not reserved now.");
    }

    private class Seat implements Comparable<Seat>{
        private final String seatNum;
        private boolean reserved;
        private double seatPrice;

        public Seat(String seatNum, double seatPrice) {
            this.seatNum = seatNum;
            this.seatPrice = seatPrice;
            this.reserved = false;
        }

        @Override
        public int compareTo(Seat o) {
            // Use .compareToIgnoreCase() to compare 2 strings
            return this.seatNum.compareToIgnoreCase(o.getSeatNum());
        }

        public String getSeatNum() {
            return seatNum;
        }

        public double getSeatPrice() {
            return seatPrice;
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + this.getSeatNum() + " is reserved for you. Price is $" + this.getSeatPrice());
                return true;
            }
            System.out.println(this.seatNum + " is not available.");
            return false;
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation for " + seatNum + " is cancelled. You have refund $" + this.getSeatPrice());
                return true;
            }
            System.out.println(seatNum + " is already available");
            return false;
        }
    }
}
