package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import javax.swing.*;
public class WindowSize extends JDialog {
    private int size;

    public WindowSize() {
        JDialog dialog = new JDialog();
        this.setBounds(200, 200, 400, 100);
        setLocationRelativeTo(null);
        setModal(true);
        JLabel label = new JLabel("Число точек:");
        JTextField input = new JTextField();
        JButton button = new JButton("enter");
        button.addActionListener(e -> {
            try {
                if (input.getText().equals("") || Integer.parseInt(input.getText()) < 2) {
                    JOptionPane.showMessageDialog(dialog, "Введите корректно число точек");
                } else {
                    size = Integer.parseInt(input.getText());
                    dispose();
                }
            } catch (NumberFormatException exception){
                JOptionPane.showMessageDialog(dialog, "Введите корректно число точек");
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(layout.createSequentialGroup().addComponent(label).addComponent(input)).addComponent(button));

        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label).addComponent(input)).addComponent(button));

    }

    public int getSizeOf() {
        return size;
    }
}