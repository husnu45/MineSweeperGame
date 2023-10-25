# The MineSweeper
## The Minesweeper Game v1.0
### Description
In this project , i created one of the most known old-fashion game "Minesweeper" with Java.
Game basicly playable in console.(No GUI used)

### How to Play 

[gamerules](https://github.com/husnu45/MineSweeperGame/assets/120014374/22b94909-f14c-416c-b9c2-1f58c7be0709)

### Creating Field Size by Player
Before starting game , player decides the size of row & column to create field size.

For Example ;

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/c394e2d4-a9bd-4f04-bc47-57b320f1bb13)

### The Field

After player decide size of the field , two maps welcomes us.

Revealed Map of Mines (Optional) : This map shows us the hidden mines in our Minefield.(This map was printed to check if our game works.Later this map can be hidden.)

Note: The mine count is always generated as 1/4 of the field size.

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/7bb97a29-dda6-4599-b12f-c33a598b698b)

### Opening Cells By Player

Player can open the cell by choosing Row & Column number.
As you can see after player select 3rd row and 5th column cell opened and there is no mine.

The Mines Around : After a player finds an empty cell, our game gives a hint to the player about how many mines are around the opened cell.

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/1110ffab-a720-49ce-a4a4-726aa03502d7)

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/720772c0-5aef-4019-b947-f516ae669d5c)

As you can see our revealed map of mines.Blue point player's choose and red ones are the mines around.

### Stepping a Mine & Game Over

If player steps a mine , game will be over.But if players wants play again can type "Yes" (Non-Sensitive Upper & Lower Case).If player wants quit game just type random character.

Selecting 'Yes' to play again starts the game with the same number of rows and columns entered before.

Player choose to not play again ;

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/1c29d631-2f9b-4e0c-9ad5-012c63329ee3)


![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/fc51972a-1205-4a79-b22a-ac06d41326ae)

### Opening Empty Fields When No Adjacent Mines are Present in the Selected Cell

If a player's selected cell and its surroundings have no mines, the game will open the mineless cells.And mark the point with zero "0".

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/5b0cc868-9d8f-40c5-9eb3-0f7551642f48)

### Selecting a Previously Opened Cell

If a player selects a cell that is already opened, the game will warn the player and ask for the row and column again.

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/c031ba56-78e4-4151-b0e0-bb117822d525)


### Winning The Game

If a player finds all the empty cells, the game will be won.

![image](https://github.com/husnu45/MineSweeperGame/assets/120014374/aae421de-3ce1-4282-88a4-eab4fe41513b)













