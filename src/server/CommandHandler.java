package server;

import command.commandData.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

import com.google.gson.*;
import command.reservation.CancelReservationCommand;
import command.reservation.GetReservationByIDCommand;
import command.reservation.GetReservationByUserCommand;
import model.commandModels.CommandResult;
import model.commandModels.CommandType;
import command.location.AddLocationCommand;
import command.location.GetAllLocationsCommand;
import command.reservation.MakeReservationCommand;
import command.servicePackage.AddServicePackageCommand;
import command.slot.AddSlotCommand;
import command.slot.GetAvailableSlotsCommand;
import command.user.LoginCommand;
import command.user.RegisterCommand;

public class CommandHandler implements HttpHandler
{
    @Override
    public void handle(HttpExchange exchange) throws IOException
    {
        boolean success = false;
        System.out.println("\n\n\n#################################\n");
        System.out.println("server.CommandHandler running ... ");

        String query = exchange.getRequestURI().getPath();
        System.out.println("\nrequest uri = " + query);

        File file = null;
        if(query.equals("/")) {
            file = new File("web/index.html");
        }

        try
        {



            if (exchange.getRequestMethod().toLowerCase().equals("post"))
            {
                String reqData = null;
                String username = null;
                try {
                    InputStream reqBody = exchange.getRequestBody();
                    reqData = readString(reqBody);

                    JsonParser parser = new JsonParser();
                    JsonObject json = (JsonObject) parser.parse(reqData);

                    JsonElement usernameObj = json.get("username");
                    if(usernameObj != null) {
                        username = json.get("username").getAsString();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                Gson gson = new Gson();
                ServerCommandData scd = null;
                try
                {
                    scd = gson.fromJson(reqData, ServerCommandData.class);
                }
                catch (Exception e)
                {
                    System.out.println("Failed to deserialize from json");
                }

                CommandType myCommandType = scd.getCommandType();
                System.out.println("\nReceived a " + myCommandType.toString() + " command!");



                // USER commands

                if(myCommandType == CommandType.LOGIN)
                {
                    LoginCommandData commandData = null;
                    commandData = gson.fromJson(reqData, LoginCommandData.class);
                    if(commandData == null)
                    {
                        System.out.println("Invalid server.Server Login Command Data");
                    }
                    else
                    {
                        LoginCommand lsc = new LoginCommand(commandData );
                        CommandResult loginCommandResult = lsc.execute();
                        success = sendResult(loginCommandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.REGISTER)
                {
                    RegisterCommandData commandData = null;
                    commandData = gson.fromJson(reqData, RegisterCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid server.Server Register Command Data");
                    }
                    else
                    {
                        RegisterCommand command = new RegisterCommand(commandData);

                        CommandResult registerCommandResult = command.execute();
                        success = sendResult(registerCommandResult, exchange, username);
                    }
                }


                // LOCATION commands

                else if(myCommandType == CommandType.ADD_LOCATION)
                {
                    AddLocationCommandData commandData = null;
                    commandData = gson.fromJson(reqData, AddLocationCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid server.Server Add Location Command Data");
                    }
                    else
                    {
                        AddLocationCommand command = new AddLocationCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.GET_LOCATIONS)
                {
                    GetAllLocationsCommandData commandData = null;
                    commandData = gson.fromJson(reqData, GetAllLocationsCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid server.Server Get All Locations Command Data");
                    }
                    else
                    {
                        GetAllLocationsCommand command = new GetAllLocationsCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }

                // SERVICE PACKAGE Commands

                else if(myCommandType == CommandType.ADD_PACKAGE)
                {
                    AddServicePackageCommandData commandData = null;
                    commandData = gson.fromJson(reqData, AddServicePackageCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid server.Server add Package Command Data");
                    }
                    else
                    {
                        AddServicePackageCommand command = new AddServicePackageCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }


                // SLOT Commands

                else if(myCommandType == CommandType.ADD_SLOT)
                {
                    AddSlotCommandData commandData = null;
                    commandData = gson.fromJson(reqData, AddSlotCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid server.Server add Slot Command Data");
                    }
                    else
                    {
                        AddSlotCommand command = new AddSlotCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.GET_AVAILABLE_SLOTS)
                {
                    GetAvailableSlotsCommandData commandData = null;
                    commandData = gson.fromJson(reqData, GetAvailableSlotsCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid GET_AVAILABLE_SLOTS Command Data");
                    }
                    else
                    {
                        GetAvailableSlotsCommand command = new GetAvailableSlotsCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }

                // RESERVATION commands

                else if(myCommandType == CommandType.MAKE_RESERVATION)
                {
                    MakeReservationCommandData commandData = null;
                    commandData = gson.fromJson(reqData, MakeReservationCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid MAKE_RESERVATION Command Data");
                    }
                    else
                    {
                        MakeReservationCommand command = new MakeReservationCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.GET_RESERVATION_BY_USER)
                {
                    GetReservationByUserCommandData commandData = null;
                    commandData = gson.fromJson(reqData, GetReservationByUserCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid GET_RESERVATION_BY_USER Command Data");
                    }
                    else
                    {
                        GetReservationByUserCommand command = new GetReservationByUserCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.CANCEL_RESERVATION)
                {
                    CancelReservationCommandData commandData = null;
                    commandData = gson.fromJson(reqData, CancelReservationCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid CANCEL_RESERVATION Command Data");
                    }
                    else
                    {
                        CancelReservationCommand command = new CancelReservationCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }
                else if(myCommandType == CommandType.GET_RESERVATION_BY_ID)
                {
                    GetReservationByIDCommandData commandData = null;
                    commandData = gson.fromJson(reqData, GetReservationByIDCommandData.class);
                    if(commandData == null)
                    {
                        System.out.print("Invalid GET_RESERVATION_BY_USER Command Data");
                    }
                    else
                    {
                        GetReservationByIDCommand command = new GetReservationByIDCommand(commandData);

                        CommandResult commandResult = command.execute();
                        success = sendResult(commandResult, exchange, username);
                    }
                }



                else
                {
                    System.out.print("Received Unknown Command Type!");
                }
            }
            if (!success)
            {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                Gson myGson = new Gson();
                String errorJson = myGson.toJson("Command Failed, Bad Request");

                OutputStream respBody = exchange.getResponseBody();
                writeString(errorJson, respBody);
                respBody.close();
            }
        }
        catch (IOException e)
        {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

            Gson myGson = new Gson();
            String errorJson = myGson.toJson("Command Failed, HTTP SERVER ERROR");

            OutputStream respBody = exchange.getResponseBody();
            writeString(errorJson, respBody);
            respBody.close();
            e.printStackTrace();
        }
    }


    private boolean sendResult(CommandResult commandResult, HttpExchange exchange, String username) throws IOException
    {
        boolean success = true;
        try
        {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
            Gson myGson = new Gson();

            String json = myGson.toJson(commandResult);

            OutputStream respBody = exchange.getResponseBody();
            writeString(json, respBody);
            respBody.close();
        }
        catch (IOException e)
        {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);

            Gson myGson = new Gson();
            String errorJson = myGson.toJson("Command Failed, HTTP SERVER ERROR");

            OutputStream respBody = exchange.getResponseBody();
            writeString(errorJson, respBody);
            respBody.close();
            e.printStackTrace();
            success = false;
        }

        return success;
    }
    private void writeString(String str, OutputStream os) throws IOException
    {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
    private String readString(InputStream is) throws IOException
    {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0)
        {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }
}
