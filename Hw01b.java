
//package hw01b;

import java.util.*;
import java.io.*;

public class Hw01b
{
    static Scanner console = new Scanner(System.in);
    
    public static void main( String[] args ) throws FileNotFoundException
    {
        // Declaring and initalizing the program looping variable
        boolean programQuit = false;
        
        // Declaring and initializing variables
            String filename = "stocks.txt",
                   userMenuChoice;
            
            
            //PrintWriter outFile = new PrintWriter(filename);
            //Scanner inFile = new Scanner(new FileReader(filename));
            
            // Writing bogus data to a file. for debugging purposes.
            //outFile.print("AAPL 85.90 105.50 ");
            //outFile.print("MSFT 40.50 80.90");
            //outFile.close();

         
        do
        {
            userMenuChoice = menu();
          
            // Prints the menu choice. for debugging purposes.
            System.out.println("Your choice: " + userMenuChoice);

            switch (userMenuChoice)
            {
                case "1":
                    GetStockStats(filename);
                    break;
                case "2":
                    GetStockHigh(filename);
                    break;
                case "3":
                    GetStockLow(filename);
                    break;
                case "c":
                case "C":
                    System.out.print("Enter a stock filename: ");
                    filename = console.next();
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
    
    public static void GetStockStats ( String filename ) 
            throws FileNotFoundException
    {
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String userInputStockTicker,
               stockTicker;
        
        Double minPrice,
               maxPrice,
               avgPrice;
               
     
        // Gets input from user for which ticker they want.
        // converts the input to all capital letters.
        System.out.print("Enter a stock ticker: ");
        userInputStockTicker = console.next();
        userInputStockTicker = userInputStockTicker.toUpperCase();

        // Sets the stockTicker variable to the next string in file.
        stockTicker = inFile.next();

        // This gets us to the data of the ticker entered by user.
        while (!stockTicker.equals( userInputStockTicker ))
           stockTicker = inFile.next();

        // Enters data into each of these variables according 
        // to the ticker entered by the user.
        minPrice = inFile.nextDouble();
        maxPrice = inFile.nextDouble();
        avgPrice = ( ( minPrice + maxPrice ) / 2 );

        // Prints this data to the user. 
        System.out.printf(stockTicker + " min: %.2f max: %.2f avg: "
                + "%.2f", minPrice, maxPrice, avgPrice);
        System.out.println();
        System.out.println();
        
        inFile.close();
    }
    
    public static void GetStockHigh ( String filename ) 
            throws FileNotFoundException
    {
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String dummyStr,
               highestPriceTicker = "null";
        
        double dummyDouble,
               dummyDouble2,
               highestPriceNum = 0;
        
 
        while ( inFile.hasNext() )
        {
            dummyStr = inFile.next();
            dummyDouble = inFile.nextDouble();
            dummyDouble = inFile.nextDouble();
            
            if (highestPriceNum < dummyDouble)
            {
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble;
            }
        }
        
        System.out.printf(highestPriceTicker + " has highest price of %.2f", 
                highestPriceNum);
        System.out.println();
        System.out.println();
        

        inFile.close();
    }
    
    public static void GetStockLow (String filename) throws FileNotFoundException
    {
        Scanner inFile = new Scanner(new FileReader(filename));
        
        String dummyStr,
               highestPriceTicker = "error";
        
        double dummyDouble,
               dummyDouble2,
               highestPriceNum = 0;
        
        while ( inFile.hasNext() )
        {
            dummyStr = inFile.next();
            dummyDouble = inFile.nextDouble();
            dummyDouble2 = dummyDouble;
            dummyDouble = inFile.nextDouble();
            
            if (dummyDouble2 < dummyDouble)
            {
                highestPriceTicker = dummyStr;
                highestPriceNum = dummyDouble2;
            }
        }
        
        System.out.printf(highestPriceTicker + " has lowest price of %.2f", 
                highestPriceNum);
        System.out.println();
        System.out.println();
        

        inFile.close();
    }
}
