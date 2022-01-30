package edu.ithaca.dturnbull.bank;


public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
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
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * If amount is negative, throw exception "not enough money"
     * If amount is larger than balance, throw exception "not enough money"
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
        String[] emailArray = email.split("@");
        
        //Domain
        if (email.indexOf('@') == -1 || email.indexOf(".") == -1 || !emailArray[emailArray.length-1].contains(".") || emailArray[0].indexOf(".") == emailArray[0].length()-1 || emailArray[0].indexOf("_") == emailArray[0].length()-1 || emailArray[0].indexOf("-") == emailArray[0].length()-1 || email.contains("..")){
            return false;
        }

        if(email.startsWith(".") || email.contains("#")){
            return false;
        }

        if(emailArray.length>2 || emailArray[1].indexOf(".") == emailArray[1].length()-2){
            return false;
        }
        
        if(emailArray[1].contains("_")){
            return false;
        }

        
        
        else {
            return true;
        }
    }
}
