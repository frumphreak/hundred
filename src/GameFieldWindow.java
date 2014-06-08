//Кількість цифр 181!

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameFieldWindow extends JFrame implements ActionListener {
    static short numberOfColums = 15;// Налаштування таблиці
    // кнопок

    static JButton[] arrayButton =
            new JButton [LogicMainWindow.RAZMERMASIVA+LogicMainWindow.FORDATE];// Визначенний
    // масив кнопок

    public GameFieldWindow() {
        super("Віконечко");
        addWindowListener(new WindowAdapt());
    }


    void ButtonsPainter(GameFieldWindow obj,int razmerMassiva) {
        LogicMainWindow lmw = new LogicMainWindow();
        lmw.createArrayOfButtons(razmerMassiva,arrayButton);

        // Кнопка витиснута
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        for (int i = 0; i < razmerMassiva-lmw.numberOfZeroInArray; i++) {// i<181
            obj.add(arrayButton[i]);
            arrayButton[i].setBorder(raisedbevel);
            arrayButton[i].addActionListener(obj); // Запис кожнї кнопки до
            // ActionListener
        }
    }

    void workWithMainWindow(GameFieldWindow obj) { // Налаштування вікна
        // windFuck.setBounds(10, 10, 1350, 300);
        obj.setExtendedState(MAXIMIZED_BOTH);
        obj.setLayout(new GridLayout(0, numberOfColums));// Розмітка
        // сторінки.
        ButtonsPainter(obj, LogicMainWindow.RAZMERMASIVA+LogicMainWindow.FORDATE);
        obj.setVisible(true);
    }


    // Обробка натискання клавіш
    public void actionPerformed(ActionEvent actionEvent) {
        // Обробка натискання клавіш вікна
        for (int i = 0; i < LogicMainWindow.RAZMERMASIVA; i++) {
            if (actionEvent.getSource() == arrayButton[i]) {
                LogicMainWindow lmw = new LogicMainWindow();
                lmw.disappear(arrayButton, i, numberOfColums);
            }
        }
    }
}


class WindowAdapt extends WindowAdapter {
    public void windowClosing(WindowEvent ev) {
        System.exit(0);
    }
}