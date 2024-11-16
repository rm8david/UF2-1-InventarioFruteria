import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1",1234);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String opcion= "";
            Scanner sc = new Scanner(System.in);
            do{
                System.out.println("Ingrese el codigo de fruta, escriba FIN para terminar");
                System.out.println("Opciones disponibles: PL, PC, PN, BN, TP, TR, UN, UB, PT, CR, MR, MA");
                opcion = sc.next();
                out.writeUTF(opcion);
                //
                out.flush();
                String producto = in.readUTF();
                System.out.println(producto);
            }while(!opcion.equals("FIN"));

        } catch (IOException | NullPointerException e) {
               System.out.println(e.getMessage());
        }
    }
}
