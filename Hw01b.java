/*

    Programmer  : Anthony D'Ambrosio
    Date        : 10/5/2015
    Purpose     : Reads data from a file. finds highs, lows, and the avg. 
    Limitations : There must be a data file titled stocks.txt inside the same 
                  folder.

*/
//package hw01b;
import java.util.*;
import java.io.*;

public class Hw01b
{
    static Scanner console = new Scanner(System.in);
    
    public static void main( String[] args ) throws FileNotFoundException
    {
        // Declaring and initalizing program variables. 
        boolean programQuit = false;
        
        String filename = "stocks.txt",
               userMenuChoice;
        
        // Main loop that keeps the programing running.
        do
        {
            // Calls the menu and returns the users choice. 
            userMenuChoice = menu();
          
            // Prints the menu choice. for debugging purposes.
            System.out.println("Your choice: " + userMenuChoice);
            
            switch (userMenuChoice)
            {
                case "1":
                    GetStockStats(filename);
                    break;
                case "2":
                    GetStockHighLow(filename, userMenuChoice);
                    break;
                case "3":
                    GetStockHighLow(filename, userMenuChoice);
                    break;
                case "c":
                case "C":
                    System.out.print("Enter a stock filename: ");
                    filename = console.next();
                    System.out.println(filename);
                    break;
                case "q":
                case "Q":
                    programQuit = true;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    System.out.println();
                    break;
            }
        } while (programQuit != true);
    }
    
    // Menu 
    public static String menu()
    {
        System.out.println("Enter '1' to get max, min and avg of a stock");
        System.out.println("Enter '2' to get stock ticker with highest price");
        System.out.println("Enter '3' to get stock ticker with lowest price");
        System.out.println("Enter 'c' to change the stockfile name");
        System.out.println("Enter 'q' to quit");
        System.out.print("Your choice: ");
        String userMenuChoice = console.next();
        System.out.println();
        return userMenuChoice;
    }
    
    // Finds the lowest, highest, and average of a specific stock.
    public static void GetStockStats ( String filename ) 
            throws FileNotFoundException
    {
        
        //Declaring and initializing variables.
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String userInputStockTicker = "error",
               stockTicker,
               dummyStr;
        
        Double minPrice = 0.0,
               maxPrice = 0.0,
               avgPrice = 0.0,
               dummyDouble = 0.0;
        
        int stockCount = 0;
               
        
        // Gets stock ticker information from user. 
        System.out.print("Enter a stock ticker: ");
        userInputStockTicker = console.next();
        // Converts user choice to all capitals like in the text file. 
        userInputStockTicker = userInputStockTicker.toUpperCase();
       
        // Runs until there is nothing left in the file.
        while ( inFile.hasNext() )
        {
            // If the next string is equal to user choice we step in, otherwise
            // we dont bother.
            if ( inFile.next().equals( userInputStockTicker ) )
            {
                // Fills a dummy variable. 
                dummyDouble = inFile.nextDouble();
                
                // If this is the first time running into a stock match we 
                // initalize the min and max. If we dont the next if statmenets
                // wont catch.
                if (stockCount == 0)
                {
                    minPrice = dummyDouble;
                    maxPrice = dummyDouble;
                }
                
                // If min is greater than next double, set min to the double. 
                if (minPrice > dummyDouble)
                    minPrice = dummyDouble;
                
                // if max is less than next double, set max to the double. 
                if (maxPrice < dummyDouble)
                    maxPrice = dummyDouble;
                
                // average is the sum of all the stocks. we will divide by 
                // number of that stock later.
                avgPrice+= dummyDouble;
                
                // Holds how many times we run into that specific stock.
                stockCount++;

            }
        }
        
       //System.out.println("stockCount = " + stockCount);
       //System.out.println("avg not divided = " + avgPrice);

        // It is impossible for the avg price to be 0 if we ran into that stock
        // therefore this is our check if the stock actually exists. 
        if ( avgPrice == 0.0 )
            System.out.println(userInputStockTicker + " was not found");
        // else, the stock exists. 
        else
        {
            // Divde the avgprice by how many stocks of that type we ran into.
            avgPrice = avgPrice / stockCount;
            // Prints the min, max and avg to the user. 
            System.out.printf(userInputStockTicker + " min: %.2f max: %.2f avg: "
                + "%.2f", minPrice, maxPrice, avgPrice );
            System.out.println();
        }
        inFile.close();
    }

