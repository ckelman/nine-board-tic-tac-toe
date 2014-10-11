import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class NineBoard extends Board
{
	//Array used to keep track of the 9 boards
	public Board[][] board;
	//keeps track of the board being played on
	public Board currentBoard;
	
	//Creates a new NineBoard object
	public NineBoard()
	{
		
		board =  new Board[3][3];
		for(int i=0;i<3;i++)
		{
			for (int k=0;k<3;k++)
			{
				board[i][k] = new Board(i,k);

			}
		}
		currentBoard = null;
	}
	
	//copy's the NineBoard
	public NineBoard copy() {
		NineBoard b = new NineBoard();
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				b.board[i][j] = board[i][j].copy();
			}
		}
		
		b.currentBoard = currentBoard;
		return b;
}
	

	//checks for a winner by checking for a winner on any of the nine Boards
	public Player getWinner() 
	{
		
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				Player p = board[i][j].getWinner();
				if(p!=null)
					return p;
			}
		}
		return null;
		
	 }
	
	  //checks if full by checkking if every board is full
	  public boolean isFull() 
	  {
		for(int i=0;i<3;i++)
		{
			for(int j=0;j<3;j++)
			{
				if(!board[i][j].isFull())
					return false;
			}
		}
		return true;
	  }
	  
	  //Gets the list of all possible Moves
	  public List<Move> getPossibleMoves(Player player) 
	  {
		  //If there is no currentBoard to be played on, then the computer
		  //can play on any board, so gets every possible move
		  //for each board.  
		  if(currentBoard!=null&&!currentBoard.isFull())
			  return currentBoard.getPossibleMoves(player);
		  //otherwise, it gets every possible move for the current board to be played on
		  else
		  {
			  List<Move> moves = new ArrayList<Move>();
			  for(int i=0;i<3;i++)
			  {
				  for(int j=0;j<3;j++)
				  {
					  List<Move> temp = board[i][j].getPossibleMoves(player);
					  for(int k=0;k<temp.size();k++)
					  {
						  moves.add(temp.get(k));
					  }
				  }
			  }
			  return moves;
		  }
	  }
	  
	  
	    public void apply(Move move) throws TTTRuntimeException 
	    {
	    	//applies he move by first setting the currentBoard to the Move's board
	    	currentBoard = board[move.board.x][move.board.y];
	    	//Then applies the move to the board
	    	currentBoard.apply(move);
	    	//then sets the currentBoard to the location where the move was played
	    	currentBoard = board[move.x][move.y];

	    	
	    }
	    

	    //a function to print out the NineBoard
	    public void print()
	    {
	    	for(int bRow=2;bRow>=0;bRow--)
	    	{
	    		for(int lRow =2;lRow>=0;lRow--)
	    		{
	    			for(int bCol=0;bCol<3;bCol++)
	    			{
	    				System.out.print("|");
	    				for(int lCol=0;lCol<3;lCol++)
	    				{
	    					if(board[bCol][bRow].grid[lCol][lRow]==null)
	    						System.out.print(" ");
	    					else
	    					System.out.print(board[bCol][bRow].grid[lCol][lRow]);
	    					
	    					System.out.print("|");
	    				}
	    				System.out.print("  ");
	    			}
	    			System.out.println();
	    			if(lRow!=0)
	    			System.out.println("-------  -------  -------");
	    		}
	    		System.out.println();
	    	}
	    }
	    
	    public void print(PrintWriter out) 
	    {
	    	print();
	    	
	   }

	        public void print(PrintStream out) 
	        {
	    	print();
	        }

	     
}
