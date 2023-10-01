import java.util.Random;
import java.util.Scanner;

public class Minesweeper {

    // Class Özelliklerimiz

    String[][] gameBoard; // Oyun Alanı.
    int rows, columns;   // Oyun Alanının satır(rows) ve sütun(columns) miktarı.
    int mineCount; // Oyun alanı üzerindeki mayınların miktarı.
    int revealedMinesCount; // Ortaya çıkarılan mayın sayısı.

    boolean[][] openedCellsInBoard; // Açılan hücreleri tutan tahtamız.
    boolean isGameOver;

    //----------------------------------------------------------------------------------------------//

    // Constructor Methodumuz

    public Minesweeper(int rows, int columns) {  // Oyun Alanımızı ve Mayın Miktarını başlatan kurucu methodumuz.
        this.rows = rows;
        this.columns = columns;
        this.gameBoard = new String[rows][columns];   // Oyun Alanımız.
        this.mineCount = (rows * columns) / 4;       // Oyun alanının büyüklüğünün 1/4'ü kadar olan mayın sayısı.
        this.revealedMinesCount = 0;  // Ortaya çıkartılan mayın sayısı.
        this.openedCellsInBoard = new boolean[rows][columns]; // Açılan hücrelerin takip edilmesi.
        this.isGameOver = false; // Oyunun sona erme kontrolü
    }

    // Oyun Alanını oluşturan Method

    public void createBoard() {
        for (int a = 0; a < rows; a++) {
            for (int b = 0; b < columns; b++) {
                gameBoard[a][b] = " "; // Boş Hücrelerin Oluşturulması
                openedCellsInBoard[a][b] = false; // Başlangıç hücrelerin kapalı olarak gelmesi.
            }
        }
    }

    // Mayınların Tahtaya Rastgele Yerleştirilmesi

    public void addMines() {
        Random random = new Random();  // Mayınları yerleştirmek için kullanacağımız Random objemiz.
        int addedMines = 0;

        while (addedMines < mineCount) {  // Mayınların yerleştirilmesi.
            int row = random.nextInt(rows);
            int col = random.nextInt(columns);

            // Mayınların Farklı Hücrelere Yerleştirilmesi
            if (!gameBoard[row][col].equals("*")) {
                gameBoard[row][col] = "*";
                addedMines++;
            }
        }
    }

    // Oyun Alanının Görüntülenmesi

    public void showGameBoard() {
        String unicodeString = "\uD83D\uDCA3"; // Bomba Sembolü :)

        System.out.println("*************************************");
        System.out.println(unicodeString + "          The Minefield          " + unicodeString);
        System.out.println("*************************************");
        System.out.print("   ");

        for (int c = 0; c < columns; c++) {  // Sütun Numaraları
            System.out.printf("%4d ", c);
        }
        System.out.println();

        for (int d = 0; d < rows; d++) {  // Satır Numaraları
            System.out.printf("%2d| ", d);

            for (int e = 0; e < columns; e++) {
                if (openedCellsInBoard[d][e]) {
                    System.out.printf("  %s  ", gameBoard[d][e]); // Hücrenin açıldığında boş görünmesi
                } else {
                    System.out.print("  -  "); // Hücrenin kapalı olduğunda görüntüsü
                }
            }

            System.out.println();
        }
    }

    // Oyunu başlatan method.

