//Кількість цифр 181!

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameFieldWindow extends JFrame implements ActionListener {
    // Тип гри (через закреслене можна або ні)
    private boolean crossed=false;
    // Кількість букв у слові людини на яку гадаютьі
    private int countOfLetters;

    // криво, фу
    LogicMainWindow lmw = new LogicMainWindow();

    JPanel mainPanel = new JPanel();
    JPanel first = new JPanel();
    JPanel second = new JPanel();

    JButton help= new JButton("Можливі варіанти");
    JButton newDistribution= new JButton ("Оновлення");
    JButton result= new JButton ("Вивести результат");




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
        lmw.createArrayOfButtons(razmerMassiva,arrayButton);

        // Кнопка витиснута
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        for (int i = 0; i < razmerMassiva-lmw.numberOfZeroInArray; i++) {// i<181
            first.add(arrayButton[i]);
            arrayButton[i].setBorder(raisedbevel);
            arrayButton[i].addActionListener(obj); // Запис кожнї кнопки до
            // ActionListener
        }
    }

    void workWithMainWindow(GameFieldWindow obj) { // Налаштування вікна
        // дефолтні розміри вікна
        //obj.setBounds(10, 10, 800, 600);
        obj.add(mainPanel);
        mainPanel.setLayout(new GridLayout(2, 1));
        mainPanel.add(first);
        mainPanel.add(second);

        obj.setExtendedState(MAXIMIZED_BOTH);// на весь екран
        first.setLayout(new GridLayout(0, numberOfColums));// Розмітка сторінки.
        ButtonsPainter(obj, LogicMainWindow.RAZMERMASIVA+LogicMainWindow.FORDATE);
        obj.setVisible(true);
        obj.setResizable(false);// якщо натиснути кнопку максимізації, то воно стає надто малим
        // бо не встановлені дефолтні розміри вікна

        second.setLayout(new GridLayout(1, 3));
        second.add(help);
        second.add(result);
        second.add(newDistribution);

        help.addActionListener(obj);
        newDistribution.addActionListener(obj);
        result.addActionListener(obj);
        result.setEnabled(false);


    }

    // Дізнатися, яка гра розпочалась.
    public void setCrossed(boolean crossed){
        this.crossed=crossed;
    }
    //Отримання довжини імені обраної особи
    public void setCountOfLetters(int countOfLetters){
        this.countOfLetters=countOfLetters;
    }


    // Обробка натискання клавіш
    public void actionPerformed(ActionEvent actionEvent) {
        // Обробка натискання клавіш вікна
        // Розмір масиву кнопок + розмір на дату
        for (int i = 0; i < LogicMainWindow.RAZMERMASIVA+LogicMainWindow.FORDATE; i++) {
            if (actionEvent.getSource() == arrayButton[i]) {
                if (crossed){
                    System.out.println(crossed);
                    lmw.disappear(arrayButton, i, numberOfColums,crossed);
                }
                else{
                    System.out.println(crossed);
                    lmw.disappear(arrayButton, i, numberOfColums,crossed);
                }
            }
        }

        if (actionEvent.getSource() == help) {
            System.out.println("help");
        }

        if (actionEvent.getSource() == result) {
            System.out.println("result");
        }

        if (actionEvent.getSource() == newDistribution) {
            for (int i = 0; i < LogicMainWindow.RAZMERMASIVA+LogicMainWindow.FORDATE-lmw.numberOfZeroInArray; i++) {
                first.remove(arrayButton[i]);
            }
            revalidate();
            repaint();
            System.out.println("newDistribution");
        }

    }
}


class WindowAdapt extends WindowAdapter {
    public void windowClosing(WindowEvent ev) {
        System.exit(0);
    }
}