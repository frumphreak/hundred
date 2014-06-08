import javax.swing.*;

public class LogicMainWindow {
    int firstNumber=0,secondNumber=0;
    int click;
    int bigger,smaller;
    int[] nArr=new int[181];


    void createArrayOfButtons(int lenghtArray, JButton[] array) {
        createNumericArray(nArr);

        for (int i = 0; i < lenghtArray; i++) {
            JButton button = new JButton("" +  nArr[i]);
            array[i] = button;
        }
    }

    private void createNumericArray(int[] numericalArray) {

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

    void disappear(JButton [] arrayButton, int iterator, int numberOfColums){
        int flagOfZero=0;

        click++;
        if (click==1){
            arrayButton[iterator].hide();
            firstNumber=iterator;
            System.out.println("First: "+firstNumber);
        }
        if (click!=1){
            secondNumber=iterator;
            System.out.println("Second: "+secondNumber);
            click=0;
            System.out.println();


            if ((nArr[firstNumber]==nArr[secondNumber])||(nArr[firstNumber]+nArr[secondNumber]==10)){
                //Кнопки в одному рядку
                if (firstNumber/numberOfColums==secondNumber/numberOfColums){
                    // Якщо кнопки один біля одного
                    if(Math.abs(firstNumber-secondNumber)==1){
                        arrayButton[iterator].hide();
                        nArr[firstNumber]=0;
                        nArr[secondNumber]=0;
                    }
                    //Якщо кнопки на відстаі один від одного
                    else if(Math.abs(firstNumber-secondNumber)!=1){
                        biggerAndSmaller(firstNumber,secondNumber);
                        // Умова завжди більша на 1 від дійсності
                        int condition = bigger-smaller;
                        // условия проверки значений между выбраными клавишами
                        for (int i=0;i<condition;i++){
                            if (nArr[smaller+i]==0){
                                flagOfZero++;
                                System.out.println(flagOfZero);
                            }
                        }
                        //Якщо флаг дорівнює умові, то ховаємо і занудюємо
                        if (flagOfZero==condition-1){
                            arrayButton[iterator].hide();
                            nArr[firstNumber]=0;
                            nArr[secondNumber]=0;
                            flagOfZero=0;
                        }
                        else{
                            arrayButton[firstNumber].show();
                        }
                    }
                    else {
                        arrayButton[firstNumber].show();
                    }
                }


                //Для стовбіц
                else if (firstNumber%numberOfColums==secondNumber%numberOfColums){
                    // Якщо поруч знаходяться значення по вертикалі
                    if (Math.abs(firstNumber-secondNumber)==numberOfColums){
                        arrayButton[iterator].hide();
                        nArr[firstNumber]=0;
                        nArr[secondNumber]=0;
                    }
                    // Коли значення знаходяться по вертикалі на відстані декілької рядків
                    else{
                        biggerAndSmaller(firstNumber,secondNumber);
                        System.out.println(bigger+" "+smaller);

                        // Умова завжди більша на 1 від дійсності
                        int condition = (bigger-smaller)/numberOfColums;
                        // условия проверки значений между выбраными клавишами
                        for (int i=0;i<condition;i++){
                            if (nArr[smaller+numberOfColums*i]==0){
                                flagOfZero++;
                                System.out.println(flagOfZero);
                            }
                        }
                        //Якщо флаг дорівнює умові, то ховаємо і занудюємо
                        if (flagOfZero==condition-1){
                            arrayButton[iterator].hide();
                            nArr[firstNumber]=0;
                            nArr[secondNumber]=0;
                            flagOfZero=0;
                        }
                        else{
                            arrayButton[firstNumber].show();
                        }
                    }
                }
                else {
                    arrayButton[firstNumber].show();
                }
            }
            else {
                arrayButton[firstNumber].show();
            }

        }
    }

    private void biggerAndSmaller(int first, int second){
        if(first>second){
            bigger=first;
            smaller=second;
        }
        else {
            bigger=second;
            smaller=first;
        }
    }
}
