/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contractmanager;


import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
/**
 *
 * @author callu
 */
public class NewContract  {

    public static void NewEntry() throws IOException 
    {
        PrintWriter output = null;
        PrintWriter output2 = null;
        Scanner userInput = new Scanner(System.in);  
        File contracts = new File("contracts.txt");
        File monthlyCharges = new File("monthlyCharge.txt");
        int packageInput;
        String Package = null;
        String bundle = null;
        String clientName;
        String reference;
        String period = null;
        int bundleInput;
        int contractPeriod;
        String intCalls = null;
        String discount = null;
        String type = null;
        double monthlyCharge = 0;
        
        
       try{ 
            DecimalFormat df = new DecimalFormat("£#,##0.00");
            BufferedWriter fw = new BufferedWriter(new FileWriter("contracts.txt", true));
            BufferedWriter mc = new BufferedWriter(new FileWriter("monthlyCharge.txt", true));
            output2 = new PrintWriter(mc);
            output = new PrintWriter(fw);
            String out =  new SimpleDateFormat("dd-MMM-yyyy").format(new Date());
            output.print(out);
            fw.write(" ");
            System.out.println("Please enter the client's name:");
            clientName = userInput.nextLine();
            while(!clientName.matches("[a-z A-Z]+")){
                System.out.println("Invalid input, please try again!");
                clientName = userInput.nextLine();
            }
            output.print(clientName);
            fw.write(" ");
            System.out.println("Please enter client's package (1 = Small, 2 = Medium and 3 = Large)");
            while(!userInput.hasNextInt()){
                System.out.println("Invalid input, please try again!");
                userInput.next();
            }
            packageInput = userInput.nextInt();
            if(packageInput == 1){
                Package = "Small (300)";
                output.print(Package);
            }
            else if(packageInput == 2){                
                Package = "Medium (600)";
                output.print(Package);
            }
            else if(packageInput == 3){                
                Package = "Large (1200)";
                output.print(Package);
            }
            fw.write(" ");
            System.out.println("Please enter client's data bundle (1 = Low, 2 = Medium, 3 = High and 4 = Unlimited)");
            while(!userInput.hasNextInt()){
                System.out.println("Invalid input, please try again!");
                userInput.next();
            }
            bundleInput = userInput.nextInt();
            userInput.nextLine();
            if(bundleInput == 1){
                bundle = "Low (1GB)";
                output.print(bundle);
            }
            else if(bundleInput == 2){
                bundle = "Medium (4GB)";
                output.print(bundle);
            }
            else if(bundleInput == 3){
                bundle = "High (8GB)";
                output.print(bundle);
            }
            else if(bundleInput == 4){
                bundle = "Unlimited";
                output.print(bundle);
            }
            fw.write(" ");
            System.out.println("Please enter the client's contract period in months (1, 12, 18 or 24)");
            while(!userInput.hasNextInt()){
                System.out.println("Invalid input, please try again!");
                userInput.next();
            }
            contractPeriod = userInput.nextInt();
            userInput.nextLine();
            if(contractPeriod == 1){
                period = "1 Month";
                output.print(period);
            }
            else if(contractPeriod == 12){
                period = "12 Months";
                output.print(period);
            }
            else if(contractPeriod == 18){
                period = "18 Months";
                output.print(period);
            }
            else if(contractPeriod == 24){
                period = "24 Months";
                output.print(period);
            }
            fw.write(" ");
            System.out.println("Please enter the client's access to international calls (Yes or No)");
            intCalls = userInput.nextLine();
            while(!intCalls.matches("[a-zA-Z]+")){
                System.out.println("Invalid input, please try again!");
                intCalls = userInput.nextLine();
            }
            output.print(intCalls);
            fw.write(" ");
            System.out.println("Please enter client's reference number");
            System.out.println("Reference format is as follows: Two letters followed by three digits and a letter (Max 6 characters)");
            System.out.println("The last letter can only be B for Business Account or N for Non-Business Account");
            reference = userInput.nextLine();
            output.print(reference);
            if (reference.endsWith("B")){
                type = "Business";                
                if (contractPeriod == 12 || contractPeriod == 18 || contractPeriod == 24){
                discount = "10%";
                monthlyCharge = 7.65;
                }   
            }
            if(reference.endsWith("N")){
                type = "Non-Business";
                if (contractPeriod == 12 || contractPeriod == 18){
                    discount = "5%";
                    monthlyCharge = 8.08;
                if(contractPeriod == 24){
                    discount = "10%";
                    monthlyCharge = 7.65;
                    }
                if(contractPeriod == 1){
                    discount = "0%";
                    monthlyCharge = 0.00;
                }
            }
            }
            if (packageInput >=3){
                mc.write(" ");
                output2.print(monthlyCharge);
            }
            fw.newLine();
            output.close();
            output2.close();
            System.out.println("+------------------------------------------------------------------------+");
            System.out.println("|                                                                        |");
            System.out.println("| Customer:" + clientName +"                                             |");
            System.out.println("|      Ref:" + reference +"         " + "Date:" + out +"                 |");
            System.out.println("|  Package:" + Package +"           " + "Data:" + bundle + "             |");
            System.out.println("|   Period:" + period +"            " + "Type:" + type + "                            |");
            System.out.println("|                                                                        |");
            System.out.println("| Discount:" + discount +"           " + "Intl. Calls:" + intCalls +"                      |");
            System.out.println("|                                                                        |");
            System.out.println("|     Discounted Monthly Charge: "+ df.format(monthlyCharge) + "                             |");
            System.out.println("+------------------------------------------------------------------------+");
       }catch (FileNotFoundException e){
            System.out.println("There was a problem creating the file! Closing program");
            System.exit(0);
           }
       }
    }
