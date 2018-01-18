/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.Supir;
import Model.Admin;
import Model.Mobil;
import Model.Staff;
import Model.Main;
import View.LoginPage;
import View.AdminPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author myusuf, fairuz, diniyal
 */
public class ControllerLoginPage implements ActionListener {
    private List<Mobil> listMobil;
    private List<Supir> listSupir;
    private List<Admin> listAdmin;
    private LoginPage view;
    public static Admin admin = null;

    public ControllerLoginPage() {
        this.view = new LoginPage();
        view.addActionListener(this);
        view.setVisible(true);
        listMobil = Main.listMobil;
        listSupir = Main.listSupir;
        listAdmin = Main.listAdmin;
    }
     
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source.equals(view.getjButtonSignUp())){
            String username = view.getTxSignUpUsername();
            String password = view.getjPasswordFieldSignUp();
            String nip = view.getTxSignUpIdNumber();
            String nama = view.getTxSignUpName();
            if((username != "") && (username != "Username") &&
               (password != "") && (password != "Password") &&
               (nip != "") && (username != "Id Number") &&
               (nama != "") && (nama != "Name")){
               Admin a = new Admin(username, password, nip, nama);
               a.writeObject();
               listAdmin.add(a);
               view.setjLabelSignUp("Signed Up!");
               view.ResetView();      
            }else{
               view.setjLabelSignUp("Failed to Sign Up!");
               view.ResetView();
            }
            
        }
        else if(source.equals(view.getjButtonAdminSignIn())){
            String username = view.getTxAdminSignInUsername();
            String password = view.getjPasswordFieldAdminSignIn();
            boolean check = false;
            for(Admin a : listAdmin){
                if((a.getUsername().equalsIgnoreCase(username)) && 
                    a.getPassword().equalsIgnoreCase(password)){
                    check = true; 
                    admin = a;
                }
            } 
            if(check){
                view.dispose();
                new ControllerAdminPage();
            }
            else{
                view.setjLabelSignIn("Failed to Sign In");
                view.ResetView();
            }
        }
    }
    
}
