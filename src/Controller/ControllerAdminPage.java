/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.AdminPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import jdk.nashorn.internal.runtime.JSType;


/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class ControllerAdminPage implements ActionListener {
    static List<Mobil> listMobil = new ArrayList();
    static List<Supir> listSupir = new ArrayList();
    static List<Admin> listAdmin = new ArrayList();
    static List<Customer> listCustomer = new ArrayList();
    static List<Transaksi> listTransaksi = new ArrayList();
    
    List<String> listDaftarMobil = new ArrayList();
    List<String> listDaftarSupir = new ArrayList();
    
    private AdminPage view;
    private Admin admin;
    
    static int idMobil = 13000;
    static int idCustomer = 95000;
    static int idTransaksi =39000;
    
    public ControllerAdminPage() {
        this.view = new AdminPage();
        view.addActionListener(this);
        view.setVisible(true);
        if(ControllerLoginPage.admin != null){
            admin = ControllerLoginPage.admin;
            view.setjLabelAdminName(admin.getNama());
        }
        
        listMobil = Main.listMobil;
        listSupir = Main.listSupir;
        listAdmin = Main.listAdmin;
        listCustomer = Main.listCustomer;
        listTransaksi = Main.listTransaksi;
        
        for(Mobil o : listMobil){
            listDaftarMobil.add(o.getNoPolisi());
        }
        view.setjComboBoxCar(listDaftarMobil);
        
        List<String> listDaftarSupir = new ArrayList();
        for(Supir o : listSupir){
            listDaftarSupir.add(o.getNip());
        }
        view.setjComboBoxDriver(listDaftarSupir);
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonAddCar())){
            String policeNum = view.getTxPoliceNum();
            String capacity = view.getTxCapacity();
            String brand = view.getTxBrand();
            if(listMobil.size() > 0){
                idMobil = listMobil.get(listMobil.size()-1).getIdMobil();
            }
            
            Mobil m = new Mobil(idMobil+1, policeNum,capacity, brand);
            m.writeObject();
            listMobil.add(m);
            view.setjLabelAddCar("Car Added");
            listDaftarMobil.clear();
            for(Mobil o : listMobil){
                listDaftarMobil.add(o.getNoPolisi());
            }
            view.setjComboBoxCar(listDaftarMobil);
            view.ResetView();   
        }
        else if(source.equals(view.getjButtonAddDriver())){
            String driverName = view.getTxDriverName();
            String idNumber = view.getTxIdNumber();
            String noPol = view.getjComboBoxCar();
            Mobil mu = null;
            for(Mobil o : listMobil){
                if(o.getNoPolisi() == noPol){mu = o;}
            }
            
            if(mu != null){
                Supir s = new Supir(driverName, idNumber, mu);
                s.writeObject();
                listSupir.add(s);
                view.setjLabelAddDriver("Driver Added");
                listDaftarSupir.clear();
                for(Supir o : listSupir){
                    listDaftarSupir.add(o.getNip());
                }
                view.setjComboBoxDriver(listDaftarSupir);
                view.ResetView();
            }
            else{view.setjLabelAddDriver("Failed to Add Driver");}
            view.ResetView();   
        }
        else if(source.equals(view.getjButtonAddTransaction())){
            String name = view.getTxCustomerName();
            String destination = view.getTxDestination();
            String date = view.getTxDate();
            String price = view.getTxPrice();
            String nipSupir = view.getjComboBoxDriver();
            Supir su = null;
            for(Supir o : listSupir){
                if(o.getNip() == nipSupir){su = o;}
            }

            if(listCustomer.size() > 0){
                idCustomer = JSType.toInteger(listCustomer.get(listCustomer.size()-1).getIdCustomer());
            }
            
            Customer cus = new Customer(String.valueOf(idCustomer + 1), destination, name);
            cus.writeObject();
            listCustomer.add(cus);
            if(listTransaksi.size() > 0){
                idTransaksi = listTransaksi.get(listTransaksi.size()-1).getIdTransaksi();
            }
            
            if((su != null) && (cus != null) && (admin != null)){
                Transaksi t = new Transaksi(idTransaksi+1, admin, su, cus, date, price);
                t.writeObject();
                listTransaksi.add(t);
                
                view.setjLabelAddCar("Transaction Added");
                view.setjLabelAddDriver("Transaction Added");
            }
            else{
                view.setjLabelAddCar("Failed to Add Transaction");
                view.setjLabelAddDriver("Failed to Add Transaction");
            }
            view.ResetView(); 
        }
        else if(source.equals(view.getjButtonShowCars())){
            view.dispose();
            new ControllerViewCarsPage();
        }
        else if(source.equals(view.getjButtonShowDrivers())){
            view.dispose();
            new ControllerViewDriversPage();
        }
        else if(source.equals(view.getjButtonShowCustomers())){
            view.dispose();
            new ControllerViewCustomersPage();
        }
        else if(source.equals(view.getjButtonShowTransactions())){
            view.dispose();
            new ControllerViewTransactionsPage();
        }
        else if(source.equals(view.getjButtonSignOut())){
            view.dispose();
            new ControllerLoginPage();
            ControllerLoginPage.admin = null;
        }
    }
}
