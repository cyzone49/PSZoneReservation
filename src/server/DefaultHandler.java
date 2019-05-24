package server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class DefaultHandler implements HttpHandler{

    /** Displays homepage to browser
     *
     * @param httpExchange
     * @throws IOException
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {

        String query = httpExchange.getRequestURI().getPath();
        Gson gson = new Gson();

        System.out.println(query);

        File file = null;
        if (query.equals("/")) {
            file = new File("src/web/index.html");
        }
        else if (query.equals("src/web/css/main.css")) {
            file = new File("web/css/main.css");
        }
        else if (query.equals("src/web/js/scripts.js")) {
            file = new File("web/js/scripts.js");
            if(!file.exists()) {
                System.out.println("whatt+");
            }
        }
        else {
            String response = gson.toJson("invalid API. You suck!");
            httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_NOT_FOUND, response.length());
            StreamOut.streamToClient(httpExchange, response);
            return;
        }

        httpExchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
        OutputStream output = httpExchange.getResponseBody();
        FileInputStream fs = new FileInputStream(file);
        final byte[] buffer = new byte[0x10000];
        int count = 0;
        while ((count = fs.read(buffer)) >= 0) {
            output.write(buffer, 0, count);
        }
        output.flush();
        output.close();
        fs.close();
    }
}

