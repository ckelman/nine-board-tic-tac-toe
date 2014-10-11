
/*
 * File: MinimaxComputerGame.java
 * Creator: George Ferguson
 * Created: Tue Jan 31 12:12:05 2012
 * Time-stamp: <Tue Jan 29 15:55:53 EST 2013 ferguson>
 */

import java.util.List;
import java.io.*;
import java.util.StringTokenizer;

/**
 * This class extends ComputerGame in order to pick the next
 * move more intelligently. I have coded up the top-level part
 * of AIMA Figure 5.3, since it might not be obvious how to do it.
 * But the rest is up to you...
 */
public class MinimaxComputerGame extends ComputerGame {
	private int CUTOFF = 4;


    /**
     * Return the best move for the nextPlayer in the current state,
     * computed using MINIMAX search. See AIMA 5.2.1 and Figure 5.3
     * (page 166) in particular.
     */
    @Override
    protected Move getNextComputerMove() {
	List<Move> moves = state.getPossibleMoves();
	if (moves.size() == 0) {
	    throw new NoPossibleMoveException();
	}
	// argmax_{a \in actions(s)} minValue(result(s,a))
	Move bestMove = null;
	int maxv = Integer.MIN_VALUE;
	for (Move move: moves) {
		int v = minValue(result(state,move),  Integer.MIN_VALUE,Integer.MAX_VALUE,0);
	    if (v >= maxv) {
		bestMove = move;
		maxv = v;
	    }
	}    
	System.out.println("Best move is: " + bestMove + ", utility: " + maxv);
	return bestMove;
    }

    protected int maxValue(State s, int alpha, int beta, int count) {
    	count+=1;
    	//if the state is terminal or the depth limit has been reached, returns the value of this state
    	if(s.isTerminal()||count>CUTOFF)
    	{
    		return utility(s);
    	}
    	else
    	{
    		//otherwise recursively calls min to find the worst case after applying each of the possible
    		//moves
    		int v = Integer.MIN_VALUE;
    		
    		List<Move> moves = s.getPossibleMoves();
    		for(Move mo: moves)
    		{
    			State s1 = state.copy();
    			s1.apply(mo);
    			int t = minValue(s1, alpha, beta,count);
    			if (t>v)
    				v = t;
    	   		//updates beta and alpha
        		if(v>=beta)
        			return v;
        		if(v>alpha)
        			alpha =v;
    		}
 
    		
    		return v;
    	}
	
    }

    protected int minValue(State s, int alpha, int beta, int count) {
    	count+=1;
    	//if the state is terminal or the depth limit has been reached, returns the value of this state
    	if (s.isTerminal()||count>CUTOFF)
    	{
    		return utility(s);
    	}
    	//otherwise recursively calls max to find the worst case after applying each of the possible
		//moves
    	else
    	{
    		int v = Integer.MAX_VALUE;
    		
    		List<Move> moves = s.getPossibleMoves();
    		for(Move mo: moves)
    		{
    			State s1 = s.copy();
    			s1.apply(mo);
    			int t = maxValue(s1,alpha,beta,count);
    			if (t<v)
    				v = t;
    			//updates beta and alpha
        		if(v<=alpha)
        		return v;
        		
        		if(v>beta)
        			beta = v;
    		}
    		
    		return v;
    	}
    }

