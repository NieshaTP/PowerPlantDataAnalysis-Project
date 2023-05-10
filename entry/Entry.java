/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package entry;
import java.io.*;
import java.util.*;
/**
 *
 * @author nieshapatterson
 */
public class Entry {
    
    private String month;
    private int day;
    private int year;
    private float powerOutput;


    public Entry(String m, int d, int y, float p){
        month = m;
        day = d;
        year = y;
        powerOutput = p;
    }
    

    //getter methods
    public String getMonth(){ return month; }
    public int getDay(){ return day; }
    public int getYear() {return year;}
    public float getPowerO() {return powerOutput;}
    // setter methods
    public void setMonth(String newMonth) {month = newMonth;}
    public void setDay(int newDay) {day = newDay;}
    public void setYear(int newYear){year = newYear;}
    public void setPowerO(float newPowerO) {powerOutput = newPowerO;}
    
     /**
     * Option 1 of project
     * @param filename name of file w multiple data types
     * @return the ArrayList
     * @throws IOException
     */
    public static ArrayList<Entry> upload(String filename) throws IOException {
        File f = new File(filename);
        Scanner input = new Scanner(f);
        
        ArrayList<Entry> entries = new ArrayList<>();   
        while (input.hasNext()){
            String m = input.next();
            int d = input.nextInt();
            int y = input.nextInt();
            float p = input.nextFloat();
          
            Entry e = new Entry(m, d, y, p);
            entries.add(e);
        }
        return entries;
         
    }
    
    /**
    General menu for project options
    @param k The Scanner object used for user input
    @return An integer representing the user's choice
*/
  
    public static int printMenu(Scanner k){
        System.out.println("Welcome to the Power Plant Analyzer program. "
                + "Please choose from the following options:");
        System.out.println("1. Upload Data");
        System.out.println("2. View Data");
        System.out.println("3. Create Statistics");
        System.out.println("4. Print Statistics");
        System.out.println("5. Print Month");
        System.out.println("6. Exit the program\n");

        return k.nextInt();
    }
    /**
     * Option 2 of project
     * @param data ArrayList with multi. data types
     */
    public static void printData(ArrayList<Entry> data){
        for (Entry entry : data) {
        System.out.println("Date: " + entry.getMonth() + " " + entry.getDay() + ", " +
                entry.getYear()+ "  Output: " + entry.getPowerO());
    }       
    }
    /**
     * Option 3 of project
     * @param data floats of power output
     * @throws IOException for output file
     */
    public static void createStatsFile(ArrayList<Entry> data) throws IOException{
        PrintWriter pw = new PrintWriter("stats.txt");
        // sort from lowest to highest
        ArrayList<Float> powerOutputs = new ArrayList<>();
        for (Entry entry : data) 
        {
            powerOutputs.add(entry.getPowerO());
        }
        Collections.sort(powerOutputs);
        // day w highest entry
        Entry maxEntry = data.get(0); 
        float maxPowerO = maxEntry.getPowerO(); 

        for (Entry entry : data) {
            if (entry.getPowerO() > maxPowerO) {
                maxPowerO = entry.getPowerO(); 
                maxEntry = entry;
        }
        }
        int maxDay = maxEntry.getDay();
        
        
        // total by month
        ArrayList<String> months = new ArrayList<>(12);
        float[] totalByMonth = new float[12]; 
        
        months.add("January");// 0
        months.add("February");// 1
        months.add("March");// 2
        months.add("April");// 3
        months.add("May");// 4
        months.add("June");// 5
        months.add("July");// 6
        months.add("August"); // 7
        months.add("September");// 8
        months.add("October");// 9
        months.add("November");// 10
        months.add("December");// 11

        for (Entry entry : data) 
        {
            int monthIndex = months.indexOf(entry.getMonth()); 
            totalByMonth[monthIndex] += entry.getPowerO(); 
        }

        //average for all powerOutput data
        int size = data.size();
        float total = 0;
        for(Entry e : data)
        {
            total += e.getPowerO();
        }
        float average = total/size;
        
        pw.println("Statistics File:");
        pw.println("A: Power output sorted from lowest to highest:");
        for (float a : powerOutputs)
        {
            pw.println(a);
        }
        pw.println("\nB: The day with the highest output was \n" + maxEntry.getMonth() +
                " " + maxEntry.getDay() + ", 2018, and the output was " + maxEntry.getPowerO() + ".");
        pw.printf("\n");
        pw.println("C: Monthly power totals.");
        for (int i = 0; i < totalByMonth.length; i++) 
        {
        if (totalByMonth[i] > 0) {
            pw.printf(months.get(i) + "'s power output total is %.1f.\n", totalByMonth[i]);
        }
        }
        pw.printf("\nD: The average power output for all data is %.2f.", average);
        pw.close();
    }
    /**
     * Option 4 of project
     * prints stats.text file
     * @throws IOException for reading stats.text file
     */
    public static void printStatistics() throws IOException
    {
    File f = new File("stats.txt");
    Scanner input = new Scanner(f);
    while (input.hasNext()) 
     {
           System.out.println(input.nextLine());   
     }

}
    
    
    /**
     * @param args the command line arguments
     * @throws IOException
     */
    public static void main(String[] args) throws IOException
    {
        PrintWriter pw1 = new PrintWriter("input.txt");
        pw1.println("January 10 2018 236.9");
        pw1.println("January 11 2018 267.6");
        pw1.println("January 12 2018 278.1");
        pw1.println("January 13 2018 246.9");
        pw1.println("February 2 2018 199.7");
        pw1.println("February 3 2018 134.6");
        pw1.println("February 4 2018 200.8");
        pw1.println("February 5 2018 198.2");
        pw1.println("March 10 2018 168.3");
        pw1.println("March 11 2018 179.4");
        pw1.close();
        PrintWriter pw2 = new PrintWriter("input2.txt");
        pw2.println("January 10 2018 236.9");
        pw2.println("March 11 2018 267.6");
        pw2.println("May 12 2018 278.1");
        pw2.println("July 13 2018 246.9");
        pw2.println("July 14 2018 262.3");
        pw2.println("November 15 2018 288.6");
        pw2.println("November 2 2018 199.7");
        pw2.close();
        
        ArrayList<Entry> data = new ArrayList<>();
        Scanner k = new Scanner(System.in);

        int option = printMenu(k);
        k.nextLine();
        while(option != 6){
           if (option == 1)
           {
               System.out.println("Please enter the file name.");
               String filename = k.nextLine();
               data = upload(filename);
               option = printMenu(k);
           }
           if (option == 2) 
           {
           printData(data);
           option = printMenu(k);
           }  
          
           if (option == 3)
           {
               createStatsFile(data);
               option = printMenu(k);
           }
           if (option == 4)
           {
               printStatistics();
               option = printMenu(k);
           }

        }
    
    }
}    
