package model.commandModels;

import command.commandData.ServerCommandData;

import java.util.ArrayList;
import java.util.List;

public class CommandDataList {

    private List<ServerCommandData> commandDataList;

    public CommandDataList() {
        this.commandDataList = new ArrayList<>();
    }

    public List<ServerCommandData> getCommandDataList() {
        return commandDataList;
    }

    public void setCommandDataList(List<ServerCommandData> commandDataList) {
        this.commandDataList = commandDataList;
    }

    public void add( ServerCommandData CommandData ) {
        this.commandDataList.add( CommandData );
    }

    /**
     * Append current commandDataList with command.commandData objects of another commandDataList.
     * @param in_commandDataList commandDataList to be used to append
     */
    public void addList( CommandDataList in_commandDataList ) {
        this.commandDataList.addAll(in_commandDataList.getCommandDataList());

    }

    public int size() {
        return this.commandDataList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.commandDataList.size()==0) {
            sb.append("\t\t*** EMPTY ***\n");
        } else {
            for ( ServerCommandData c : this.commandDataList ) {
                sb.append("\t - ");
                sb.append(c.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public ServerCommandData getAt(int index){return this.commandDataList.get(index);}

}

