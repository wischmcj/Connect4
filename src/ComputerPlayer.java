import java.awt.List;
import java.util.ArrayList;

public class ComputerPlayer {
static  int[][] game;
  static int height;
  static int cpuColor = 2; // 
  static int userColor = 1;
  /*
   * These are used to keep track of each players threats, and therefore to keep track of winning plays
   * These also help track who is in control in the game, and of which player is in control of which column. This is important feature of our
   * hard and medium computer players 
   */
  static ArrayList<Tile> cpuEvenThreat = new ArrayList<Tile>();
  static ArrayList<Tile> cpuOddThreat = new ArrayList<Tile>();
  static ArrayList<Tile> userOddThreat = new ArrayList<Tile>();
  static ArrayList<Tile> userEvenThreat = new ArrayList<Tile>();

  public static final int COLUMNNUMBER =6;
  static Boolean control = false; 

  static String cpuLevel = "easy";

  public ComputerPlayer(){
    game = Board.board;
  }
  /*
   * The core of the computer player, runs various checks and 
   * returns a move based on the chosen strategy
   */
  public static int playTile(int userPlay){
    int choice = 0;
    game = Board.board;
    setHeight();
    evaluateThreats(cpuColor);
    evaluateThreats(userColor);
    /*
     * This strategy involves no notion of control of the game. The computer player blocks wins where it can, and will try to 
     * create any threat possible, but does not "Think ahead". 
     */
    if(cpuLevel.equals("easy")){
      //Lastly, the computer player attempts to block doubles. Doing this assures that the computer player will have a move to make
      int blockDoubleColumn = findSingle(userColor);
      if(blockDoubleColumn != -1  && !(isFull(blockDoubleColumn))){
    	  choice = blockDoubleColumn;
      }
      //Fourth Priority is to build doubles
      int buildDoubleColumn = findSingle(cpuColor); 
      if(buildDoubleColumn != -1 && !(isFull(buildDoubleColumn))){
        choice =   buildDoubleColumn;
      }
      //Third priority is to create threats. Note our method already tests if spaces are playable
      int buildThreatColumn = findDouble(cpuColor);
      if(buildThreatColumn !=-1 && !(isFull(buildThreatColumn))){
        choice =   buildThreatColumn;
      }
       //Second priority is to block an opponents 
      if(!(userOddThreat.isEmpty())){
          if(isPlayable(userOddThreat.get(0).getCol(),userOddThreat.get(0).getRow())&& !(isFull(userOddThreat.get(0).getCol()))){
            choice =  userOddThreat.get(0).getCol();
            userOddThreat.remove(0);
          }
        }
      if(!(userEvenThreat.isEmpty())){
        if(isPlayable(userEvenThreat.get(0).getCol(),userEvenThreat.get(0).getRow())&& !(isFull(userEvenThreat.get(0).getCol()))){
          choice =  userEvenThreat.get(0).getCol();
          userEvenThreat.remove(0);
        }
      } 
      //First priority is a winning move
     if(!(cpuOddThreat.isEmpty())){
        if(isPlayable(cpuOddThreat.get(0).getCol(),cpuOddThreat.get(0).getRow())&& !(isFull(cpuOddThreat.get(0).getCol()))){
          choice =  cpuOddThreat.get(0).getCol();
          cpuOddThreat.remove(0);
        }
      }
	 if(!(cpuEvenThreat.isEmpty())){
	        if(isPlayable(cpuEvenThreat.get(0).getCol(),cpuEvenThreat.get(0).getRow())&& !(isFull( cpuEvenThreat.get(0).getCol()))){
	          choice =   cpuEvenThreat.get(0).getCol();
	          cpuEvenThreat.remove(0);
	        }
	  }
    }
    /*
     * This strategy involves more blocking than the last. It is very similar to the previous one,
     * but the computer will block two-in-a-row's and will prioritize blocking
     * odd threats over even threats as they are known to be more effective. This player 
     * will also not play in columns with an enemy threat, even or odd.
     */
    else if(cpuLevel.equals("medium")){
    	//Lastly, the computer player attempts to block doubles. Doing this assures that the computer player will have a move to make
        int preventDoubleColumn = findSingle(userColor);
        if(preventDoubleColumn != -1  && !(isFull(preventDoubleColumn))){
        	if(isThreatColumn( preventDoubleColumn,userColor)==null){
            	choice = preventDoubleColumn;
         
        	}
        }
        //Seventh Priority is to build doubles
        int buildDoubleColumn = findSingle(cpuColor);
        if(buildDoubleColumn != -1 && !(isFull(buildDoubleColumn))){
        	if(isThreatColumn( buildDoubleColumn,userColor) == null){
        		choice =   buildDoubleColumn;
        	}
       	}
        //Sixth priority is to create even threats. 
        int buildEvenThreatColumn = findDouble(cpuColor);
        if(buildEvenThreatColumn !=-1 && !(isFull(buildEvenThreatColumn)) && isEvenRow(getRow(buildEvenThreatColumn))  ){
        	if(isThreatColumn( buildEvenThreatColumn,userColor) ==null){
            	choice =   buildEvenThreatColumn;
        	}
        }
        //Fifth priority is to block even threats.
        int blockEvenThreatColumn = findDouble(userColor);
        if(blockEvenThreatColumn !=-1 && !(isFull(blockEvenThreatColumn )) && isEvenRow(getRow(blockEvenThreatColumn ))){
        	if(isThreatColumn( blockEvenThreatColumn,userColor) == null){
        		choice =   blockEvenThreatColumn ;
        		System.out.println( "CHOICE = " + choice);
        	}
        }
        //Fourth priority is to create odd threats. 
        int buildOddThreatColumn = findDouble(cpuColor);
        if(buildOddThreatColumn !=-1 && !(isFull(buildOddThreatColumn)) && !(isEvenRow(getRow(buildOddThreatColumn)))  ){
        	if(isThreatColumn( buildOddThreatColumn,userColor) == null){
        			choice =   buildOddThreatColumn;
        	}    	
        }
        //Third priority is to block odd threats.
        int blockOddThreatColumn = findDouble(userColor); 
        if(blockOddThreatColumn !=-1 && !(isFull(blockOddThreatColumn)) && !(isEvenRow(getRow(blockOddThreatColumn)))){
        	if(isThreatColumn( blockOddThreatColumn,userColor) == null){
      	         choice =   blockOddThreatColumn;
      	 	}
        }
        //Second priority is to block an opponents odd threat, then even threat
	  	for(int i = 0 ; i < userEvenThreat.size();i++){
	      	  if(isPlayable(userEvenThreat.get(i).getCol(),userEvenThreat.get(i).getRow())&& !(isFull(userEvenThreat.get(i).getCol()))){
		     	  choice =  userEvenThreat.get(i).getCol();
		      	  userEvenThreat.remove(i);
		 	  }	
	    }
        for(int i = 0 ; i < userOddThreat.size();i++){
       	  if(isPlayable(userOddThreat.get(i).getCol(),userOddThreat.get(i).getRow())&& !(isFull(userOddThreat.get(i).getCol()))){
       		  choice =  userOddThreat.get(i).getCol();
       		  userOddThreat.remove(i);
       	  }
        	  
        }
        //First priority is a winning move
        for(int i = 0 ; i < cpuOddThreat.size();i++){
    	     if(isPlayable(cpuOddThreat.get(i).getCol(),cpuOddThreat.get(i).getRow())&& !(isFull(cpuOddThreat.get(i).getCol()))){
    	    	choice =  cpuOddThreat.get(i).getCol();
    	    	cpuOddThreat.remove(i);
          }
        }
        for(int i = 0 ; i < cpuEvenThreat.size();i++){
  	       if(isPlayable(cpuEvenThreat.get(i).getCol(),cpuEvenThreat.get(i).getRow())&& !(isFull( cpuEvenThreat.get(i).getCol()))){
  	        	choice =   cpuEvenThreat.get(i).getCol();
  	        	cpuEvenThreat.remove(i);
  	       }
       }	
       choice = evaluateChoice(choice);
    }
    /*
     * This strategy involves a strict notion of control, where the previous strategy merely hints
     * at this notion by differentiating odd and even threats. The computer will first prevent the
     * creation of odd enemy threats and then attempt to create them, before performing similar 
     * actions to the previous strategies.
     * The notion of control allows the computer player to know when it should play in columns
     * containing threats, friendly and otherwise
     */
    else{
    	if(control){
    		//Lastly, the computer player attempts to block doubles. Doing this assures that the computer player will have a move to make
	        int preventDoubleColumn = findSingle(userColor);
	        
	        System.out.println("preventDoubleColumn " + preventDoubleColumn );
	        
	        if(preventDoubleColumn != -1  && !(isFull(preventDoubleColumn))){
	        	if(isThreatColumn( preventDoubleColumn,userColor)!= "Odd"){
	        			choice = preventDoubleColumn;
	            		System.out.println( "CHOICE = " + choice);

	        	}
	        }
	        //Seventh Priority is to build doubles
	        int buildDoubleColumn = findSingle(cpuColor);
	        System.out.println("buildDoubleColumn " + buildDoubleColumn );
	        
	        if(buildDoubleColumn != -1 && !(isFull(buildDoubleColumn))){
	        	if(isThreatColumn( buildDoubleColumn,userColor) != "Odd"){
	        		choice =   buildDoubleColumn;
	        		System.out.println( "CHOICE = " + choice);
	        	}
	       	}
	        //Sixth priority is to create even threats. 
	        int buildEvenThreatColumn = findDoubleAdvanced(cpuColor);
	        
	        System.out.println("buildEvenThreatColumn " + buildEvenThreatColumn );
	        
	        if(buildEvenThreatColumn !=-1 && !(isFull(buildEvenThreatColumn)) && isEvenRow(getRow(buildEvenThreatColumn))  ){
	        	if(isThreatColumn( buildEvenThreatColumn,userColor) != "Odd"){
	            	choice =   buildEvenThreatColumn;
	            	System.out.println( "CHOICE = " + choice);
	        	}
	        }
	        //Fifth priority is to block even threats.
	        int blockEvenThreatColumn = findDoubleAdvanced(userColor);
	        
	        System.out.println("blockEvenThreatColumn  " + blockEvenThreatColumn );
	        
	        if(blockEvenThreatColumn !=-1 && !(isFull(blockEvenThreatColumn )) && isEvenRow(getRow(blockEvenThreatColumn ))){
	        	if(isThreatColumn( blockEvenThreatColumn,userColor) != "Odd"){
	        		choice =   blockEvenThreatColumn ;
	        		System.out.println( "CHOICE = " + choice);
	        	}
	        }
	        
	        //Fourth priority is to create odd threats. 
	        int buildOddThreatColumn = findDouble(cpuColor);
	        
	        System.out.println("buildOddThreatColumn " + buildOddThreatColumn );
	        
	        if(buildOddThreatColumn !=-1 && !(isFull(buildOddThreatColumn)) && !(isEvenRow(getRow(buildOddThreatColumn)))  ){
	        	if(isThreatColumn( buildOddThreatColumn,userColor) != "Odd"){
	        			choice =   buildOddThreatColumn;
	        			System.out.println( "CHOICE = " + choice);
	        	}    	
	        }
	        //Third priority is to block odd threats.
	        int blockOddThreatColumn = findDouble(userColor);
	        
	        System.out.println("blockOddThreatColumn " + blockOddThreatColumn );
	        
	        if(blockOddThreatColumn !=-1 && !(isFull(blockOddThreatColumn)) && !(isEvenRow(getRow(blockOddThreatColumn)))){
	        	if(isThreatColumn( blockOddThreatColumn,userColor) != "Odd"){
	      	         choice =   blockOddThreatColumn;
	      	       System.out.println( "CHOICE = " + choice);
	        	}
	        }
	         //Second priority is to block an opponents odd threat, then even threat
		  	for(int i = 0 ; i < userEvenThreat.size();i++){
		      	  if(isPlayable(userEvenThreat.get(i).getCol(),userEvenThreat.get(i).getRow())&& !(isFull(userEvenThreat.get(i).getCol()))){
			     	  choice =  userEvenThreat.get(i).getCol();
			      	  System.out.println("user even threat " + choice );
			      	  userEvenThreat.remove(i);
			   
		      	  }
		    }
	        	
	        for(int i = 0 ; i < userOddThreat.size();i++){
	       	  if(isPlayable(userOddThreat.get(i).getCol(),userOddThreat.get(i).getRow())&& !(isFull(userOddThreat.get(i).getCol()))){
	       		  choice =  userOddThreat.get(i).getCol();
	       		  System.out.println("user odd threat " + choice );
	       		  userOddThreat.remove(i);
	
	          }
	        	  
	        }
	        //First priority is a winning move
	        for(int i = 0 ; i < cpuOddThreat.size();i++){
	    	     if(isPlayable(cpuOddThreat.get(i).getCol(),cpuOddThreat.get(i).getRow())&& !(isFull(cpuOddThreat.get(i).getCol()))){
	    	    	choice =  cpuOddThreat.get(i).getCol();
	    	    	 System.out.println("cpu odd threat " + choice );
	    	    	cpuOddThreat.remove(i);
	          }
	        }
	  	
	       for(int i = 0 ; i < cpuEvenThreat.size();i++){
	  	       if(isPlayable(cpuEvenThreat.get(i).getCol(),cpuEvenThreat.get(i).getRow())&& !(isFull( cpuEvenThreat.get(i).getCol()))){
	  	        	choice =   cpuEvenThreat.get(i).getCol();
	  	          System.out.println("cpu even threat " + choice );
	  	        	cpuEvenThreat.remove(i);
	  	       }
	       }	
    	}
 
    	else{
    		//Lastly, the computer player attempts to block doubles. Doing this assures that the computer player will have a move to make
	        int preventDoubleColumn = findSingle(userColor);
	        
	        System.out.println("preventDoubleColumn " + preventDoubleColumn );
	        
	        if(preventDoubleColumn != -1  && !(isFull(preventDoubleColumn))){
	        	if(isThreatColumn( preventDoubleColumn,userColor)==null){
	            	choice = preventDoubleColumn;
	            	System.out.println( "CHOICE = " + choice);
	        	}
	        }
	        //Seventh Priority is to build doubles
	        int buildDoubleColumn = findSingle(cpuColor);
	        System.out.println("buildDoubleColumn " + buildDoubleColumn );
	        
	        if(buildDoubleColumn != -1 && !(isFull(buildDoubleColumn))){
	        	if(isThreatColumn( buildDoubleColumn,userColor) == null){
	        		choice =   buildDoubleColumn;
	        		System.out.println( "CHOICE = " + choice);
	        	}
	       	}
	        //Sixth priority is to create even threats. 
	        int buildEvenThreatColumn = findDoubleAdvanced(cpuColor);
	        
	        System.out.println("buildEvenThreatColumn " + buildEvenThreatColumn );
	        
	        if(buildEvenThreatColumn !=-1 && !(isFull(buildEvenThreatColumn)) && isEvenRow(getRow(buildEvenThreatColumn))  ){
	        	if(isThreatColumn( buildEvenThreatColumn,userColor) ==null){
	            	choice =   buildEvenThreatColumn;
	            	System.out.println( "CHOICE = " + choice);
	        	}
	        }
	        //Fifth priority is to block even threats.
	        int blockEvenThreatColumn = findDoubleAdvanced(userColor);
	        
	        System.out.println("blockEvenThreatColumn  " + blockEvenThreatColumn );
	        
	        if(blockEvenThreatColumn !=-1 && !(isFull(blockEvenThreatColumn )) && isEvenRow(getRow(blockEvenThreatColumn ))){
	        	if(isThreatColumn( blockEvenThreatColumn,userColor) == null){
	        		choice =   blockEvenThreatColumn ;
	        		System.out.println( "CHOICE = " + choice);
	        	}
	        }
	        
	        //Fourth priority is to create odd threats. 
	        int buildOddThreatColumn = findDoubleAdvanced(cpuColor);
	        
	        System.out.println("buildOddThreatColumn " + buildOddThreatColumn );
	        
	        if(buildOddThreatColumn !=-1 && !(isFull(buildOddThreatColumn)) && !(isEvenRow(getRow(buildOddThreatColumn)))  ){
	        	if(isThreatColumn( buildOddThreatColumn,userColor) == null){
	        			choice =   buildOddThreatColumn;
	        			System.out.println( "CHOICE = " + choice);
	        	}    	
	        }
	        //Third priority is to block odd threats.
	        int blockOddThreatColumn = findDoubleAdvanced(userColor);
	        
	        System.out.println("blockOddThreatColumn " + blockOddThreatColumn );
	        
	        if(blockOddThreatColumn !=-1 && !(isFull(blockOddThreatColumn)) && !(isEvenRow(getRow(blockOddThreatColumn)))){
	        	if(isThreatColumn( blockOddThreatColumn,userColor) == null){
	      	         choice =   blockOddThreatColumn;
	      	       System.out.println( "CHOICE = " + choice);
	        	}
	        }
	         //Second priority is to block an opponents odd threat, then even threat
		  	for(int i = 0 ; i < userEvenThreat.size();i++){
		      	  if(isPlayable(userEvenThreat.get(i).getCol(),userEvenThreat.get(i).getRow())&& !(isFull(userEvenThreat.get(i).getCol()))){
			     	  choice =  userEvenThreat.get(i).getCol();
			      	  System.out.println("user even threat " + choice );
			      	  userEvenThreat.remove(i);
			   
		      	  }
		    }
	        	
	        for(int i = 0 ; i < userOddThreat.size();i++){
	       	  if(isPlayable(userOddThreat.get(i).getCol(),userOddThreat.get(i).getRow())&& !(isFull(userOddThreat.get(i).getCol()))){
	       		  choice =  userOddThreat.get(i).getCol();
	       		  System.out.println("user odd threat " + choice );
	       		  userOddThreat.remove(i);
	
	          }
	        	  
	        }
	        //First priority is a winning move
	        for(int i = 0 ; i < cpuOddThreat.size();i++){
	    	     if(isPlayable(cpuOddThreat.get(i).getCol(),cpuOddThreat.get(i).getRow())&& !(isFull(cpuOddThreat.get(i).getCol()))){
	    	    	choice =  cpuOddThreat.get(i).getCol();
	    	    	 System.out.println("cpu odd threat " + choice );
	    	    	cpuOddThreat.remove(i);
	          }
	        }
	  	
	       for(int i = 0 ; i < cpuEvenThreat.size();i++){
	  	       if(isPlayable(cpuEvenThreat.get(i).getCol(),cpuEvenThreat.get(i).getRow())&& !(isFull( cpuEvenThreat.get(i).getCol()))){
	  	        	choice =   cpuEvenThreat.get(i).getCol();
	  	          System.out.println("cpu even threat " + choice );
	  	        	cpuEvenThreat.remove(i);
	  	       }
	       }	
    	}
    	 choice = evaluateChoice(choice);
    }
  
   return choice;
  }
  /*
   * This is used by the hard strategy to determine if the computer layer has control of the game.
   * Note that control of the game involves having the most number of columns in which you have the 
   * lowest odd threat.
  */
  public static boolean determineControl(){
	  Boolean con = true;
	  int min = 6;
		  for(Tile t1 : cpuOddThreat){
				if(t1.getRow()< min){
					min = t1.getRow();
				}
		  }
		  for(Tile t2 : userOddThreat){
					if(t2.getRow()<min){
						con = false;
					}
		 }
	  return con;  
  }
  /*
   * This method is looking for three of the same color chip in a row and 
   * adding the tile that would complete that threat to an array for that type of threat.
   */
  public static void evaluateThreats(int color){
    for(int j = 0 ; j<=height; j ++ ){	
      for(int i = 0; i <= 6 ;i++ ){
        //Checks horizontally
    	 if(i<=4){
	        if((Board.board[i][j] == color) && Board.board[i+1][j] == color && Board.board[i+2][j] == color){
	        	if(isValid(i+3,j)){
		            arrayInsert(i+3,j, color);
		        }
	        	if(isValid(i-1,j)){
	        		arrayInsert(i-1,j, color);
	        	}
	        }
    	 }
    	 if(i<4){
	        if(Board.board[i][j] == color && Board.board[i+2][j] == color && Board.board[i+3][j] == color ){
	          if(isValid(i+1,j)){
	            arrayInsert(i+1,j, color);
	          }
	        }
    	 
	        if(Board.board[i][j] == color && Board.board[i+1][j] == color && Board.board[i+3][j] == color){
	          if(isValid(i+2,j)){
	            arrayInsert(i+2,j, color);
	          }
	        }
    	 }
    	 if(j<=2){
	        //Checks vertically
	        if(Board.board[i][j] == color && Board.board[i][ j+1] == color && Board.board[i][j+2]== color  && isValid(i,j+3) ){
	          arrayInsert(i,j+3, color);
	        }
    	 }   
    	 if(i<=4 && j<=3){
	        //Checks diagonally (right)
	        if(Board.board[i][j] == color && Board.board[i+1][j+1] == color && Board.board[i+2][j+2] == color  ){
	          if(isValid(i+3,j+3)){
	            arrayInsert(i+3,j+3, color);
	          }
	          if(isValid(i-1,j-1)){
	            arrayInsert(i-1,j-1, color);
	          }
	        }
    	 }
	     if(i<=3 && j<=2){   
	        if(Board.board[i][j] == color && Board.board[i+2][j+2] == color && Board.board[i+3][j+3] == color  ){
	          if(isValid(i+1,j+1)){
	            arrayInsert(i+1,j+1, color);
	          }
	        }
	        if(Board.board[i][j] == color && Board.board[i+1][j+1] == color && Board.board[i+3][j+3] == color  ){
	          if(isValid(i+2,j+2)){
	            arrayInsert(i+2,j+2, color);
	          }
	        }
    	 }
    	 if(i>=3 && j<=2 ){
	        //Checks diagonally (left) 
	        if(Board.board[i][j] == color && Board.board[i-1][j+1] == color && Board.board[i-2][j+2]== color  ){
	          if(isValid(i-3,j+3)){
	            arrayInsert(i-3,j+3, color);
	          }
	          if(isValid(i+1,j-1)){
	            arrayInsert(i+1,j-1, color);
	          }
	        }
	        if(Board.board[i][j] == color && Board.board[i-2][j+2] == color && Board.board[i-3][j+3] == color  ){
	          if(isValid(i-1,j+1)){
	            arrayInsert(i-1,j+1, color);
	          }
	        }
	        if(Board.board[i][j] == color && Board.board[i-1][j+1] == color && Board.board[i-3][j+3] == color  ){
	          if(isValid(i-2,j+2)){
	            arrayInsert(i-2,j+2, color);
	          }
	        }
	      }
      }	 
    }	
  }