    /**
     * Returns the utility of the given State (assumed terminal) for the
     * computer player.
     */
    protected int utility(State s) 
    {
    	//if the state is terminal thenit returns -infinity for a loss, zero for a tie
    	//and positive infinity for a win
	    if(s.isTerminal())
	    {
			Player winner = s.getWinner();
			if (winner == null) {
			    // Draw
			    return 0;
			} else if (winner.equals(humanPlayer)) {
			    // Lose
			    return Integer.MIN_VALUE;
			} else {
			    // Win
			    return Integer.MAX_VALUE;
			}
	    }
	    //otherwise delivers a guess on the how good the state is
	    else return heuristic(s);
    }

    
    private int heuristic(State s)
    {
    	int sum = 0;
    	NineBoard board = s.board;
    	for(int i=0;i<3;i++)
    	{
    		for(int j=0;j<3;j++)
    		{
    			//gets each board in the board, then 
    			Board b = board.board[i][j];
    			
    			//finds number of each player in the rows, collumns and diagonals
    			for(int row=0;row<3;row++)
    			{
    				int mySum = 0;
    				int opSum =0;
    				for(int col =0;col<3;col++)
    				{
    					if(b.grid[row][col]==humanPlayer)
    						opSum++;
    					
    					else if(b.grid[row][col]==humanPlayer.otherPlayer())
    						mySum++;
    				}
    				
    				//if the computer is in any of the spots, and the human is in none of the spots
    				if(mySum>0&&opSum<=0)
    				{
    					//returns 10^number of computer spots filled
    					sum+= (Math.pow(10, mySum));
    				}
    				
    				//if the human is in any of the spots, and the computer is in none of the spots
    				else if(mySum<=0&&opSum>0)
    				{
    					//returns -1*10^number of human spots filled
    					sum-= (Math.pow(10, mySum));
    				}

    			}
    			
    			
    			//finds values for collums
    			for(int row=0;row<3;row++)
    			{
    				int mySum = 0;
    				int opSum =0;
    				for(int col =0;col<3;col++)
    				{
    					if(b.grid[col][row]==humanPlayer)
    						opSum++;
    					
    					else if(b.grid[col][row]==humanPlayer.otherPlayer())
    						mySum++;
    				}

    					
    				if(mySum>0&&opSum<=0)
    				{
    					sum+= (Math.pow(10, mySum));
    				}
    				
    				else if(mySum<=0&&opSum>0)
    				{
    					sum-= (Math.pow(10, mySum));
    				}

    			}
    			
    			//finds values for diagonals
    			for(int row=0;row<3;row++)
    			{
    				int mySum = 0;
    				int opSum =0;
    				
    				if(b.grid[row][row]==humanPlayer)
						opSum++;
					
					else if(b.grid[row][row]==humanPlayer.otherPlayer())
						mySum++;
    				
    				if(row==2)
    				{

    					if(mySum>0&&opSum<=0)
        				{
        					sum+= (Math.pow(10, mySum));
        				}
        				
        				else if(mySum<=0&&opSum>0)
        				{
        					sum-= (Math.pow(10, mySum));
        				}
    				}

    			}
    			//second diagonal
    			for(int row=0;row<3;row++)
    			{
    				int mySum = 0;
    				int opSum =0;
    				
    				if(b.grid[row][2-row]==humanPlayer)
						opSum++;
					
					else if(b.grid[row][2-row]==humanPlayer.otherPlayer())
						mySum++;
    				
    				if(row==2)
    				{

    					if(mySum>0&&opSum<=0)
        				{
        					sum+= (Math.pow(10, mySum*2));
        				}
        				
        				else if(mySum<=0&&opSum>0)
        				{
        					sum-= (Math.pow(10, mySum*2));
        				}
    				}

    			}
    			
    		}
    	}
    	return sum;
    }

    /**
     * Return a new State which is the result of applying Move m in
     * State s.
     * <p>
     * It is crucial that you realize that you have to copy the State
     * before applying the Move. And that this is the source of the
     * memory overhead for state-space search. There are ways of trading
     * space for time, but this is simplest.
     */
    protected State result(State s, Move m) {
	State newState = s.copy();
	newState.apply(m);
	return newState;
    }
    public void play()
    {
    	//asks for fast or smart
    	MinimaxComputerGame game =new MinimaxComputerGame();
    	int x = -1;
    	while(x!=4&&x!=5)
    	{
    	System.out.println("Do you want a cutoff of 4(fast) or 5(smart)?");
	    	try{
				String line = stdin.readLine();
				StringTokenizer tokens = new StringTokenizer(line);
				x = Integer.valueOf(tokens.nextToken());
	
				
			}
			catch(Exception ex){
				System.out.println(ex);
			}
    	}
    	game.CUTOFF = x;
    	CUTOFF = x;
    	super.play();
    	
    }

    public static void main(String[] argv) {
    	
	 new MinimaxComputerGame().play();
    }

}
