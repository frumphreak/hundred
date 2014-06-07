import javax.swing.*;

public class LogicMainWindow {

    void CreateArrayOfButtons(int lenghtArray, JButton[] array,int[] numericalArray) {
        CreateNumericArray(numericalArray);

        for (int i = 0; i < lenghtArray; i++) {
            JButton button = new JButton("" +  numericalArray[i]);
            array[i] = button;
        }
    }

    void CreateNumericArray(int[] numericalArray) {
        int arrayCounter = 0;
        // Проганяємо всі числа від 1 до 100;
        for (int numbers = 1; numbers < 101; numbers++) {// numbers < 101
            // Старша цифра числа
            if ((numbers > 9) && (numbers != 100)) {
                numericalArray[arrayCounter] = numbers / 10;
                arrayCounter++;// Наступна комірка при кожному записі
            }
            // Молодша фицра числа
            if (numbers % 10 != 0) {
                numericalArray[arrayCounter] = numbers % 10;
                arrayCounter++;// Наступна комірка при кожному записі
            }
            // Визначення числа 100
            if (numbers == 100) {
                numericalArray[arrayCounter] = 1;
            }
        }
    }
}