  /*
   * The following method will find any two chips in a row with the same color that can be completed to three.
   * This method is used in the easy and medium strategies, while the hard uses and advanced version.
   */
  public static int findDouble(int color){
    for(int j = 0 ; j<=height; j++){	
      for(int i = 0; i < 6 ;i++ ){
	      if(i<=4 ){  
    	  	//Checks horizontally
	        if(Board.board[i][j] == color && Board.board[i+1][j] == color ){
	          if(isPlayable(i+2,j)){
	            return i+2;
	          }
	          if(isPlayable(i-1,j)){
	            return i-1;
	          }
	        }
	      }
	        //Checks vertically
	      if(j<=3){  
	      	if(Board.board[i][j] == color && Board.board[i][j+1] ==color && isPlayable(i,j+2) ){
	        	return i;
	        }
	      }
	      if(j<=3 && i <=5 ){
	        //Checks diagonally (right)
	        if(Board.board[i][j] == color && Board.board[i+1][j+1] == color  ){
	          if(isPlayable(i+2,j+2)){
	            return i+2;
	          }
	          if(isPlayable(i-1,j-1)){
	            return i-1;
	          }
	       }
	      }   
	      if(i>=1 && j<=3){ 
	    	//Checks diagonally (left) 
	        if(Board.board[i][j]  == color && Board.board[i-1][j+1] ==color  ){
	          if(isPlayable(i-2,j+2)){
	            return i-2;
	          }
	          if(isPlayable(i+1,j-1)){
	            return i+1;
	          }
	        }
	      }
      }	

    }
    return -1;
  }
  /*
   * This is used in the hard player strategy, it performs the same checks as above but finds
   * all possible spaces in which one can create a threat, and no others. It also neglects to check
   * vertically as these threats may be prevented easily if not blocked
   */
  public static int findDoubleAdvanced(int color){
    for(int j = 0 ; j<=height; j++){	
      for(int i = 0; i < 6 ;i++ ){
	      if(i<=4){ 
    	  	//Checks horizontally
	    	if(i<=5){
		        if(Board.board[i][j] == color && Board.board[i+1][j] == color ){
		          if(isPlayable(i+2,j)){
		            return i+2;
		          }
		        }
	    	}
		    if(Board.board[i][j] == color && Board.board[i+1][j] == color ){
			      if(isPlayable(i-1,j)){
			    	  return i-1;
			      }
	        }
	        if(Board.board[i][j] == color && Board.board[i+2][j] == color && isPlayable(i+1,j) ){
	          return i+1; 
	        }
	      }
        //Checks diagonally (right)
	    if(i<=4 && j<=3){
	        if(Board.board[i][j]  == color && Board.board[i+1][j+1] == color ){
	          if(isPlayable(i+2,j+2)){
	            return i+2;
	          }
	          if(isPlayable(i-1,j-1)){
	            return i-1;
	          }
	        }
	        if(Board.board[i][j] == color && Board.board[i+2][j+2] == color){
	          if(isPlayable(i+1,j+1)){
	            return i+1;
	          }
	        }
	    }
	    if(i>=1 && j<=3 ){
	        //Checks diagonally (left) 
	        if(Board.board[i][j] == color && Board.board[i-1][j+1] == color ){
	          if(isPlayable(i-2,j+2)){
	            return i-2;
	          }
	          if(isPlayable(i+1,j-1)){
	            return i+1;
	          }
	        }
		    if(i>=2){    
		        if(Board.board[i][j] == color && Board.board[i-2][j+2] == color  ){
		          if(isPlayable(i-1,j+1)){
		            return i-1;
		          }
		        }
		    }
	    }
      }	

    }
    return -1;
  }
  /*
   * This function is used to find friendly tiles to play against
   */
  public static int findSingle(int color){
    for(int j = 0 ; j<=height; j++){	
      for(int i = 0; i < 6 ;i++ ){
    	  	if(Board.board[i][j] == color){
	          if(isPlayable(i+1,j) ){
	            return i+1;
	          }
	          if(isPlayable(i-1,j) ){
	            return i-1;
	          }
	          if(isPlayable(i,j+1)){
	            return i;
	          }
	      }	
      }
    }
    for(int j = 0 ; j<=height; j++){	
      for(int i = 0; i < 6 ;i++ ){
        if(Board.board[i][j] == color){
          if(isPlayable(i+1,j+1) ){
            return i+1;
          }
          if(isPlayable(i-1,j+1)){
            return i-1;
          }
          if(isPlayable(i-1,j-1)){
            return i-1;
          }
          if(isPlayable(i+1,j-1)){
            return i+1;
          }
        }
      }
    }
    return -1;
  }
  /*
   * This function tells us if a given row is odd or even. As our index starts at 0, this is the opposite of the number 
   * itself being even or odd.
   */
  public static boolean isEvenRow(int row){
    return !(row%2==0);
  }

