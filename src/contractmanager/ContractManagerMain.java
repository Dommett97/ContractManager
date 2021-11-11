/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contractmanager;




import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author callu
 */
public class ContractManagerMain {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
                       
        Scanner keybInput = new Scanner(System.in);
        
        File contracts = new File("contracts.txt"); 
        File monthlyCharges = new File("monthlyCharge.txt");
        File archive = new File("Archive.txt");
        String[] summary = null;
        FileReader fr = new FileReader(contracts);
        BufferedReader br = new BufferedReader(fr);
        FileReader fr2 = new FileReader(monthlyCharges);
        BufferedReader br2 = new BufferedReader(fr2);
        FileReader fr3 = new FileReader(archive);
        BufferedReader br3 = new BufferedReader(fr3);
        Pattern p = Pattern.compile("-?\\d+");
        int column = 13;
        List<Integer> bundles = Files.lines(Paths.get("Archive.txt"))
                    .map(p::matcher)
                    .map(m -> m.find(column)  ? Integer.parseInt(m.group()) : null)
                    .collect(Collectors.toList());
        int occurences = Collections.frequency((bundles), 4); 
        int occurences2 = Collections.frequency((bundles), 3);
        DecimalFormat df = new DecimalFormat("#0");     
        int janCount = 0;
        int febCount = 0;
        int marCount = 0;
        int aprCount = 0;
        int mayCount = 0;
        int junCount = 0;
        int julCount = 0;
        int augCount = 0;
        int sepCount = 0;
        int octCount = 0;
        int novCount = 0;
        int decCount = 0;
        int sum = 0;  
        int bundleCount = 0;
        int numOfContracts = 0;
        int numOfContracts2 = 0;
        int bundleCount2 = occurences + occurences2;

