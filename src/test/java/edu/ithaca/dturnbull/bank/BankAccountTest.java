package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertFalse( BankAccount.isEmailValid(""));

        //These are my tests you need to make pass in MY repository -Laci
        assertFalse(BankAccount.isEmailValid("a@bdotcom")); //No . in email --- This is an invalid partition, this is testing a boundary case for the domain --.com
        assertFalse(BankAccount.isEmailValid("a.b@com"));  // the . came before the @ --- This is an invalid partition,  this is testing a boundary case and an invalid equivalence test
        
        //Prefix side  
        assertFalse(BankAccount.isEmailValid("Laci.@gmail.com")); // Tests for a period immediately before the @ symbol
        assertFalse(BankAccount.isEmailValid("Laci_@gmail.com")); // Tests for an underscore immediately before the @ symbol 
        assertFalse(BankAccount.isEmailValid("Laci-@gmail.com")); // Tests for a dash immediately before the @ symbol (which is required for a valid email)
        assertFalse(BankAccount.isEmailValid("Laci..Y@gmail.com")); // Tests for two periods one after the other
        assertFalse(BankAccount.isEmailValid("Laci__Y@gmail.com")); // Tests for two underscores one after the other
        assertFalse(BankAccount.isEmailValid("Laci--Y@gmail.com")); // Tests for two dashes one after the other 
        assertFalse(BankAccount.isEmailValid(".Laci@gmail.com")); // Tests for period at the beginning of the email

        //Domain side 
        assertFalse(BankAccount.isEmailValid("Laci*@gmail.g")); // The last portion of the domain must be at least two characters
        assertFalse(BankAccount.isEmailValid("Laci*@gmail.c_om")); // The last portion of the domain cannot contain an underscore
        assertFalse(BankAccount.isEmailValid("Laci*@gmail.c#om")); // The last portion of the domain cannot contain a #
        assertFalse(BankAccount.isEmailValid("Laci@gmail")); // Email must contain last portion of the domain (.com, .ord, .edu, .cc)
         

        //Middle 
        assertFalse(BankAccount.isEmailValid("Laci#@gmail.com")); // # is an invalid character
        assertFalse(BankAccount.isEmailValid("Laci*@gmail.com")); // * is an invalid character
        assertFalse(BankAccount.isEmailValid("Laci!@gmail.com")); // ! is an invalid character
        assertFalse(BankAccount.isEmailValid("Laci&&@gmail.com")); // & is an invalid character
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}