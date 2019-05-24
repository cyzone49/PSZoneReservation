package server;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class StreamOut {
    protected static void streamToClient(HttpExchange httpExchange, String response) throws IOException
    {
        BufferedOutputStream out = new BufferedOutputStream(httpExchange.getResponseBody());
        ByteArrayInputStream bis = new ByteArrayInputStream(response.getBytes());

        byte[] buffer = new byte[4096];
        int count;
        while ((count = bis.read(buffer)) != -1) {
            out.write(buffer, 0, count);
        }
        out.flush();
        out.close();
    }

}
