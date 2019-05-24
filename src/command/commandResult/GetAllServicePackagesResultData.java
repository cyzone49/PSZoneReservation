package command.commandResult;

import model.ServicePackage;
import model.commandModels.CommandType;

import java.util.List;

public class GetAllServicePackagesResultData extends ClientResultData {

    private List<ServicePackage> packages = null;

    public GetAllServicePackagesResultData(List<ServicePackage> packages) {
        setCommandType(CommandType.GET_PACKAGES);
        this.packages = packages;
    }

    public List<ServicePackage> getPackages() {
        return packages;
    }

    public void setPackages(List<ServicePackage> packages) {
        this.packages = packages;
    }
}
