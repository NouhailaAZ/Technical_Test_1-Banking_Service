import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public class Account implements AccountService {
    private int balance;
    private final ArrayList<Transaction> transactions;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public Account() {
        this.balance = 0;
        this.transactions = new ArrayList<>();
    }

    public int getBalance() {
        return balance;
    }
    
    public int getTransactionCount() {
        return transactions.size();
    }
    
    @Override
    public void deposit(int amount) {
        validateAmount(amount);
        balance += amount;
        transactions.add(new Transaction(LocalDate.now(), amount, balance));
        
    }
    
    @Override
    public void withdraw(int amount) {
        validateAmount(amount);
        if (amount > balance) {
            throw new IllegalArgumentException("Your balance is not sufficient to withdraw this amount " + amount + ", your balance: " + balance);
        }else{
            balance -= amount;
            transactions.add(new Transaction(LocalDate.now(), -amount, balance));
        }  
        
    }
    
    @Override
    public void printStatement() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        
        System.out.println("Date       || Amount || Balance");
        
        // Inverser l'ordre pour afficher les transactions les plus récentes en premier
        List<Transaction> reversedTransactions = new ArrayList<>(transactions);
        Collections.reverse(reversedTransactions);
        
        for (Transaction transaction : reversedTransactions) {
            System.out.printf("%s || %d   || %d%n", 
                transaction.getDate().format(DATE_FORMATTER), 
                transaction.getAmount(), 
                transaction.getBalance());
        }
    }
    
    // Méthode de validation de montant
    private boolean validateAmount(int amount) {
        if(amount <= 0){
        throw new IllegalArgumentException("Amount must be positive");
        }
        return false;
    }
}
