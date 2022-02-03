package edu.ithaca.dturnbull.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     * * @throws IllegalArgumentException if starting balance is negative or has more than 2 decimals in balance
     */
    public BankAccount(String email, double startingBalance) throws InsufficientFundsException {

        if (isAmountValid(startingBalance) == false) {
            if (startingBalance <0){
                throw new InsufficientFundsException("Starting balance is negative");
            }
            else{
                throw new IllegalArgumentException("You can't deposit a negative amount or amount with more than 2 decimal places");
            }
        }
        else if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @throws IllegalArgumentException if withdraw amount is invalid
     * @throws InsufficientFundsException if there is not enough money in the account
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * If amount is negative, throw exception "not enough money"
     * If amount is larger than balance, throw exception "not enough money"
     */
    //withdraw function passes tests
    public void withdraw (double amount) throws InsufficientFundsException{

        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("You can't withdraw a negative amount or amount with more than 2 decimal places");
        }
        else if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    /**
     * @return false if string does not contain @ or . or if string starts with . 
     * @return false if string contains . # or ..
     * @return false if domain does not have at least 2 characters or if there are any invalid characters in domain
     * 
     * */
    public static boolean isEmailValid(String email){
        String[] emailArray = email.split("@");
        
        if (email.indexOf('@') == -1 || email.indexOf(".") == -1) {       
            return false;
        }

        if(email.startsWith(".") || email.contains("#") || email.contains("..")){
            return false;
        }

        if(emailArray.length>2 || emailArray[1].indexOf(".") == emailArray[1].length()-2 || emailArray[0].indexOf(".") == emailArray[0].length()-1 || emailArray[0].indexOf("_") == emailArray[0].length()-1 || emailArray[0].indexOf("-") == emailArray[0].length()-1 ){
            return false;
        }
        
        if(emailArray[1].contains("_") || !emailArray[emailArray.length-1].contains(".")) {
            return false;
        }
        
        else {
            return true;
        }
    }

    /**
     * @post if amount is negative it is invalid
     * if there are more than 2 decimals on any double it is invalid
    */
    public static boolean isAmountValid(double amount){

        String amountString = Double.toString(amount);
        String[] splitStr = amountString.split("\\.");
    
        if (amount < 0){
           return false;
      }

        if (splitStr[1].length() > 2) {
            return false;
        }
         else {
            return true;
        }
    }

    /**
     * @throws IllegalArguementException if amount has more than 2 decimals
     * otherwise increments balance by the deposited amount
     * */
    public void deposit(double amount) throws InsufficientFundsException{

        if (isAmountValid(amount) == false){
            throw new IllegalArgumentException("Amount is negative or has two decimals, neither can be deposited");
        }
        else {
            balance += amount;
        }
    }

    /**
     * @throws InsufficientFundsException if the withdraw amount is greater than the intiial account balance
     * @throws IllegalArgumentException if there are more than 2 decimal places on the amount 
     * otherwise decreases initial account by transfer amount and incrememnts transfer account by amount
     */
    
    public void transfer(double amount, BankAccount transferAccount) throws InsufficientFundsException{
        if (isAmountValid(amount) == true && amount < balance){
            balance -= amount;
            transferAccount.balance += amount;
        }
        else if (amount > balance){
            throw new InsufficientFundsException("Not enough money!");
        }
        else{
            throw new IllegalArgumentException("You can't deposit an amount with more than 2 decimal places");
            }
        }
    }





