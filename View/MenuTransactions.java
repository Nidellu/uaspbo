/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package uaspbo.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import uaspbo.Contoller.Controller;
import uaspbo.Model.Transactions;

/**
 *
 * @author brian
 */
public class MenuTransactions {

    public MenuTransactions(int id) {
        showTransaction(id);
    }

    private void showTransaction(int id) {
        JFrame f = new JFrame("Transactions");
        Controller con = new Controller();
        
        ArrayList<Transactions> listTransactions = con.getTransactions(id);

        DefaultTableModel tableModel = new DefaultTableModel();

        JTable table = new JTable(tableModel);
        tableModel.addColumn("Id");
        tableModel.addColumn("UserID");
        tableModel.addColumn("Username");
        tableModel.addColumn("GameID");
        tableModel.addColumn("Game Name");
        tableModel.addColumn("Price");
        

        for (int i = 0; i < listTransactions.size(); i++) {
            int idTr = listTransactions.get(i).getId();
            int userID = listTransactions.get(i).getUser_id();
            String name = listTransactions.get(i).getUsername();
            int gameID = listTransactions.get(i).getGame_id();
            String gameName = listTransactions.get(i).getGameName();
            String price = listTransactions.get(i).getPrice();

            Object[] data = {idTr, userID, name, gameID, gameName, price};

            tableModel.insertRow(i, data);

        }

        JButton backButton = new JButton("Back to Game List");
        backButton.setBounds(170, 350, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
                new MenuGameList(id);
            }
        });

        table.setBounds(50, 100, 700, 200);
        table.setRowHeight(100);

        f.setSize(800, 500);
        f.add(backButton);
        f.add(new JScrollPane(table));
        f.setVisible(true);

    }

}
