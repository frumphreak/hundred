import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class NumberOfColums extends JFrame implements ChangeListener,
        ActionListener {

    static NumberOfColums colums;
    private JSlider sliderCountColums = new JSlider(5, 30); // Значення, які ми
    // будемо в змозі
    // вибрати
    private JButton beginGame = new JButton("ПУСТЬ НАЧНЕТСЯ БИТВА");
    private JLabel textNumberOfColumnFromSlider = new JLabel("You choose : 17",
            JLabel.CENTER);

    NumberOfColums() {
        super("Вибір");
        windowsOption();
        addWindowsComponent();
    }

    private void windowsOption() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int sizeWidth = 400;
        int sizeHeight = 200;
        int locationX = (screenSize.width - sizeWidth) / 2;
        int locationY = (screenSize.height - sizeHeight) / 2;
        setBounds(locationX, locationY, sizeWidth, sizeHeight);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

    }

    private void addWindowsComponent() {
        setLayout(new GridLayout(3, 1));
        add(sliderCountColums);
        add(textNumberOfColumnFromSlider);
        add(beginGame);

        beginGame.addActionListener(this);
    }


    static public void createWindowWithSlider(NumberOfColums obj) {
        obj.workWithSlider();
        obj.pack();
        obj.setVisible(true);
        obj.setResizable(false);
    }

    private void workWithSlider() {
        sliderCountColums.setPaintLabels(true);
        sliderCountColums.setPaintTicks(true);
        sliderCountColums.setMinorTickSpacing(1);// Кілкість маленьких
        // познаочок. Йде по порядку
        sliderCountColums.setMajorTickSpacing(5);// Кількість великих позначок
        sliderCountColums.addChangeListener(this);
        sliderCountColums.setValue(17);// Встановлюємо початкове значення 5

    }

    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == beginGame) {
            GameFieldWindow.numberOfColums = (short) sliderCountColums.getValue();

            // Створення основго вікна. (Main працює 1 раз).
            GameFieldWindow windFuck = new GameFieldWindow();
            windFuck.workWithMainWindow(windFuck);
            NumberOfColums.colums.dispose();// Закриття діалогу
        }
    }

    public void stateChanged(ChangeEvent e) { // Відстежування зміни слайдера
        textNumberOfColumnFromSlider.setText("You choose :"
                + ((JSlider) (e.getSource())).getValue());
    }
}
