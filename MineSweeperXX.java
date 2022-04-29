/*
** Student Name:Oscar Wu
** Student ID: V00876811
** CSC 110 Fall of 2016
** Assignment: MineSweeper
*/
import java.util.Scanner; //to use scanner
import java.util.Random;


public class MineSweeperXX{

	public static void main (String[] args){
		Scanner console = new Scanner (System.in);
    String[][] grid = new String[8][8];
			for(int i=0; i<grid.length; i++){
		  		for(int j=0; j<grid[i].length; j++){
		  			grid[i][j]=".";
		  		}
		  	}
    //fullGrid is the answer key map of the game
    String[][] fullGrid = initializeFullGrid(grid);
			for(int i=0; i<fullGrid.length; i++){
				for(int j=0; j<fullGrid[i].length; j++){
				}
			}

		int row = 0;
		int col = 0;
    String[][] ongoingGrid =new String[8][8];
			for(int i=0; i<grid.length; i++){
		  		for(int j=0; j<grid[i].length; j++){
		  			ongoingGrid[i][j]=".";
		  		}
		  	}
		drawFullGrid(ongoingGrid);
    //WIN or lose
    //If there are 10 bombs/ 10"." remining, user WIN!
    int bombscounter=0;
		while(bombscounter!=10){
			bombscounter = 0;
      //prompt user for row and col number
			System.out.print("Select a cell. Row value (a digit between 0 and 7):");
			row = console.nextInt();
			System.out.print("Select a cell. Column value (a digit between 0 and 7):");
			col = console.nextInt();
      //if user did not select bomb...
			if(!fullGrid[row][col].equals("B")){
			  ongoingGrid = revealGridCell(row,col,fullGrid,ongoingGrid);
			  drawFullGrid(ongoingGrid);
			  for(int i=0;i<fullGrid.length;i++){
				  for(int j=0; j<fullGrid[i].length;j++){
					  if(ongoingGrid[i][j].equals(".")){
						  bombscounter++;
					  }
				  }
			  }
		  }else{ //if user selected bomb, go through fullGrid to reveal all bombs
			  for(int i=0;i<fullGrid.length;i++){
				  for(int j=0; j<fullGrid[i].length;j++){
					  if(fullGrid[i][j].equals("B")){
						  ongoingGrid[i][j] = fullGrid[i][j];
					  }
				  }
			  }
			  ongoingGrid[row][col] = "X"; //put an "X" on selected bomb.
			  break; //to escape the while loop.
		  }
	  }
	  if(bombscounter==10){
			System.out.println("Congrats! You Won!");
	  }else{
		  System.out.println("BOOOOOOM! Game Over!");
      drawFullGrid(ongoingGrid);
	  }
  }

  /*Place 10 bombs at random game grid
  for each grid cell that does not contain a bomb,
  record number of bombs adjacent to the cell*/
  public static String[][] initializeFullGrid(String[][] grid){
    //create a 8x8 grid/gameboard
    //background grid to keep track of bombs' location
	  int rows = 8;
    int cols = 8;
    //create random.
    Random r = new Random();
    //number of bombs
    int numbombs = 10;
    int counter = 0;

    while(counter < numbombs){
      int randrows = r.nextInt(rows-1);
      int randcols = r.nextInt(cols-1);
      if(grid[randrows][randcols].equals(".")){
        grid[randrows][randcols] = "B";
        counter++;
      }
    }
    //Reveal a number: neighbour count
    for (int i=0; i<grid.length;i++){
      for (int j=0; j<grid[i].length; j++){
      //make sure the cell do not contain a BOMB
    	  if (!grid[i][j].equals("B")){
      //initializing neighbourcounter
    		  int neighbourcounter = 0;
          //upper left
    		  if(i>0 && j>0 && grid[i-1][j-1].equals("B")){
    			  neighbourcounter++;
    		  }
          //upper
    		  if(i-1>=0 && grid[i-1][j].equals("B")){
    			  neighbourcounter++;
    		  }
          //upper right
    		  if(i-1>=0 && j+1<grid[i].length && grid[i-1][j+1].equals("B")){
    			  neighbourcounter++;
    		  }
          //left
    		  if(j-1>=0 && grid[i][j-1].equals("B")){
    			  neighbourcounter++;
    		  }
          //right
    		  if(j+1<grid[i].length && grid[i][j+1].equals("B")){
    			  neighbourcounter++;
    		  }
          //down left
    		  if(i+1<grid.length && j-1>=0 && grid[i+1][j-1].equals("B")){
    			  neighbourcounter++;
    		  }
          //down
    		  if(i+1<grid.length && grid[i+1][j].equals("B")){
    			  neighbourcounter++;
    		  }
          //down right
    		  if(i+1<grid.length && j+1<grid[i].length && grid[i+1][j+1].equals("B")){
    			  neighbourcounter++;
    		  }
    		  grid[i][j] = Integer.toString(neighbourcounter);
    	   }
      	}
    }
    return grid;
  }

