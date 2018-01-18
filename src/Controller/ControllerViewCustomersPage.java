/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.ViewCustomersPage;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import jdk.nashorn.internal.runtime.JSType;


/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class ControllerViewCustomersPage  extends MouseAdapter implements ActionListener {
    static List<Mobil> listMobil = new ArrayList();
    static List<Supir> listSupir = new ArrayList();
    static List<Admin> listAdmin = new ArrayList();
    static List<Customer> listCustomer = new ArrayList();
    static List<Transaksi> listTransaksi = new ArrayList();
    
    String[] listDaftarCustomer;
    int idMobil;
    String noPol;
    String kapasitas;
    String merk;
    
    private ViewCustomersPage view;
    private Admin admin;

    public ControllerViewCustomersPage() {
        this.view = new ViewCustomersPage();
        view.addActionListener(this);
        view.addMouseAdapter(this);
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
        
        String[] listDaftarCustomer = new String[listCustomer.size()];
        for(int i = 0; i < listDaftarCustomer.length; i++){
            listDaftarCustomer[i] = listCustomer.get(i).getIdCustomer();
        }
        view.setjListCustomer(listDaftarCustomer);
    }
    private String[] getDaftarCustomer(){
        String[] listDaftarCustomer = new String[listCustomer.size()];
        for(int i = 0; i < listDaftarCustomer.length; i++){
            listDaftarCustomer[i] = listCustomer.get(i).getIdCustomer();
        }
        return listDaftarCustomer;
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(view.getjListCustomers())){
            int i = view.getSelectedtCustomer();
            Customer c = listCustomer.get(i);
            view.setjTextAreaDetails(c.tampilBiodata());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonEdit())){
            int i = view.getSelectedtCustomer();
            
            kapasitas = JOptionPane.showInputDialog("Name", listCustomer.get(i).getNama());
            if(kapasitas == null){
                kapasitas = listCustomer.get(i).getNama();
            }
            
            merk = JOptionPane.showInputDialog("Destination", listCustomer.get(i).getTujuan());
            if(merk == null){
                merk = listCustomer.get(i).getTujuan();
            }
            
            Customer c = new Customer(listCustomer.get(i).getIdCustomer(), kapasitas, merk);
            c.writeObject();
            listCustomer.set(i, c);
            view.ResetView();
            view.setjListCustomer(getDaftarCustomer());
            view.setjLabelStatus("Edited");
        }
        else if(source.equals(view.getjButtonDelete())){
            int i = view.getSelectedtCustomer();
            try{
            //Specify the file name and path
            File file = new File("database/Customer/" +listCustomer.get(i).getIdCustomer()+".dat");
                
                if(file.delete()){System.out.println(file.getName() + " is deleted!");}
                else{System.out.println("Delete failed: " +file.getName() +" didn't delete");}
            }
            catch(Exception ex){
                System.out.println("Exception occurred");
                ex.printStackTrace();
            }
            listCustomer.remove(i);
            view.ResetView();
            view.setjListCustomer(getDaftarCustomer());
            view.setjLabelStatus("Deleted");
        }
        
        else if(source.equals(view.getjButtonHome())){
            view.dispose();
            new ControllerAdminPage();
        }
        
        else if(source.equals(view.getjButtonSignOut())){
            view.dispose();
            new ControllerLoginPage();
            ControllerLoginPage.admin = null;
        }
    }
}
