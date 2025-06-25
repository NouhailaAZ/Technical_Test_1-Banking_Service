import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class AccountTest {
    public static void main(String[] args) {
        System.out.println("=== Test 1: Banking Service ===\n");
        
        // Test du scénario de l'acceptance test
        testAcceptanceScenario();
                
        // Tests d'exceptions
        testExceptions();
    }
    
    private static void testAcceptanceScenario() {
        System.out.println("- Acceptance test -\n");
        
        Account account = new Account();
        
        account.deposit(100);
        account.deposit(2000);
        account.withdraw(2100);
        
        account.printStatement();
    }
    
    private static void testExceptions() {
        System.out.println("\n- Tests d'exceptions -\n");

        Account account = new Account();
        
        // Test de dépôt avec montant négatif
        try {
            account.deposit(-100);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative deposit correctly rejected:" + e.getMessage());
        }
        
        // Test de retrait avec montant négatif
        try {
            account.withdraw(-50);
        } catch (IllegalArgumentException e) {
            System.out.println("Negative withdrawal correctly rejected: " + e.getMessage());
        }
        
        // Test de retrait avec fonds insuffisants
        try {
            account.withdraw(100);
        } catch (IllegalArgumentException e) {
            System.out.println( e.getMessage());
        }
        
        // Test de dépôt zéro
        try {
            account.deposit(0);
        } catch (IllegalArgumentException e) {
            System.out.println("Filing of zero correctly rejected: " + e.getMessage());
        }

        account.printStatement();

    }

}