package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;

public class Settings extends JDialog {
    private TabulatedFunctionFactory factory = new ArrayTabulatedFunctionFactory();
    private final JRadioButton massif;
    private final JRadioButton list;

    public Settings() {
        super();
        setModal(true);
        setLocationRelativeTo(null);
        setSize(new Dimension(200, 100));
        JLabel label = new JLabel("Выберите фабрику");
        massif = new JRadioButton("Массивы");
        list = new JRadioButton("Список");
        ButtonGroup group = new ButtonGroup();
        group.add(massif);
        group.add(list);
        massif.addActionListener(e -> {
            if (e.getSource() == massif) {
                factory = new ArrayTabulatedFunctionFactory();
            }
        });
        list.addActionListener(e -> {
            if (e.getSource() == list) {
                factory = new LinkedListTabulatedFunctionFactory();
            }
        });
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addGroup(layout.createSequentialGroup().addComponent(label))
                .addGroup(layout.createSequentialGroup().addComponent(massif).addComponent(list)));
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(label))
                .addGroup(layout.createParallelGroup().addComponent(massif).addComponent(list)));
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }
}