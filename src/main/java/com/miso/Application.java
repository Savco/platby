package com.miso;

import com.miso.dao.BankAccountDAO;
import com.miso.dao.PlayerDAO;
import com.miso.dao.TransactionDAO;
import com.miso.entity.BankAccount;
import com.miso.entity.Player;
import com.miso.entity.Transaction;
import com.miso.enums.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Component
    class DemoCommandLineRunner implements CommandLineRunner {

        @Autowired
        private PlayerDAO playerDAO;

        @Autowired
        private BankAccountDAO bankAccountDAO;

        @Autowired
        private TransactionDAO transactionDAO;

        @Override
        public void run(String... args) throws Exception {

//            Player p1 = new Player("Sav", "Miso", 10d, Category.U19);
//            Player p2 = new Player("Sav", "Riso", 20d, Category.U19);
//
//            Set<Player> pls = new HashSet();
//            pls.add(p1);
//            pls.add(p2);
//
//            Transaction t = new Transaction(20d, new Date());
//            Set<Transaction> ts = new HashSet();
//
//            BankAccount b = new BankAccount("123", pls, ts);
//
//            playerDAO.create(p1);
//            playerDAO.create(p2);
//
//            transactionDAO.create(t);
//
//            bankAccountDAO.create(b);
            System.out.println("Hello there!");
        }
    }
}
