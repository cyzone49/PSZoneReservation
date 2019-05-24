package command.commandData;

import static model.commandModels.CommandType.GET_PACKAGES;

public class GetAllServicePackagesCommandData extends ServerCommandData {
    public GetAllServicePackagesCommandData() {
        setCommandType(GET_PACKAGES);
    }
}
