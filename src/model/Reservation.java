package model;

import java.io.Serializable;

public class Reservation implements Serializable {

    private int reservationID;
    private int startTime;
    private double duration;
    private int checkedIn;

    private int userID;
    private int slotID;

    private String username;
    private String packageName;
    private double hourlyRate;
    private int maxOccupancy;
    private double total;

    private String confirmationID;

    public Reservation() {
    }

    public Reservation(int userID, int slotID, int startTime, double duration, int checkedIn) {
        this.userID = userID;
        this.slotID = slotID;
        this.startTime = startTime;
        this.duration = duration;
        this.checkedIn = checkedIn;
    }

    public String getConfirmationID() {
        return confirmationID;
    }

    public void setConfirmationID(String confirmationID) {
        this.confirmationID = confirmationID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getSlotID() {
        return slotID;
    }

    public void setSlotID(int slotID) {
        this.slotID = slotID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public int getCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(int checkedIn) {
        this.checkedIn = checkedIn;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }

    public void setMaxOccupancy(int maxOccupancy) {
        this.maxOccupancy = maxOccupancy;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
