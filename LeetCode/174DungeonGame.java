/* In this question, I use the backward calculate that trace back from the destination and fill
all the columns with the minimum health that is needed to go to the destination from this grid.*/

public class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int result;						// the result that will be handin
        int row = dungeon.length;		// the number of rows in dungeon
        int column = dungeon[0].length;	// the number of columns in dungeon
        int i, j;						
        
        int[][] solution = new int[row][column];	// create a solution from to store
		
		/* initialize the destination grid cost*/
        solution[row-1][column-1] = dungeon[row-1][column-1] >= 0 ? 1 : -dungeon[row-1][column-1] + 1;
        
		/* Update the minimum for the last column*/
        for(i = row-2; i >= 0; i--){
            solution[i][column-1] = ( solution[i+1][column-1] - dungeon[i][column-1] ) > 0 ? ( solution[i+1][column-1] - dungeon[i][column-1]) : 1;
        }
        
		/* Update the minimum for the last row*/
        for(i = column-2; i >= 0; i--){
            solution[row-1][i] = ( solution[row-1][i+1] - dungeon[row-1][i] ) > 0 ? ( solution[row-1][i+1] - dungeon[row-1][i]) : 1;
        }
        
		/* start from the nearest unfilled point to the destination, we start to calculate the cost for each grid*/
        for(i = row-2; i >= 0; i--) {
        	for(j = column-2; j >= 0; j--){
        		int temp1 = solution[i+1][j] - dungeon[i][j];		// cost from the next row
        		int temp2 = solution[i][j+1] - dungeon[i][j];		// cost from the next column
        		
        		if(temp1 < temp2) {
        			solution[i][j] = temp1 > 0 ? temp1 : 1;
        		}
        		else {
        			solution[i][j] = temp2 > 0 ? temp2 : 1;
        		}
        	}
        }
        
        return solution[0][0];
    }
}