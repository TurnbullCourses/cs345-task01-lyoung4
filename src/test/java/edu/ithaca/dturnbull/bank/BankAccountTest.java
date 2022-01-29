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

        //These are my tests you need to make pass in MY repository -Laci
        assertFalse(BankAccount.isEmailValid("a@bdotcom")); //No . in email --- This is an invalid partition, this is testing a boundary case for the domain --.com
        assertFalse(BankAccount.isEmailValid("a.b@com"));  // the . came before the @ --- This is an invalid partition,  this is testing a boundary case and an invalid equivalence test
        
        //Prefix side
        assertTrue(BankAccount.isEmailValid("Laci4-Y@gmail.com"));  
        assertFalse(BankAccount.isEmailValid("Laci.@gmail.com")); //Prefix ends with .
        assertFalse(BankAccount.isEmailValid("Laci_@gmail.com")); //Prefix ends with _ 
        assertFalse(BankAccount.isEmailValid("Laci-@gmail.com")); //Prefix ends with - 
        assertFalse(BankAccount.isEmailValid("Laci..Y@gmail.com")); //Two periods one after the other
        assertFalse(BankAccount.isEmailValid(".Laci@gmail.com")); //Period at the beginning of the address
        assertFalse(BankAccount.isEmailValid("Laci#@gmail.com")); //# is an invalid character

        //Domain side 
        assertFalse(BankAccount.isEmailValid("Laci@gmail.g")); //Last part of domain must be at least 2 letters
        assertFalse(BankAccount.isEmailValid("Laci@gma_il.c_om")); //Last portion of the domain cannot contain an underscore
        assertFalse(BankAccount.isEmailValid("Laci@gmailcom")); //No period in last part of domain 
        assertFalse(BankAccount.isEmailValid("Laci@gmail")); //Email must contain last portion of the domain (.com, .ord, .edu, .cc)
         

        //Middle 
        assertTrue(BankAccount.isEmailValid("Laci#@gmail.com")); //# is an invalid character
        assertFalse( BankAccount.isEmailValid("")); //Empty string
        assertFalse( BankAccount.isEmailValid("L@ci@gmail.com")); //Two @ symbols


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