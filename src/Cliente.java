import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        try {
            //conectamos al servidor con al ip, en este caso la ip local y el puerto que hemos puesto en el servidor
            Socket socket = new Socket("127.0.0.1",1234);
            //creamos los canales de entrada y salida de datos, al igual que en el servidor
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String opcion= "";
            Scanner sc = new Scanner(System.in);
            do{
                System.out.println("Ingrese el codigo de fruta, escriba FIN para terminar");
                System.out.println("Opciones disponibles: PL, PC, PN, BN, TP, TR, UN, UB, PT, CR, MR, MA");
                //pedimos una clave de dos caracteres por consola y se la enviamos al servidor
                opcion = sc.next();
                out.writeUTF(opcion);
                out.flush();
                //recobimos por el in ( DataInputStream ) lo que nos responde el servidor
                String producto = in.readUTF();
                System.out.println(producto);
                //SI la opcion escogida es FIN, salimos y desconectamos
            }while(!opcion.equals("FIN"));
            socket.close();
        } catch (IOException | NullPointerException e) {
               System.out.println(e.getMessage());
        }
    }
}
