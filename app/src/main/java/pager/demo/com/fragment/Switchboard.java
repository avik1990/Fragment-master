package pager.demo.com.fragment;

/**
 * Created by user2 on 27-02-2018.
 */

public class Switchboard {

    public  boolean isHallRoomswitch() {
        return hallRoomswitch;
    }

    public  void setHallRoomswitch(boolean hallRoomswitch) {
        this.hallRoomswitch = hallRoomswitch;
    }

    public  boolean isBathRoomswitch() {
        return bathRoomswitch;
    }

    public  void setBathRoomswitch(boolean bathRoomswitch) {
        this.bathRoomswitch = bathRoomswitch;
    }

    public  boolean isBedRoomswitch() {
        return bedRoomswitch;
    }

    public  void setBedRoomswitch(boolean bedRoomswitch) {
        this.bedRoomswitch = bedRoomswitch;
    }

    public static   boolean hallRoomswitch=false;
    public  static boolean bathRoomswitch=false;
    public  static boolean bedRoomswitch=false;




}
