/* CLIENT */
import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Socket client = null;

        //Default port number we are going tio use
        int portnumber = 53000;
        if (args.length >= 1){
            portnumber = Integer.parseInt(args[0]);
        }
        for (int i=0; i <10; i++){
            try {
                String msg = "";

                // Create a client socket
                client = new Socket(InetAddress.getLocalHost(), portnumber);
                System.out.println("Client socket is created" + client);

                // Create an output stream of the client socketk
                OutputStream clientOut = client.getOutputStream();
                PrintWriter pw = new PrintWriter(clientOut, true);

                // Create input stream of the client socket
                InputStream clientIn = client.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter your name. Type bye to exit.");

                //Read data from standard input device and write it
                // to the output stream of the client socket.
                msg = stdIn.readLine().trim();
                pw.println(msg);

                //Read data from standard input stream of the client socket.
                System.out.println("Message returned from the server = " + br.readLine());
                pw.close();
                br.close();
                client.close();

                //Stop the operation
                if (msg.equalsIgnoreCase("Bye")) {
                    break;
                }
            }catch(IOException e){
                System.out.println("I/O error" + e);

            }
        }

    }
}