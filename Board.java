
public class Board  {


    private int rows;
    private int cols;
    
    /** The grid of pieces */
    private Player[][] grid;
    
    

    public Board(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        grid = new Player[rows][cols];
        // set each cell of the board to null (empty).
        reset();

    }
    
    public void reset() {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                grid[r][c] = null;
            }
        }
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    
    /**
    * Returns the Player whose piece occupies the given location, 
    * @param row int
    * @param col int
    */
    public Player getCell(int row, int col ) throws IndexOutOfBoundsException{
        if( (row < 0) || (col < 0) || (row >= rows) || (col >= cols) ) {
            throw new IndexOutOfBoundsException();
        } else {
            return grid[row][col];
        }
    }
    
    //returns true if there are no more plays left
    public boolean boardFilled(){
        for(int r =0;r<rows;r++){
            for(int c = 0;c<cols;c++){
                if(grid[r][c] == null){
                    return false;
                }
            }
        }
        return true; 
    }

    // Returns true if move is possible given board state.  
    public boolean possibleMove(Move move) {
        // TODO: write this.  Right now, it ignores filled columns, claiming any move is possible
        for(int r = 0 ; r<rows; r++){
                if(grid[r][move.getColumn()] == null){
                    return true;
                }
        }
        return false;
    }
    
    // Adds a piece to the board for a given Move
    public void addPiece(Move move) {
        //TODO: this is a test stub, you need to rewrite this.
    	// grid[0][move.getColumn()] = move.getPlayer();
        int i = 0;
        while(grid[i][move.getColumn()]!=null){
            i++;
        }
        grid[i][move.getColumn()] = move.getPlayer();
    }

    // if the board contains a winning position, returns the Player that wins.
    // Otherwise, returns null.  You could ignore lastMove.
    public Player winner(Move lastMove) {
        // TODO: write this.  Currently, there is never a winnder.
        if(colWinner(lastMove)!=null){
            return colWinner(lastMove);
        }else if(rowWinner(lastMove)!=null){
            return rowWinner(lastMove);
        }else{
            return diagWinner(lastMove);
        }
    }

    public Player colWinner(Move lastMove){
        int x = 0;
        for(int r = 1; r<rows;r++){
            if(grid[r][lastMove.getColumn()]==(grid[r-1][lastMove.getColumn()])){
                x++;
            }else{
                x = 0;
            }
            if(x == 4){
                return lastMove.getPlayer();
            }
        }
        return null;
    }

    public Player rowWinner(Move lastMove){
        int x = 0;
        int fc = 0;
        if(lastMove.getColumn() == cols){
            fc = cols;
        }else if(cols-lastMove.getColumn()<4){
            fc = cols;
        }
        for(int r = 0;r<rows;r++){
            for(int c = lastMove.getColumn()-2;c<fc;c++){
                if(grid[r][c]==(grid[r][c-1])){
                    x++;
                }else{
                    x = 0;
                }
                if(x == 4){
                    return lastMove.getPlayer();
                }
            }
        }
        return null;
    }

    public Player diagWinner(Move lastMove){
        for(int r = 0;r<rows;r++){
            for(int c = 0;r<cols;c++){
                if(grid[r][c]==(grid[r+1][c+1])&&grid[r+1][c+1]==(grid[r+2][c+2])&&grid[r+2][c+2]==(grid[r+3][c+3])){
                    return lastMove.getPlayer();
                }
            }
        }
        return null;
    }

    
    
} // end Board class