  // This is used to tell if a move is possible or not at that specific
  public static boolean isPlayable(int i, int j){
	  if(isValid(i,j)){
		  if(j!=0){
			  if(Board.board[i][j-1] != 0 && Board.board[i][j]==0){
				  System.out.println("isPlayable  " + i + ", " + j);
				  return true;
		      }
	      }
		  else{
			  if(Board.board[i][j] ==0){
				  return true;
			  }
		  }
	  }	
	 return false;
  }
//This is used to tell if a move is possible or not at that specific
 public static boolean isFull(int col){
   if(Board.board[col][5] !=0){
       return true;
   }
   return false;
 }
  /*
   * this is used to tell whether the given coordinates for a tile refer to a tile or if they are out of range of the board
   */
  public static boolean isValid(int i, int j){
    if(6>=i && j>=0 && i>=0 && j<=5){
      return true;
    }
    return false;
  }
  /*
   * This is used by the evaluateThreat function to ease code readability and shorten the code
   */
  public static void arrayInsert(int a, int b, int color ){
	if(Board.board[a][b] ==0){
	  Tile toAdd =  new Tile(a,b);
		 if(cpuColor == color){
	      if(isEvenRow(b) && !(cpuEvenThreat.contains(toAdd))){
	        cpuEvenThreat.add(toAdd);
	        System.out.println("cpu even threat entered "+a+","+ b+"-------------------------------------");
	        
	      }
	      else{
	    	  if(!(cpuOddThreat.contains(toAdd))){
	    		  cpuOddThreat.add(toAdd);
	    		  System.out.println("cpu odd threat entered "+a+","+ b+"-------------------------------------");
	    	  }
	   }
	    }
	    else{
	      if(isEvenRow(b) && !(userEvenThreat.contains(toAdd))){
	        userEvenThreat.add(toAdd);
	        System.out.println("user even threat entered  "+a+","+ b+"-------------------------------------");
	        
	      }
	      else{
	    	  if(!(userOddThreat.contains(toAdd))){
		        userOddThreat.add(toAdd);
		        System.out.println("user odd threat entered  "+a+","+ b+"-------------------------------------");
	    	  }
	      }
	    }
	  }
  	}
  /*
   * This allows us to find the the height of the tallest row in order to check for moves faster by eliminating possibilities.
   */
  private static void setHeight() {
    int max = 0;
    for(int i= 0; i < COLUMNNUMBER; i++ ){
      int j=0;
      if(j<6 && i < 7){
	      while(j <= 5 && Board.board[i][j]!=0 ){
	        if(j > max ){
	          max = j;
	        }
	        j++;
	      }
      }    
      height = max;
    }
  }
  /*
   * Used in the medium and hard strategies to avoid threat columns therefore allowing a premature win 
   */
  public static String isThreatColumn(int i, int player){
    if(player == cpuColor){
      for(Tile t: cpuEvenThreat){
        if(t.col == i){
          return "Even";
        }
      }
      for(Tile t: cpuOddThreat){
        if(t.col == i){
          return "Odd";
        }		
      }
    }
    if(player == userColor){
      for(Tile t: userEvenThreat){
        if(t.col == i){
          return "Even";
        }
      }
      for(Tile t: userOddThreat){
        if(t.col == i){
          return "Odd";
        }
      }
    }
    return null;
  }
  
  public static int getRow(int col){
	 int i =0;
	 while(i<=5){
		  if(Board.board[col][i]!=0){
			  i++;
		  }
		  else{
			  return i;
		  }
	  }
	 return i;
  }
  /*
   * This method is used in the event all of the computers possible plays 
   */
  public static int evaluateChoice(int choice){
		  for(int i = 0;i<7;i++){
			  if(Board.board[i][5] != 0 ){
				  choice = (choice +1) %6;
			  }
		  }
	  return choice;
  }

  public static void main (String args[]){
	
  }
  
}
