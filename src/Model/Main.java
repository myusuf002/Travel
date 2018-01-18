/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Controller.ControllerLoginPage;

/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class Main {    /**
     * @param args the command line arguments
     */
    public static List<Mobil> listMobil = new ArrayList();
    public static List<Supir> listSupir = new ArrayList();
    public static List<Admin> listAdmin = new ArrayList();
    public static List<Customer> listCustomer = new ArrayList();
    public static List<Transaksi> listTransaksi = new ArrayList();
    
    public static void main(String[] args){
        listMobil = new ArrayList();
        File fileMobil = new File("database/Mobil");
        if(fileMobil.listFiles() != null){
            for(File f : fileMobil.listFiles()){     
                try {
                    FileInputStream fis = new FileInputStream("database/Mobil/" +f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Mobil m;
                    m= (Mobil) ois.readObject();
                    listMobil.add(new Mobil(m.getIdMobil(), m.getNoPolisi(), m.getKapasitas(), m.getMerkMobil()));
                    ois.close();
                    fis.close();
                } 
                catch (FileNotFoundException e) {System.out.println("File not found");} 
                catch (IOException e) {System.out.println("Error initializing stream : "+e.getMessage());} 
                catch (ClassNotFoundException e) {e.printStackTrace();}  
            }
        }
        
        File fileSupir = new File("database/Supir");
        if(fileSupir.listFiles() != null){
            for(File f : fileSupir.listFiles()){
                try {
                    FileInputStream fis = new FileInputStream("database/Supir/" +f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Supir s;
                    s = (Supir) ois.readObject();
                    Mobil mo = null;
                    for(Mobil o : listMobil){
                        if(o.getIdMobil() == s.getMobil().getIdMobil()){
                            mo = o;
                        }
                    }
                    
                    if(mo != null){listSupir.add(new Supir(s.getNama(), s.getNip(), mo));}
                    
                    ois.close();
                    fis.close();
                } 
                catch (FileNotFoundException e) {System.out.println("File not found");} 
                catch (IOException e) {System.out.println("Error initializing stream : "+e.getMessage());} 
                catch (ClassNotFoundException e) {e.printStackTrace();}  
            }
        }
        
        listAdmin = new ArrayList();
        File fileAdmin = new File("database/Admin");
        if(fileAdmin.listFiles() != null){
            for(File f : fileAdmin.listFiles()){     
                try {
                    FileInputStream fis = new FileInputStream("database/Admin/" +f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Admin a;
                    a = (Admin) ois.readObject();
                    listAdmin.add(new Admin(a.getUsername(), a.getPassword(), a.getNip(), a.getNama()));
                    ois.close();
                    fis.close();
                } 
                catch (FileNotFoundException e) {System.out.println("File not found");} 
                catch (IOException e) {System.out.println("Error initializing stream : "+e.getMessage());} 
                catch (ClassNotFoundException e) {e.printStackTrace();}  
            }
        }

        File fileCustomer = new File("database/Customer");
        if(fileCustomer.listFiles() != null){
            for(File f : fileCustomer.listFiles()){
                try {
                    FileInputStream fis = new FileInputStream("database/Customer/" +f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Customer c;
                    c = (Customer) ois.readObject();
                    listCustomer.add(new Customer(c.getIdCustomer(), c.getTujuan(), c.getNama()));
                    ois.close();
                    fis.close();
                } 
                catch (FileNotFoundException e) {System.out.println("File not found");} 
                catch (IOException e) {System.out.println("Error initializing stream : "+e.getMessage());} 
                catch (ClassNotFoundException e) {e.printStackTrace();}  
            }
        }
        

        File fileTransaksi = new File("database/Transaksi");
        if(fileTransaksi.listFiles() != null){
            for(File f : fileTransaksi.listFiles()){
                try {
                    FileInputStream fis = new FileInputStream("database/Transaksi/" +f.getName());
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    Transaksi t;
                    t = (Transaksi) ois.readObject();
                    
                    Supir su = null;
                    for(Supir o : listSupir){
                        if(o.getNip() == t.getSupir().getNip()){
                            su = o;
                        }
                    }
                    
                    Admin ad = null;
                    for(Admin o : listAdmin){
                        if(o.getNip() == t.getAdmin().getNip()){
                            ad = o;
                        }
                    }
                    
                    Customer cus = null;
                    for(Customer o : listCustomer){
                        if(o.getIdCustomer()== t.getCustomer().getIdCustomer()){
                            cus = o;
                        }
                    }
                    listTransaksi.add(new Transaksi(t.getIdTransaksi(), ad, su, cus, t.getTglTransaksi(), t.getHarga()));
                    ois.close();
                    fis.close();
                } 
                catch (FileNotFoundException e) {System.out.println("File not found");} 
                catch (IOException e) {System.out.println("Error initializing stream : "+e.getMessage());} 
                catch (ClassNotFoundException e) {e.printStackTrace();}  
            }
            
        }

       new ControllerLoginPage();
    }    
}
