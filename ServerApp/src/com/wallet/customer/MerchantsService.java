package com.wallet.customer;

import java.util.HashMap;
import java.util.Random;

public class MerchantsService implements MerchantsAPI {

    public static HashMap<Integer, Merchant> merchants = new HashMap<Integer, Merchant>();

    public int generateID() {
        Random randomID = new Random();
        return randomID.nextInt(Integer.MAX_VALUE);
    }

    public String getMerchantName(int id) {
        if (merchants.containsKey(id)) {
            return merchants.get(id).getName();
        } else {
            return "The merchant was not found";
        }
    }

    public String createMerchant(String name, String email,
            String country) {
        int id = generateID(); 
        Merchant createdNewClient = new Merchant(id, name, email, country);
        merchants.put(id, createdNewClient);
        return "It was created new merchant with ID: " + id + "\n" +
                "The name of the merchant is: " + merchants.get(id).getName() + "\n" +
                "The email of the merchant is: " + merchants.get(id).getEmail() + "\n" +
                "The date of registration is: " + merchants.get(id).getDateOfRefistration() + "\n" +
                "The country is: " + merchants.get(id).getCountry();
    }

    public String deleteMerchant(int id) {
        if (merchants.containsKey(id) && merchants.get(id).getBalance() == 0) {
            merchants.remove(id);
            return "The merchant with id " + id + "was deleted.";
        } else {
            return "The merchant can not be deleted, because his balance is not 0";
        }
    }

    public String renameMerchant(int id, String name) {

        if (merchants.containsKey(id)) {
            merchants.get(id).setName(name);
            return "The name of merchant with id " + id + "was changed.";
        } else {
            return "The merchant was not found";
        }
    }

    public String paymentsTransaction(int id, double balance) {
        if (balance > 0 || (balance < 0 && !(merchants.get(id).getBalance() + balance < 0))) {
            int balanceAfterTransaction = (int) (merchants.get(id).getBalance() + balance);
            merchants.get(id).setBalance(balanceAfterTransaction);
            return "The balance is:" + merchants.get(id).getBalance();
        }
        return "The transaction can not be completed. Try again, please. ";
    }
    
    @Override
    public String toString() {
        
        return super.toString();
    }

}
