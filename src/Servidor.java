import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Servidor {
    public static void main(String[] args) {
        // Usamos el map este nuevo que nos han dicho, por lo visto es de tipo clave:valor
        TreeMap<String, Producto> productos = new TreeMap<>();
        //añadimos como lo dice el ejercicio todos estos valores, he puesto tipo double en lugar de float
        productos.put("PL",new Producto("Peras limoneras", 14, 5));
        productos.put("PC",new Producto("Peras conferencia", 12, 7));
        productos.put("PN",new Producto("Plátano canario", 5, 2.5));
        productos.put("BN",new Producto("Bananas", 7, 1.3));
        productos.put("TP",new Producto("Tomates tipo pera", 8, 1.7));
        productos.put("TR",new Producto("Tomates Raf", 7, 5.3));
        productos.put("UN",new Producto("Uvas negras", 8, 3.2));
        productos.put("UB",new Producto("Uvas blancas", 5, 2.7));
        productos.put("PT",new Producto("Picotas", 8, 4.3));
        productos.put("CR",new Producto("Ciruelas rojas", 10, 2.8));
        productos.put("MR",new Producto("Melocotones rojos", 3, 2.5));
        productos.put("MA",new Producto("Melocotones amarillos", 4, 3.2));

        String opcion = "";
        ServerSocket serverSocket = null;
        try {
            //Iniciamos el Serversocket aceptando la conexion del cliente
            serverSocket = new ServerSocket(1234);
            Socket socket = serverSocket.accept();
            System.out.println("Servidor conectado, esperando entrada del cliente...");
            //Creamos los canales de entrada y salida de datos
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            //creamos un producto vacio ya que vamos a trabajar con esta clase
            Producto producto;
            //while true es infinito, pero tenemos la opcion de romper el bucle si recibimos el String "TRUE" desde el cliente
            while(true){
                opcion = dataInputStream.readUTF();
                if(opcion.equals("FIN")){
                    System.out.println("El cliente ha finalizado la conexion");
                    break;
                }
                //productos.get recorre la lista de MapTree y recobe el Producto que coincida con la clave opcion
                producto = productos.get(opcion);
                //SI no hay coincidencia mandamos este mensaje
                if(producto==null){
                    dataOutputStream.writeUTF("No se encontro el producto");
                    dataOutputStream.flush();
                }else{
                    //Si existe coincidencia mandamos el mensaje producto.toString (es que coincide un producto con una clave)
                    System.out.println("El cliente ha buscado el producto: "+producto.getNombre());
                    dataOutputStream.writeUTF(producto.toString());
                    dataOutputStream.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor");
        }
    }
}