    public static void GetStockHighLow(String filename, String menuChoice) 
            throws FileNotFoundException
    {
        // Declaring and initializing variables 
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String dummyStr = "error",
               lowestPriceTicker = "error",
               highestPriceTicker = "error";
        
        double dummyDouble = 0.0,
               lowestPriceNum = 0,
               highestPriceNum = 0;
       
        
        // Runs for as long as there is data inside the file.
        for (int c = 0; inFile.hasNext(); c++)
        {
            // Variable to hold the next string.
            dummyStr = inFile.next();
            // Variable to hold the next double. 
            dummyDouble = inFile.nextDouble();
            
            // If this is the first time the loop is running, we want to 
            // initializing lowest and ticker, otherwise the next if wont catch.
            if (c == 0)
            {
                lowestPriceTicker = dummyStr;
                lowestPriceNum = dummyDouble;
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble;
            }
            
            // If the current lowest is greater than the next double, set 
            // lowest equal to that double and get the name of that stock.
            if (lowestPriceNum > dummyDouble)
            {
                lowestPriceTicker = dummyStr;
                lowestPriceNum = dummyDouble;
            }
            
            if (highestPriceNum < dummyDouble)
            {
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble;
            }
        }
        
        
        if ("2".equals( menuChoice ))
        {
            System.out.printf(highestPriceTicker + " has highest price of %.2f", 
                highestPriceNum);
            System.out.println();
        }
        
        if ("3".equals( menuChoice ))
        {
            System.out.printf(lowestPriceTicker + " has lowest price of %.2f", 
                lowestPriceNum);
            System.out.println();
        }
        System.out.println();

        inFile.close();
    }

    /* pre extra credit
    // Finds the highest value of all data in text file. 
    public static void GetStockHigh ( String filename ) 
            throws FileNotFoundException
    {
        // Declaring and initializing variables.
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String dummyStr,
               highestPriceTicker = "null";
        
        double dummyDouble,
               highestPriceNum = 0;
       
        // Runs for as long as there is data in the file.
        for (int c = 0; inFile.hasNext(); c++ )
        {
            // Holds the next string.
            dummyStr = inFile.next();
            // Holds the next double. 
            dummyDouble = inFile.nextDouble();
            
            // If this is the first time the loop is running, we want to 
            // initalize the variables. Otherwise the next if statement 
            // wont catch.
            if (c == 0)
            {
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble;
            }
            
            // If the current highest price is less than the next double 
            // we make the highest price equal to the new double and get the 
            // ticker name.
            if (highestPriceNum < dummyDouble)
            {
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble;
            }

        }
        
        
        // Prints data out to the user. 
        System.out.printf(highestPriceTicker + " has highest price of %.2f", 
                highestPriceNum);
        System.out.println();
        System.out.println();
        
        inFile.close();
    }
    
    // Finds the lowest value of all data in text file. 
    public static void GetStockLow (String filename) 
            throws FileNotFoundException
    {
        
        // Declaring and initializing variables 
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String dummyStr,
               lowestPriceTicker = "error";
        
        double dummyDouble,
               lowestPriceNum = 0;
       
        
        // Runs for as long as there is data inside the file.
        for (int c = 0; inFile.hasNext(); c++)
        {
            // Variable to hold the next string.
            dummyStr = inFile.next();
            // Variable to hold the next double. 
            dummyDouble = inFile.nextDouble();
            
            // If this is the first time the loop is running, we want to 
            // initializing lowest and ticker, otherwise the next if wont catch.
            if (c == 0)
            {
                lowestPriceTicker = dummyStr;
                lowestPriceNum = dummyDouble;
            }
            
            // If the current lowest is greater than the next double, set 
            // lowest equal to that double and get the name of that stock.
            if (lowestPriceNum > dummyDouble)
            {
                lowestPriceTicker = dummyStr;
                lowestPriceNum = dummyDouble;
    
            }
        }
        
        System.out.printf(lowestPriceTicker + " has lowest price of %.2f", 
                lowestPriceNum);
        System.out.println();
        System.out.println();
        

        inFile.close();
    }
    */

}

