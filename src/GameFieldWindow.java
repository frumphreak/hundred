//Кількість цифр 181!

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class GameFieldWindow extends JFrame implements ActionListener {


    static int razmerMassiva = 181;
    static short numberOfColums = 15;// Налаштування таблиці
    // кнопок
    static JButton[] arrayButton = new JButton[razmerMassiva];// Визначенний
    // масив кнопок
    String nameOfButton;
    static int[] nArr = new int[razmerMassiva];
    static int button1 = 9000, button2 = 9000;// будь-яке число, що перевищує
    // потенційний розмір масиву.
	/*
	 * button1 зберігає індекс першої НАТИСНУТОЇ кнопки в ОДНОМІРНОМУ масиві.
	 * button2 по аналогії для другої натиснутої кнопки.
	 */

    int IndexDiff = 0;// button2-button1
    int bigger, smaller, temp;

    int jobCountNajatiy = 0, previousI = 0;
    int buffer[] = new int[2];// зберігає конкретне значення масиву

    public GameFieldWindow() {
        super("Віконечко");
        addWindowListener(new WindowAdapt());
    }


    // Обробка натискання клавіш
    public void actionPerformed(ActionEvent actionEvent) {

        // Обробка 181 кнопки
        int countButton = 0;
        boolean zakreslennya = false;
        int prevIndexI = 0, prevIndexJ = 0, IndexI = 0, IndexJ = 0;// значення
        // індексів
        // i and j
        boolean knopkyNajali = false;
        String msg;

        // Обробка натискання клавіш вікна
        for (int i = 0; i < razmerMassiva; i++) {
            if (actionEvent.getSource() == arrayButton[i]) {
                jobCountNajatiy++;
                if (jobCountNajatiy == 1) {
                    countButton = 0;
                }// перше натискання
                if (jobCountNajatiy == 2) {
                    countButton = 1;
                    jobCountNajatiy = 0;
                }// друге натискання
                knopkyNajali = true;
                buffer[countButton] = nArr[i];// якщо кнопка 8, то і запише 8
                if (countButton == 0) {
                    prevIndexI = (int) i / numberOfColums;
                    prevIndexJ = (int) i % numberOfColums;
                    previousI = prevIndexI * numberOfColums + prevIndexJ;
                    arrayButton[i].hide();
                    button1 = i;
                }
                if (countButton == 1) {
                    button2 = i;
                    IndexDiff = button2 - button1;
                    if (IndexDiff < 0) {
                        IndexDiff = IndexDiff * (-1);// Робимо різницю ЗАВЖДИ
                        // додатньою
                        bigger = button1;
                        smaller = button2;
                    } else {
                        bigger = button2;
                        smaller = button1;
                    }

                }
                if (IndexDiff == 1) {
                    zakreslennya = Sravnenie();
                }
                if (IndexDiff > 1) {// тобто це не одна й та ж кнопка
                    if (IndexDiff % numberOfColums == 0) {// якщо різниця кратна
                        // 10 (тобто вони в
                        // одному стовпці)

                        if (IndexDiff == numberOfColums) {// і рівна стовпцям -
                            // це сусіди.
                            zakreslennya = Sravnenie();
                        }
                        // інакше
                        if (IndexDiff > numberOfColums) {// і більша за стовпці,
                            // то вони можуть
                            // бути сусідами
                            // лише тоді, коли
                            // всі інші елементи
                            // цього стовпця між
                            // ними рівні нулю
                            temp = bigger;
                            while (temp != smaller + numberOfColums) {// десятки
                                // всюди
                                // замінити
                                // на
                                // cols!!!
                                temp -= numberOfColums;
                                if (nArr[temp] != 0) {
                                    break;
                                }
                                if (temp == smaller + numberOfColums) {// це
                                    // значить,
                                    // шо
                                    // цикл
                                    // повністю
                                    // виконався
                                    zakreslennya = Sravnenie();
                                }
                            }
                        }
                    }
                    // різниця більша одиниці, але не кратна 10
                    // if(IndexDiff%numberOfColums!=0){
                    else {// це означає, шо вони в одному рядку
                        temp = bigger;
                        while (temp != smaller + 1) {
                            temp--;
                            if (nArr[temp] != 0) {
                                break;
                            }
                            if (temp == smaller + 1) {// це значить, шо цикл
                                // повністю виконався
                                zakreslennya = Sravnenie();
                            }
                        }
                    }
                }

                msg = Integer.toString(i);

                if (zakreslennya == true) {
                    arrayButton[i].show();
                    arrayButton[previousI].show();
                    arrayButton[i].setVisible(false);// i=173,indexI=17,IndexJ=3.
                    // 17*10+3==173//IndexI*numberOfColums+IndexJ
                    arrayButton[previousI].setVisible(false);// prevIndexI*numberOfColums+prevIndexJ
                    nArr[i] = 0;
                    nArr[previousI] = 0;
                }

                if ((zakreslennya == false) && (countButton == 1)) {
                    arrayButton[previousI].show();
                    arrayButton[i].show();
                    arrayButton[previousI].setVisible(true);
                    arrayButton[i].setVisible(true);
                }
            }

            if (knopkyNajali == true) {
                break;
            }
        }
    }


    boolean Sravnenie() {
        if (nArr[button1] == nArr[button2]) {
            return true;// System.out.println("Однакові");
        }
        if (nArr[button1] + nArr[button2] == 10) {
            return true;// System.out.println("Десяточка");
        } else
            return false;
    }



    static void ButtonsPainter(GameFieldWindow obj,int razmerMassiva) {
        LogicMainWindow lmw = new LogicMainWindow();
        lmw.CreateArrayOfButtons(razmerMassiva,arrayButton,nArr);

        // Кнопка витиснута
        Border raisedbevel = BorderFactory.createRaisedBevelBorder();
        for (int i = 0; i < razmerMassiva; i++) {// i<181
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
        ButtonsPainter(obj, razmerMassiva);
        obj.setVisible(true);
    }

}

class WindowAdapt extends WindowAdapter {
    public void windowClosing(WindowEvent ev) {
        System.exit(0);
    }
}