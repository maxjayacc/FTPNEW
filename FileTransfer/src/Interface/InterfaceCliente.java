/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

/**
 *
 * @author MAX
 */
public class InterfaceCliente {
    String name;
    String file;
    public InterfaceCliente(String name, String file){
    this.name=name;
    this.file=file;
    }
    public void Enviar(String name, String file){
    FTPClient client = new FTPClient();
 
        // Datos para conectar al servidor FTP
        String ftp = "192.99.37.129"; 
        String user = "gps_ftp@manuelfiestasweb.com";
        String password = "Ftp*2017";

        try {
            // Conactando al servidor
            client.connect(ftp);
      
            // Logueado un usuario (true = pudo conectarse, false = no pudo
            // conectarse)
            boolean login = client.login(user, password);
            boolean storeFile = false;
            
            System.out.println("Autentificación: " + login);
            
            client.setFileType(FTP.BINARY_FILE_TYPE, FTP.BINARY_FILE_TYPE);
            client.setFileTransferMode(FTP.BINARY_FILE_TYPE);
          
         
            String filenamer = name;
            
            BufferedInputStream buffIn = null;
            InputStream fis = new FileInputStream(new File(file));
       
            buffIn = new BufferedInputStream(fis);
            
             
            client.enterLocalPassiveMode();
            // Guardando el archivo en el servidor
            storeFile = client.storeFile(filenamer, buffIn);
            System.out.println("Store: " + storeFile );
            
            if(storeFile)
            {
                
                JOptionPane.showMessageDialog(null, "Operación realizada correctamente");
            }
            else
            {
            
                JOptionPane.showMessageDialog(null, "Operación no realizada");
            }
            
            buffIn.close();
          
 
            // Cerrando sesión
            client.logout();
 
            // Desconectandose con el servidor
            client.disconnect();
 
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
            
        }
   }

}