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
import uaspbo.Model.Games;

public class MenuGameList {

    public MenuGameList(int id) {
        showMenu(id);
    }

    private void showMenu(int id) {
        JFrame f = new JFrame("Game List");
        Controller con = new Controller();

        JButton buttonCari = new JButton("Transactions");
        buttonCari.setBounds(150, 20, 100, 30);
        buttonCari.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new MenuTransactions(id);
                f.dispose();
            }
        });

        ArrayList<Games> listGames = con.getAllGames();

        DefaultTableModel tableModel = new DefaultTableModel();

        JTable table = new JTable(tableModel);
        tableModel.addColumn("Name");
        tableModel.addColumn("Genre");
        tableModel.addColumn("Price");

        for (int i = 0; i < listGames.size(); i++) {
            String name = listGames.get(i).getName();
            String genre = listGames.get(i).getGenre();
            String price = listGames.get(i).getPrice();

            Object[] data = {name, genre, price};

            tableModel.insertRow(i, data);

        }
        table.setBounds(50, 50, 400, 200);
        table.setRowHeight(100);

        JLabel labelNIK = new JLabel("Game Name: ");
        JTextField textNIK = new JTextField();
        labelNIK.setBounds(10, 300, 200, 20);
        textNIK.setBounds(200, 300, 200, 20);

        JLabel labelNama = new JLabel("Game Genre: ");
        JTextField textNama = new JTextField();
        labelNama.setBounds(10, 330, 200, 20);
        textNama.setBounds(200, 330, 200, 20);

        JLabel labelTempatLahir = new JLabel("Game Price: ");
        JTextField textTempatLahir = new JTextField();
        labelTempatLahir.setBounds(10, 360, 200, 20);
        textTempatLahir.setBounds(200, 360, 200, 20);

        JLabel labelFoto = new JLabel("Foto: ");
        JButton fotoChooser = new JButton("Choose File:");
        JLabel pathFoto = new JLabel();
        labelFoto.setBounds(10, 390, 200, 20);
        fotoChooser.setBounds(200, 390, 200, 20);
        pathFoto.setBounds(200, 410, 300, 20);
        fotoChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jf = new JFileChooser();
                int returnValue = jf.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = jf.getSelectedFile();
                    String pathFile = selectedFile.getAbsolutePath();
                    pathFoto.setText(pathFile);
                }
            }
        });

        JButton insertData = new JButton("Buy Game");
        insertData.setBounds(200, 540, 200, 30);
        insertData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textNIK.getText();
                String genre = textNama.getText();
                String price = textTempatLahir.getText();

                if (name.isEmpty() || genre.isEmpty() || price.isEmpty() || pathFoto.getText().equals("No file selected")) {
                    JOptionPane.showMessageDialog(f, "Masih Kosong!", "Empty FIeld", JOptionPane.WARNING_MESSAGE);
                } else {
                    boolean gameFound = con.getGame(name, genre, price);
                    if (gameFound) {
                        int idGame = con.getIDGames(name);
                        boolean transFound = con.findTR(id, idGame);
                        if (!transFound) {
                            boolean succeed = con.inputDataToDB(id, idGame);
                            f.dispose();
                            if (succeed) {
                                JOptionPane.showMessageDialog(f, "Data berhasil disimpan");
                                new MenuTransactions(id);
                            }
                        } else {
                            JOptionPane.showMessageDialog(f, "Transactions Found", "Exist", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(f, "No Game Found", "Not Found", JOptionPane.WARNING_MESSAGE);
                    }
                }

            }

        });
        JButton backButton = new JButton("Back to Main Menu");
        backButton.setBounds(200, 590, 150, 30);
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                f.dispose();
                new MainMenu();
            }
        });
        
        f.add(backButton);
        f.add(buttonCari);
        f.add(table);
        f.add(labelNIK);
        f.add(textNIK);
        f.add(labelNama);
        f.add(textNama);
        f.add(labelTempatLahir);
        f.add(textTempatLahir);
        f.add(labelFoto);
        f.add(fotoChooser);
        f.add(pathFoto);
        f.add(insertData);
        
        f.setLayout(null);
        f.setSize(600, 800);
        f.setVisible(true);
    }
}
