/**
 *
 * @author Hüsnü Tığrak
 */

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Süsleme sembolleri
        String bombSymbol = "\uD83D\uDCA3";
        String gameSymbol = "\uD83C\uDFAE";
        String cloverSymbol = "\u2618";

        // Oyunun nasıl oynanacağı hakkında oyuncuya sunulan bilgiler
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(bombSymbol + "***** WELCOME TO MINESWEEPER GAME *****" + bombSymbol);
        System.out.println("*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*");
        System.out.println(" ");
        System.out.println(gameSymbol + "    How to Play ?    " + gameSymbol);
        System.out.println("******************************************************************************");
        System.out.println("1-)Enter the size of row and columns for create Map.\n"+
                           "2-)For step an area , enter the row number first then the column number.\n"+
                           "3-)When you stepped empty area , you will get hint for how many mines around at stepped area.\n"+
                           "4-)If there is no mines around where you stepped,that areas will be revealed.\n"+
                           "5-)When all mineless areas revealed game will be over and player wins.\n"+
                           "6-)If you step a landmine,game will be lost.Good Luck " + cloverSymbol);
        System.out.println("*******************************************************************************");
        Scanner scanner = new Scanner(System.in);

        int rows = 0;
        int columns = 0;

        // Kullanıcıdan harita oluşturması için alınan satır ve sütun numaralarının 0 ya da negatif olması durumunun kontrolü.
        while (rows <= 0 || columns <= 0) {
            System.out.print("Enter the number of rows (greater than 0) : ");
            rows = scanner.nextInt();
            System.out.print("Enter the number of columns (greater than 0) : ");
            columns = scanner.nextInt();

            if (rows <= 0 || columns <= 0) {
                System.out.println("Row and Column Size Must Be Greater Than 0. Please try again.");
            }
        }

        Minesweeper minesweeper = new Minesweeper(rows, columns);  // Minesweeper sınıfından oluşturulan nesnemiz.



        // Oyun alanının oluşturulması ve mayınların yerleştirilmesi
        minesweeper.createBoard();
        minesweeper.addMines();

        // Yaratılan oyun alanının üzerindeki mayınları gösteren harita
        minesweeper.showMinefield();

        // Oyunnun başlatılması
        minesweeper.startGame();

        scanner.close();
    }
}
