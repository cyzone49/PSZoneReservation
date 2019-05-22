package command.commandData;

import model.commandModels.CommandType;

public class AddServicePackageCommandData extends ServerCommandData {
    private String packageName;
    private double hourlyRate;
    private int maxOccupancy;

    public AddServicePackageCommandData(String packageName, double hourlyRate, int maxOccupancy) {
        setCommandType(CommandType.ADD_PACKAGE);
        this.packageName = packageName;
        this.hourlyRate = hourlyRate;
        this.maxOccupancy = maxOccupancy;
    }

    public String getPackageName() {
        return packageName;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public int getMaxOccupancy() {
        return maxOccupancy;
    }
}
