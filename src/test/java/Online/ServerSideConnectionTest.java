package Online;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class ServerSideConnectionTest extends TestCase {

    ServerSideConnectionTest() throws IOException {
    }

    @Test
    public void testCons() throws IOException {
        Socket sock = new Socket("local host",3000);
        ServerSideConnection s = new ServerSideConnection(sock,1);
        s.sendBoolean(true);

    }
}