  /*reveal cell:
  A:break out find a BOMB!!!
  B:blank cell uncover all(8) neighbouring cell
  (MIND: grid cell at an edge)
  C:NUMBER, show number of bomb in neighbouring(8) cell*/
  public static String[][] revealGridCell(int row, int col, String[][]grid, String[][]gameboard){
    //Reveal a BLANK cell
    //open neibourgh cells that are number or blank.
    //make sure the cell do not contain a BOMB
    if (grid[row][col].equals("B")){
      gameboard[row][col]="X";
    }else if(!grid[row][col].equals("0")){
      gameboard[row][col] = grid[row][col];
    }else if(grid[row][col].equals("0")){
          //upper left
          if(row>0 && col>0){
            if(!grid[row-1][col-1].equals("0")){
            	 gameboard[row-1][col-1] = grid[row-1][col-1];
            }else{
              gameboard[row-1][col-1] = " ";
            }
          }
          //upper
          if(row>0){
            if(!grid[row-1][col].equals("0")){
                 gameboard[row-1][col] = grid[row-1][col];
            }else{
              gameboard[row-1][col] = " ";
            }
          }
          //upper right
          if(row>0 && col+1<grid[row].length){
            if(!grid[row-1][col+1].equals("0")){
               gameboard[row-1][col+1] = grid[row-1][col+1];
            }else{
              gameboard[row-1][col+1] = " ";
            }
          }
          //left
          if(col>0){
            if(!grid[row][col-1].equals("0")){
             gameboard[row][col-1] =  grid[row][col-1];
            }else{
              gameboard[row][col-1] = " ";
            }
          }
          //right
          if(col+1<grid[row].length){
            if(!grid[row][col+1].equals("0")){
              gameboard[row][col+1] = grid[row][col+1];
            }else{
              gameboard[row][col+1] = " ";
            }
          }
          //down left
          if(row+1<grid.length && col>0){
            if(!grid[row+1][col-1].equals("0")){
              gameboard[row+1][col-1] = grid[row+1][col-1];
            }else{
              gameboard[row+1][col-1] = " ";
            }
          }
          //down
          if(row+1<grid.length){
            if(!grid[row+1][col].equals("0")){
              gameboard[row+1][col] = grid[row+1][col];
            }else{
              gameboard[row+1][col] = " ";
            }
          }
          //down right
          if(row+1<grid.length && col+1<grid[row].length){
            if(!grid[row+1][col+1].equals("0")){
              gameboard[row+1][col+1] = grid[row+1][col+1] ;
            }else{
              gameboard[row+1][col+1] = " ";
            }
          }
          gameboard[row][col] = " ";
      }
      return gameboard;
  }


  //Draw current state of the game board to console window.
  public static void drawFullGrid(String[][] gameboard){
    //Gameboard
    System.out.println("  | 0 1 2 3 4 5 6 7");
    System.out.println("-------------------");
       for(int i=0; i<gameboard.length; i++){
         System.out.print(i + " |");
         for(int j=0; j<gameboard[i].length; j++){
           System.out.print(gameboard[i][j]+" ");
         }
         System.out.println();
       }
  }
}
