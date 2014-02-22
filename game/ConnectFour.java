package game;

/*
 * Game : Connect Four
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConnectFour
{
    private final int ROWS = 6, COLUMNS = 7;
    private final int CONSECUTIVE_CONNECTION_REQUIRED = 4;
    
    private enum Type
    {
        Red('R'), Yellow('Y');
        
        char character;
        
        private Type(char x)
        {
            character = x;
        }
        
        public char getChar()
        {
            return character;
        }
    }
    
    private char[][] grid;
    private int[] fillerIndex;
    
    public ConnectFour()
    {
        startGame();
    }
    
    private void startGame()
    {
        initializeGrids();
        display();
        boolean hasRedWon = false;
        boolean hasYellowWon = false;
        
        while (true)
        {
            if (isGridFull())
                break;
            
            getAndPutInput(Type.Red);
            hasRedWon = checkGrid(Type.Red);
            
            if (hasRedWon)
                break;
            
            getAndPutInput(Type.Yellow);
            hasYellowWon = checkGrid(Type.Yellow);
            
            if (hasYellowWon)
                break;
        }
        
        if (hasRedWon)
            System.out.println("The Red Player Won!");
        else if (hasYellowWon)
            System.out.println("The Yellow Player Won!");
        else
            System.out.println("Game is a Draw!");
    }
    
    private void initializeGrids()
    {
        grid = new char[ROWS][COLUMNS];
        
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = ' ';
        
        fillerIndex = new int[COLUMNS];
        java.util.Arrays.fill(fillerIndex, ROWS - 1);
    }
    
    private void display()
    {
        System.out.print("\f");
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
                System.out.print("|" + grid[i][j]);
            System.out.println("|");
        }
    }
    
    private boolean isGridFull()
    {
        for (int i = 0; i < ROWS; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
            {
                if (grid[i][j] == ' ')
                    return false;
            }
        }
        return true;
    }
    
    public void getAndPutInput(Type type)
    {
        boolean isValidInput = false;
        do
        {
            System.out.println("Drop a " + type + " disk at column [0-"
                               + (COLUMNS - 1) + "] : ");
            
            try
            {
                BufferedReader d = new BufferedReader(new InputStreamReader(System.in));
                int index = Integer.parseInt(d.readLine());
                put(type, index);
                isValidInput = true;
            }
            catch (Exception e)
            {
                System.out.println("Invalid Entry! Retry with some other value...\n");
            }
        }
        while (!isValidInput);
    }
    
    private void put(Type type, int index)
    {
        grid[fillerIndex[index]--][index] = type.getChar();
        display();
    }
    
    private boolean checkGrid(Type type)
    {
        // Check downward
        for (int i = 0; i <= ROWS - CONSECUTIVE_CONNECTION_REQUIRED; i++)
        {
            for (int j = 0; j < COLUMNS; j++)
            {
                int counter = 0;
                for (int k = i; k < CONSECUTIVE_CONNECTION_REQUIRED + i; k++)
                {
                    if (grid[k][j] == type.getChar())
                        counter++;
                }
                
                if (counter == CONSECUTIVE_CONNECTION_REQUIRED)
                    return true;
            }
        }
        
        // Check across
        for (int i = 0; i <= COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED; i++)
        {
            for (int j = 0; j < ROWS; j++)
            {
                int counter = 0;
                for (int k = i; k < CONSECUTIVE_CONNECTION_REQUIRED + i; k++)
                {
                    if (grid[j][k] == type.getChar())
                        counter++;
                }
                
                if (counter == CONSECUTIVE_CONNECTION_REQUIRED)
                    return true;
            }
        }
        
        // Check left to right diagonally
        for (int i = 0; i <= ROWS - CONSECUTIVE_CONNECTION_REQUIRED; i++)
        {
            for (int j = 0; j <= COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED; j++)
            {
                int counter = 0;
                for (int k = i, m = j; k < CONSECUTIVE_CONNECTION_REQUIRED + i; k++, m++)
                {
                    if (grid[k][m] == type.getChar())
                        counter++;
                }
                
                if (counter == CONSECUTIVE_CONNECTION_REQUIRED)
                    return true;
            }
        }
        
        // Check right to left diagonally
        for (int i = 0; i <= ROWS - CONSECUTIVE_CONNECTION_REQUIRED; i++)
        {
            for (int j = COLUMNS - 1; j >= COLUMNS - CONSECUTIVE_CONNECTION_REQUIRED; j--)
            {
                int counter = 0;
                for (int k = i, m = j; k < CONSECUTIVE_CONNECTION_REQUIRED + i; k++, m--)
                {
                    if (grid[k][m] == type.getChar())
                        counter++;
                }
                
                if (counter == CONSECUTIVE_CONNECTION_REQUIRED)
                    return true;
            }
        }
        
        return false;
    }
    
    public static void main(String args[])
    {
        new ConnectFour();
    }
}