        boolean quit = false;
        do {
            System.out.println("Option 1 - Enter new Contract");
            System.out.println("Option 2 - Display Summary of Contracts");
            System.out.println("Option 3 - Display Summary of Contracts for Selected Month");
            System.out.println("Option 4 - Find and display Contract");
            System.out.println("Option 5 - Exit application");
            int userOption = readInteger(keybInput, "Please select an option: ");
            switch(userOption){
                case 1:
                    option1(numOfContracts);
                    break;
                case 2:
                    option2(bundleCount, numOfContracts, bundleCount2, numOfContracts2, br, summary, br3, sum, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount);
                    break;
                case 3:
                    option3(br, br3, summary, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount );
                    break;
                case 4:
                    option4(br, br3);
                    break;
                case 5:
                    System.out.println("You have succesfully exited the program!");
                    System.exit(0);
            }
        }while(!quit);
    }

    public static void option1(int numOfContracts) throws IOException{
        System.out.println("Please enter details for new contract: ");
        NewContract newContract = new NewContract();
        NewContract.NewEntry();
    }
    
    public static void option2(int bundleCount, int numOfContracts, int bundleCount2, int numOfContracts2, BufferedReader br, String[] summary, BufferedReader br3, int sum, int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount) throws IOException{
        Scanner selection = new Scanner(System.in);
        System.out.println("You can choose to view the Main contracts or Archived Contracts");
        System.out.println("(Type 'Main' or 'Archive')");
        String choice = selection.nextLine();
        while(!choice.equals("Main")
            && !choice.equals("Archive"))
        {
            System.out.println("Invalid entry please try again");
            selection.nextLine();
        }
        if(choice.equals("Main")){
            readContract(br, summary, bundleCount, numOfContracts, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount);
        }
        else if(choice.equals("Archive")){
            readArchive(br3, sum, numOfContracts2, bundleCount2, summary, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount);
        }
    }
    
    public static void option3(BufferedReader br, BufferedReader br3, String[] summary,int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount) throws IOException{
        Scanner selection = new Scanner(System.in);
        System.out.println("You can choose to view the Main contracts or Archived Contracts");
        System.out.println("(Type 'Main' or 'Archive')");
        String choice = selection.nextLine();
        while(!choice.equals("Main")
            && !choice.equals("Archive"))
        {
            System.out.println("Invalid entry please try again");
            selection.nextLine();
        }
        if(choice.equals("Main")){
            summaryContract(br, summary, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount);
        }
        else if(choice.equals("Archive")){
            summaryArchive(br3, summary, janCount, febCount, marCount, aprCount, mayCount, junCount, julCount, augCount, sepCount, octCount, novCount, decCount);
        }
    }
    
    public static void option4(BufferedReader br, BufferedReader br3) throws IOException{
        Scanner selection = new Scanner(System.in);
        System.out.println("You can choose to view the Main contracts or Archived Contracts");
        System.out.println("(Type 'Main' or 'Archive')");
        String choice = selection.nextLine();
        while(!choice.equals("Main")
            && !choice.equals("Archive"))
        {
            System.out.println("Invalid entry please try again");
            selection.nextLine();
        }
        if(choice.equals("Main")){
            findContract(br);
        }
        else if(choice.equals("Archive")){
            findArchive(br3);
        }
    }

    private static int readInteger(Scanner keybInput, String prompt){
        System.out.println(prompt);
        while(true) {
            try {
                int userInput = keybInput.nextInt();
                return userInput;  
            }
            catch(java.util.InputMismatchException error) {
                System.err.println("Please enter a valid number");
                keybInput.next();
            }
        }
    }
    
    public static void readContract(BufferedReader br, String[] summary, int bundleCount, int numOfContracts, int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount) throws IOException{
        Scanner scan = new Scanner(new File("monthlyCharge.txt"));
        int items = 0;
        double total = 0;
        String s;
        while(scan.hasNextDouble()){ 
            total += scan.nextDouble();
            
            items++;
        }
        double average = total/items;
        System.out.println("");
        
        while((s = br.readLine())!= null){
            summary = s.split(" ");
            for (String word : summary){
                if (word.equals("High")){
                    bundleCount++;
                }
                if (word.equals("Unlimited")){
                    bundleCount++;
                }
                if (word.equals("Yes")){
                    numOfContracts++;
                }
                if (word.equals("No")){
                    numOfContracts++;
                }
                if (word.contains("Jan")){
                    janCount++;
                }
                if (word.contains("Feb")){
                    febCount++;
                }
                if (word.contains("Mar")){
                    marCount++;
                }
                if (word.contains("Mar")){
                    aprCount++;
                }
                if (word.contains("May")){
                    mayCount++;
                }                
                if (word.contains("Jun")){
                    junCount++;
                }                
                if (word.contains("Jul")){
                    julCount++;
                }                
                if (word.contains("Aug")){
                    augCount++;
                }
                if (word.contains("Sep")){
                    sepCount++;
                }
                if (word.contains("Oct")){
                    octCount++;
                }
                if (word.contains("Nov")){
                    novCount++;
                }
                if (word.contains("Dec")){
                    decCount++;
                }
            }
        }
        System.out.println("Number of contracts: " + numOfContracts);
        System.out.println("Number of contracts with High or Unlimited Data Bundles: " + bundleCount);
        System.out.println("Average charge for large packages: " + average);
        System.out.println("Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");
        System.out.println(janCount + "  " + febCount + "   " + marCount + "   " + aprCount + "   " + mayCount + " " + junCount + " " + julCount + " " + augCount + " " + sepCount + " " + octCount + " " + novCount + " " + decCount);
    }
    
    public static void readArchive(BufferedReader br3, int sum,  int numOfContracts2, int bundleCount2, String[] summary, int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount) throws IOException{
        DecimalFormat df = new DecimalFormat("##,##.#"); 
        String line;
        String s;
        while((line = br3.readLine()) != null){
            numOfContracts2++; 
            String[] columns = line.split("\t");
            if(columns[1].equals("3")){
                int value = Integer.parseInt(columns[6]);
                sum = value + sum;
            }
            summary = line.split(" ");
            for (String word : summary){
                if (word.contains("Jan")){
                    janCount++;
                }
                if (word.contains("Feb")){
                    febCount++;
                }
                if (word.contains("Mar")){
                    marCount++;
                }
                if (word.contains("Mar")){
                    aprCount++;
                }
                if (word.contains("May")){
                    mayCount++;
                }                
                if (word.contains("Jun")){
                    junCount++;
                }                
                if (word.contains("Jul")){
                    julCount++;
                }                
                if (word.contains("Aug")){
                    augCount++;
                }
                if (word.contains("Sep")){
                    sepCount++;
                }
                if (word.contains("Oct")){
                    octCount++;
                }
                if (word.contains("Nov")){
                    novCount++;
                }
                if (word.contains("Dec")){
                    decCount++;
                }
            }
        }
        sum = sum / 49;
        System.out.println("Number of contracts: " + numOfContracts2);
        System.out.println("Number of contracts with High or Unlimited Data Bundles: " + bundleCount2);
        System.out.println("Average charge for large packages: " + df.format(sum));
        System.out.println("Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec");
        System.out.println(janCount + " " + febCount + " " + marCount + " " + aprCount + " " + mayCount + " " + junCount + " " + julCount + " " + augCount + " " + sepCount + " " + octCount + " " + novCount + " " + decCount);
        }
    
    public static void summaryContract(BufferedReader br, String[] summary,int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount )throws IOException{
        Scanner selection = new Scanner(System.in);
        String line;
        System.out.println("Please enter a month to display a summary of contracts:");
        System.out.println("Options include: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec");
        String choice = selection.nextLine();
        while(!choice.equals("Jan") && !choice.equals("Feb") && !choice.equals("Mar") && !choice.equals("Apr") && !choice.equals("May") && !choice.equals("Jun") && !choice.equals("Jul") && !choice.equals("Aug") && !choice.equals("Sep") && !choice.equals("Oct") && !choice.equals("Nov") && !choice.equals("Dec"))
        {
            System.out.println("Invalid entry please try again");
            selection.nextLine();
        }
        while((line = br.readLine()) != null){
            summary = line.split(" ");
            for (String word : summary){
                if(word.contains("Jan")){
                janCount++;
            }
            if(word.contains("Feb")){
                febCount++;
            }
            if(word.contains("Mar")){
                marCount++;
            }
            if(word.contains("Apr")){
                aprCount++;
            }
            if(word.contains("May")){
                mayCount++;
            }
            if(word.contains("Jun")){
                junCount++;
            }
            if(word.contains("Jul")){
                julCount++;
            }
            if(word.contains("Aug")){
                augCount++;
            }
            if(word.contains("Sep")){
                sepCount++;
            }
            if(word.contains("Oct")){
                octCount++;
            }
            if(word.contains("Nov")){
                novCount++;
            }
            if(word.contains("Dec")){
                decCount++;
            }
            }
        }
        if(choice.equals("Jan")){
                System.out.println("Contracts in January: " + janCount);
            }
            if(choice.equals("Feb")){
                System.out.println("Contracts in February: " + febCount);
            }
            if(choice.equals("Mar")){
                System.out.println("Contracts in March: " + marCount);
            }
            if(choice.equals("Apr")){
                System.out.println("Contracts in April: " + aprCount);
            }
            if(choice.equals("May")){
                System.out.println("Contracts in May: " + mayCount);
            }
            if(choice.equals("Jun")){
                System.out.println("Contracts in June: " + junCount);
            }
            if(choice.equals("Jul")){
                System.out.println("Contracts in July: " + julCount);
            }
            if(choice.equals("Aug")){
                System.out.println("Contracts in August: " + augCount);
            }
            if(choice.equals("Sep")){
                System.out.println("Contracts in September: " + sepCount);
            }
            if(choice.equals("Oct")){
                System.out.println("Contracts in October: " + octCount);
            }
            if(choice.equals("Nov")){
                System.out.println("Contracts in November: " + novCount);
            }
            if(choice.equals("Dec")){
                System.out.println("Contracts in December: " + decCount);
            }
        
        
    }

    

    
    
    
    public static void summaryArchive(BufferedReader br3,String[] summary,int janCount, int febCount, int marCount, int aprCount, int mayCount, int junCount, int julCount, int augCount, int sepCount, int octCount, int novCount, int decCount) throws IOException{
        Scanner selection = new Scanner(System.in);
        String line;
        System.out.println("Please enter a month to display a summary of contracts:");
        System.out.println("Options include: Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec");
        String choice = selection.nextLine();
        while(!choice.equals("Jan") && !choice.equals("Feb") && !choice.equals("Mar") && !choice.equals("Apr") && !choice.equals("May") && !choice.equals("Jun") && !choice.equals("Jul") && !choice.equals("Aug") && !choice.equals("Sep") && !choice.equals("Oct") && !choice.equals("Nov") && !choice.equals("Dec"))
        {
            System.out.println("Invalid entry please try again");
            selection.nextLine();
        }
        while((line = br3.readLine()) != null){
            summary = line.split(" ");
            for (String word : summary){
                if(word.contains("Jan")){
                janCount++;
            }
            if(word.contains("Feb")){
                febCount++;
            }
            if(word.contains("Mar")){
                marCount++;
            }
            if(word.contains("Apr")){
                aprCount++;
            }
            if(word.contains("May")){
                mayCount++;
            }
            if(word.contains("Jun")){
                junCount++;
            }
            if(word.contains("Jul")){
                julCount++;
            }
            if(word.contains("Aug")){
                augCount++;
            }
            if(word.contains("Sep")){
                sepCount++;
            }
            if(word.contains("Oct")){
                octCount++;
            }
            if(word.contains("Nov")){
                novCount++;
            }
            if(word.contains("Dec")){
                decCount++;
            }
            }
        }
        if(choice.equals("Jan")){
                System.out.println("Contracts in January: " + janCount);
            }
            if(choice.equals("Feb")){
                System.out.println("Contracts in February: " + febCount);
            }
            if(choice.equals("Mar")){
                System.out.println("Contracts in March: " + marCount);
            }
            if(choice.equals("Apr")){
                System.out.println("Contracts in April: " + aprCount);
            }
            if(choice.equals("May")){
                System.out.println("Contracts in May: " + mayCount);
            }
            if(choice.equals("Jun")){
                System.out.println("Contracts in June: " + junCount);
            }
            if(choice.equals("Jul")){
                System.out.println("Contracts in July: " + julCount);
            }
            if(choice.equals("Aug")){
                System.out.println("Contracts in August: " + augCount);
            }
            if(choice.equals("Sep")){
                System.out.println("Contracts in September: " + sepCount);
            }
            if(choice.equals("Oct")){
                System.out.println("Contracts in October: " + octCount);
            }
            if(choice.equals("Nov")){
                System.out.println("Contracts in November: " + novCount);
            }
            if(choice.equals("Dec")){
                System.out.println("Contracts in December: " + decCount);
            }
    }
    
    public static void findContract(BufferedReader br) throws IOException{
        System.out.println("Please enter the appropiate credentials for the contract:");
        Scanner selection = new Scanner(System.in);
        String line;
        String choice = selection.nextLine();
        while((line = br.readLine()) != null){
            if(line.contains(choice))
                System.out.println(line);
        }
    }
    
    public static void findArchive(BufferedReader br3) throws IOException{
        System.out.println("Please enter the appropiate credentials for the contract:");
        Scanner selection = new Scanner(System.in);
        String line;
        String choice = selection.nextLine();
        while((line = br3.readLine()) != null){
            if(line.contains(choice))
                System.out.println(line);
        }
    }
    
}
    




    
 




        
        

  


    


                
          
        
            
      
