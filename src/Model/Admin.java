/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.*;

/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class Admin extends Staff implements Serializable{
    private String username;
    private String password;
    
    
    public Admin(String username, String password, String nip, String nama) {
        super(nip, nama);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
      
    
    @Override
    public void lihatProgress(Transaksi T) {
        if(T.getAdmin() == this){
            System.out.println("Progress  : ");
            System.out.println("Tanggal   : " +T.getTglTransaksi());
            System.out.println("Transaksi : " +T.getIdTransaksi());
            System.out.println("Customer  : " +T.getCustomer().getNama() +"(" +T.getCustomer().getIdCustomer() +")");
            System.out.println("Supir     : " +T.getSupir().getNama() +"(" +T.getSupir().getNip() +")");
        }
    }
     
    public void writeObject(){
        File file = new File("database/Admin");
        if (!file.exists()) {
            if (file.mkdirs()) {
                System.out.println("Created!");
            } else {
                System.out.println("Failed to create!");
            }
        }
        try{
            FileOutputStream fos = new FileOutputStream("database/Admin/" +this.getNip() +".dat");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
            fos.close();
            System.out.println("Success!");
        }
        catch (IOException e){System.out.println("Failed. Error: "+e);}
    }
}
