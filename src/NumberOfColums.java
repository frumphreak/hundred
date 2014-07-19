import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberOfColums extends JFrame implements ChangeListener,
        ActionListener {

    static NumberOfColums colums;
    private JSlider sliderCountColums = new JSlider(5, 30); // Значення, які ми
    // будемо в змозі
    // вибрати
    private JLabel textNumberOfColumnFromSlider = new JLabel("You choose : 17 колонок",
            JLabel.CENTER);
    private JLabel labelColums = new JLabel ("Кількість колонок",JLabel.CENTER);
    private JLabel nameOfHuman = new JLabel("Введіть ім'я людини",JLabel.CENTER);
    private JTextField tfNameOfHuman = new JTextField();
    private JButton firstVariant=new JButton("Неможливо через закреслене");
    private JButton secondVariant=new JButton("Можливо через закреслене");

    private JPanel variantsOfgame=new JPanel();

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
        setLayout(new GridLayout(6, 1));
        add(labelColums);
        add(sliderCountColums);
        add(textNumberOfColumnFromSlider);
        add(nameOfHuman);
        add(tfNameOfHuman);
        add(variantsOfgame);

        variantsOfgame.setLayout(new GridLayout(1, 2));
        variantsOfgame.add(firstVariant);
        variantsOfgame.add(secondVariant);

        firstVariant.addActionListener(this);
        secondVariant.addActionListener(this);
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
        //Шикарний іф
        //Якщо поле пусте, то не пускати до гри
        if (tfNameOfHuman.getText().equals("")){
            System.out.println("NULL");
            JOptionPane.showMessageDialog(null, "Введіть ім'я люидин!", "Попередження",
                    JOptionPane.WARNING_MESSAGE);
        }
        else{
            GameFieldWindow.numberOfColums = (short) sliderCountColums.getValue();
            // Створення основго вікна. (Main працює 1 раз).
            GameFieldWindow windFuck = new GameFieldWindow();
            windFuck.workWithMainWindow(windFuck);

            if (actionEvent.getSource() == firstVariant) {
                windFuck.setCrossed(false);
                NumberOfColums.colums.dispose();// Закриття діалогу
            }

            if (actionEvent.getSource() == secondVariant) {
                windFuck.setCrossed(true);
                NumberOfColums.colums.dispose();// Закриття діалогу
            }
        }
    }

    public void stateChanged(ChangeEvent e) { // Відстежування зміни слайдера
        textNumberOfColumnFromSlider.setText("You choose :"
                + ((JSlider) (e.getSource())).getValue()+" колонок");
    }
}
