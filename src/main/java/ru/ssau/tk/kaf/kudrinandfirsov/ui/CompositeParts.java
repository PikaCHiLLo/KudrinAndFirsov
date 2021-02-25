package ru.ssau.tk.kaf.kudrinandfirsov.ui;

import ru.ssau.tk.kaf.kudrinandfirsov.functions.*;
import ru.ssau.tk.kaf.kudrinandfirsov.functions.factory.TabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;

public class CompositeParts extends JDialog {
    private String functionStr1;
    private String functionStr2;
    private final Map<String, MathFunction> map = new HashMap<>();
    private double constant;
    private MathFunction part1;
    private MathFunction part2;

    public CompositeParts(TabulatedFunctionFactory factory) {
        JDialog dialog = new JDialog();
        setModal(true);
        setSize(new Dimension(400, 200));
        setLocationRelativeTo(null);
        map.put("Единичная ф-я", new UnitFunction());
        map.put("Квадратная ф-я", new SqrFunction());
        map.put("Константная ф-я", new ConstantFunction(constant));
        map.put("Нулевая ф-я", new ZeroFunction());
        map.put("Тождественная ф-я", new IdentityFunction());
        JLabel labelHelperConst = new JLabel("Если вы хотите создать константную ф-ю, введите константу!");
        JLabel labelConst = new JLabel("Константа:");
        JTextField fieldConst = new JTextField();
        JButton buttonConst = new JButton("Добавить константу");
        buttonConst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldConst.getText().equals("")) {
                    JOptionPane.showMessageDialog(dialog, "Введите корректно константу");
                } else {
                    constant = Double.parseDouble(fieldConst.getText());
                }
            }
        });
        JLabel label = new JLabel("Выберите 1 ф-ю");
        JComboBox<String> box1 = new JComboBox<>(new String[]{"", "Единичная ф-я", "Квадратная ф-я", "Константная ф-я", "Нулевая ф-я", "Тождественная ф-я"
        });
        box1.setEditable(true);
        box1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                functionStr1 = e.getItem().toString();
                System.out.println(functionStr1);
            }
        });
        JLabel label2 = new JLabel("Выберите 2 ф-ю");
        JComboBox<String> box2 = new JComboBox<>(new String[]{"", "Единичная ф-я", "Квадратная ф-я", "Константная ф-я", "Нулевая ф-я", "Тождественная ф-я"
        });
        box2.setEditable(true);
        box2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                functionStr2 = e.getItem().toString();
                System.out.println(functionStr2);
            }
        });
        JButton over = new JButton("Создать");
        over.addActionListener(e -> {
            if (functionStr1 == null || functionStr2 == null) {
                JOptionPane.showMessageDialog(dialog, "Выберите функции");
            } else {
                part2 = map.get(functionStr2);
                part1 = map.get(functionStr1);
                WindowSize windowSize = new WindowSize();
                windowSize.setVisible(true);
                TableMath tableMath = new TableMath(factory, new CompositeFunction(part1, part2), windowSize.getSizeOf());
                tableMath.setVisible(true);
                dispose();
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER).addGroup(layout.createSequentialGroup().addComponent(labelHelperConst)).addGroup(layout.createSequentialGroup().addComponent(labelConst).addComponent(fieldConst).addComponent(buttonConst)).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup().addComponent(label).addComponent(box1)).addGroup(layout.createParallelGroup().addComponent(label2).addComponent(box2))).addComponent(over));
        layout.setVerticalGroup(layout.createSequentialGroup().addGroup(layout.createSequentialGroup().addComponent(labelHelperConst)).addGroup(layout.createParallelGroup().addComponent(labelConst).addComponent(fieldConst).addComponent(buttonConst)).addGroup(layout.createParallelGroup().addGroup(layout.createSequentialGroup().addComponent(label).addComponent(box1)).addGroup(layout.createSequentialGroup().addComponent(label2).addComponent(box2))).addComponent(over));

    }
}