
public class MainClass {
    public static void main(String arg[]) {
        GameFieldWindow gameField=new GameFieldWindow();

        // Для створення отримуєм обєкт з іношо файлу
        // Створення основного вікна проходить в класі діалога
        NumberOfColums.colums = new NumberOfColums ();
        NumberOfColums.colums.createWindowWithSlider(NumberOfColums.colums);

    }
}
