/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.*;
import View.ViewTransactionssPage;
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
public class ControllerViewTransactionsPage  extends MouseAdapter implements ActionListener {
    static List<Mobil> listMobil = new ArrayList();
    static List<Supir> listSupir = new ArrayList();
    static List<Admin> listAdmin = new ArrayList();
    static List<Customer> listCustomer = new ArrayList();
    static List<Transaksi> listTransaksi = new ArrayList();
    
    String[] listDaftarTransaksi;
    int idMobil;
    String noPol;
    String kapasitas;
    String merk;
    
    private ViewTransactionssPage view;
    private Admin admin;

    public ControllerViewTransactionsPage() {
        this.view = new ViewTransactionssPage();
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
        
        String[] listDaftarTransaksi = new String[listTransaksi.size()];
        for(int i = 0; i < listDaftarTransaksi.length; i++){
            listDaftarTransaksi[i] = String.valueOf(listTransaksi.get(i).getIdTransaksi());
        }
        view.setjListTransactions(listDaftarTransaksi);
    }
    private String[] getDaftarTransaksi(){
        String[] listDaftarTransaksi = new String[listTransaksi.size()];
        for(int i = 0; i < listDaftarTransaksi.length; i++){
            listDaftarTransaksi[i] = String.valueOf(listTransaksi.get(i).getIdTransaksi());
        }
        return listDaftarTransaksi;
    }
    
    public void mousePressed(MouseEvent me){
        Object source = me.getSource();
        if(source.equals(view.getjListTransactions())){
            int i = view.getSelectedtTransaction();
            Transaksi t = listTransaksi.get(i);
            view.setjTextAreaDetails(t.toString());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonDelete())){
            int i = view.getSelectedtTransaction();
            try{
            //Specify the file name and path
            File file = new File("database/Transaksi/" +listTransaksi.get(i).getIdTransaksi() +".dat");
                
                if(file.delete()){System.out.println(file.getName() + " is deleted!");}
                else{System.out.println("Delete failed: " +file.getName() +" didn't delete");}
            }
            catch(Exception ex){
                System.out.println("Exception occurred");
                ex.printStackTrace();
            }
            listTransaksi.remove(i);
            view.ResetView();
            view.setjListTransactions(getDaftarTransaksi());
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