    public void startGame() {
        Scanner getDataFromUser = new Scanner(System.in); // Scanner objemiz

        while (!isGameOver) {     // Oyun bitene kadar devam eden döngümüz.
            showGameBoard();

            String rowSign = "\u2192";
            String colSign = "\u2193";

            // Kullanıcıdan satır ve sütun numarasını alma.
            System.out.print("Please Select Row " + rowSign + " : ");
            int row = getDataFromUser.nextInt();
            System.out.print("Please Select Column " + colSign + " : ");
            int col = getDataFromUser.nextInt();

            // Kullanıcıdan alınan satır sütun değerlerinin geçersiz olması durumunda uyarı verip döngünün devam ettirilmesi. (Oyun tahtası dışında ya da negatif olma durumu)
            if (row < 0 || row >= rows || col < 0 || col >= columns) {
                System.out.println("Invalid Selection ! Please Select Valid Row and Column...");
                continue;
            }

            // Kullanıcı daha önce açtığı bir bir hücreyi tekrar açmaya çalışıyorsa uyarı mesajı verilip döngünün devam ettirilmesi.
            if (openedCellsInBoard[row][col]) {
                System.out.println("This cell already opened. Please select another one !!!");
                continue;
            }

            openedCellsInBoard[row][col] = true; // Seçilen hücrenin açık olarak işaretlenmesi.

            if (gameBoard[row][col].equals("*")) {  // Seçilen Hücrede Mayın Olması Durumu
                char unicodeCross = '✟';

                showGameBoard();  // Oyun tahtasının gösterilmesi
                System.out.println();
                System.out.println("************************************");
                System.out.println(unicodeCross + " KABOOM...You Stepped on a Mine...RIP " + unicodeCross); // Oyunu kaybetme durumunda verilen mesaj çıktısı.
                System.out.println("************************************");


                // Oyun sonlanmadan önce kullanıcıya tekrar oynamak isteyip istemediğinin sorulması.
                String playAgain;
                Scanner getData = new Scanner(System.in);

                System.out.print("Would you like to play again? Type 'Yes' for Try Again or Type Anything for Exit Game : ");
                playAgain = getData.nextLine();

                if (playAgain.equalsIgnoreCase("Yes")) {  // Oyuncunun tekrar oynamak istemesi durumu.
                    createBoard();
                    addMines();
                    showMinefield();
                    startGame();
                    getData.close();
                } else {
                    String sunSymbol = "\u2600";   // Oyuncunun oyunu sonlandırmak istemesi durumu.
                    System.out.println("Thank You For Playing Minesweeper ☺");
                    System.out.println("Have a Nice Day " + sunSymbol + " !! ");
                    isGameOver = true;
                }
            } else {
                String dangerSign = "\u26A0";
                int minePerimeter = findAdjMines(row, col);
                System.out.println("************************************");
                System.out.println(dangerSign + " The Mines Around " + dangerSign + " : " + minePerimeter);

                if (minePerimeter == 0) {  // Etrafında mayın olmaması durumunda boş olan hücrelerin açılması koşulu.
                    openEmptyCells(row, col);
                    gameBoard[row][col] = "0"; // Sıfır ile işaretle
                }

                // Oyunun Kazanılması Durumunun Kontrolü
                if (isGameWon()) {
                    char uniCodeVictory = '✌';
                    String smiley = "☺";
                    showGameBoard();
                    System.out.println(uniCodeVictory + smiley + " CONGRATULATIONS YOU WON THE GAME !!! " + smiley + uniCodeVictory);
                    isGameOver = true;
                }
            }
        }

        getDataFromUser.close();
    }

    public int findAdjMines(int row, int col) {  // Seçilen hücre etrafındaki mayın sayısını saydırtma...
        int mCount = 0; // Çevredeki mayın sayısını tutan değişken.

        for (int i = -1; i <= 1; i++) {  // Açılan Hücrenin yukarıdan aşağıya doğru tarayan döngü
            for (int j = -1; j <= 1; j++) {  // Açılan Hücrenin soldan sağa doğru tarayan döngü
                int mRow = row + i;  // Etraftaki hücrelerin satır koordinatları
                int mCol = col + j;  // Etraftaki hücrelerin sütun koordinatları

                if (mRow >= 0 && mRow < rows && mCol >= 0 && mCol < columns) { // Koordinatların oyun alanı içinde olması durumunda devam edilmesi
                    if (gameBoard[mRow][mCol].equals("*")) {  // Etrafta taranan hücrelerde mayın bulunma durumunda verilen mesajdaki mayın sayısını 1 arttırma.
                        mCount++;
                    }
                }
            }
        }

        return mCount; // Etrafta bulunan mayın sayısının döndürülmesi.
    }

    public void openEmptyCells(int row, int col) {
        //Seçilen hücrenin etrafındaki hücreleri tarayan iç içe döngümüz.
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int mRow = row + i;
                int mCol = col + j;

                if (mRow >= 0 && mRow < rows && mCol >= 0 && mCol < columns) {  // Taranan alanın oyun alanı içinde olmasının kontrolü
                    if (!openedCellsInBoard[mRow][mCol]) {
                        openedCellsInBoard[mRow][mCol] = true;  // Taranan alandaki hücre daha önce açılmamışsa açılması.
                        int isMinesNear = findAdjMines(mRow, mCol); // Açılan hücrenin etrafındaki mayın sayısının hesaplanması.

                        if (isMinesNear == 0) {  // Eğer bu hücrenin etrafında hiç mayın yoksa, bu hücrenin etrafındaki hücrelerin taranması.
                            openEmptyCells(mRow, mCol);
                        }
                    }
                }
            }
        }
    }

    // Oyunun Kazanılıp Kazanılmama Durumunun Kontrol Edilmesi

    boolean isGameWon() {
        int closedCells = 0;

        // Oyun alanındaki hücrelerin taranması ve açılmamıi hücrelerin sayılması.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (!openedCellsInBoard[i][j]) {
                    closedCells++;
                }
            }
        }

        return closedCells == mineCount;  // Kapalı Hücre sayısının mayın sayısına eşit olması durumunda oyunun kazanılması.
    }


    // Oluşturulan oyun haritasındaki mayınların yerini gösteren harita.
    public void showMinefield() {
        System.out.println("************************************");
        System.out.println("          Revealed Map of Mines       ");
        System.out.println("************************************");

        // Sütun numaralarını göster
        System.out.print("   ");
        for (int j = 0; j < columns; j++) {
            System.out.printf("%4d ", j);
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            // Satır numarasını göster
            System.out.printf("%2d| ", i);

            for (int j = 0; j < columns; j++) {
                if (gameBoard[i][j].equals("*")) {
                    System.out.print("  *  "); // Mayın hücresi
                } else {
                    System.out.print("  -  "); // Boş hücre
                }
            }
            System.out.println();
        }
    }
}
