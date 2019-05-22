package model;

public class ServicePackage {
    private int packageID;
    private String packageName;
    private double hourlyRate;
    private int maxOccupancy;

    public ServicePackage(String packageName, double hourlyRate, int maxOccupancy) {
        this.packageName = packageName;
        this.hourlyRate = hourlyRate;
        this.maxOccupancy = maxOccupancy;
    }

    public ServicePackage() {
    }

    public int getPackageID() {
        return packageID;
    }

    public void setPackageID(int packageID) {
        this.packageID = packageID;
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
}
