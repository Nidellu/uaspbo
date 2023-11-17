package uaspbo.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import uaspbo.Contoller.Controller;

public class LogIn {

    public LogIn() {
        showPencarianScreen();
    }

    private void showPencarianScreen() {
        JFrame frameCari = new JFrame("LOGIN");

        Controller con = new Controller();
        
        JLabel email = new JLabel("Email: ");
        email.setBounds(10, 10, 100, 30);
        JTextField inputEmail = new JTextField();
        inputEmail.setBounds(120, 10, 200, 30);
        
        JLabel password = new JLabel("Pasword: ");
        password.setBounds(10, 40, 100, 30);
        JPasswordField inputPassword = new JPasswordField();
        inputPassword.setBounds(120, 40, 200, 30);
        
        
        JButton buttonCari = new JButton("LOGIN");
        buttonCari.setBounds(150, 100, 100, 30);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = String.valueOf(inputPassword.getPassword());
                boolean found = con.logIn(inputEmail.getText(), password);
                
                if (found) {
                    frameCari.dispose();
                    int id = con.getID(inputEmail.getText());
                    new MenuGameList(id);
                } else {
                    JOptionPane.showMessageDialog(frameCari, "Email atau password salah!", "Gagal Login", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(150, 130, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frameCari.dispose();
                new MainMenu();
            }
        });

        JPanel panel = new JPanel(null);
        panel.add(email);
        panel.add(inputEmail);
        panel.add(password);
        panel.add(inputPassword);
        panel.add(backButton);
        panel.add(buttonCari);

        frameCari.setSize(400, 200);
        frameCari.add(panel);
        frameCari.setVisible(true);
    }
}
