import banking.BankingSystem
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

class BankingSystemTest {

    private val bankingSystem = BankingSystem()

    // Test for account existence check
    @Test
    fun testAccountExists() {
        val accountId = "user123"
        assertFalse(bankingSystem.accountExists(accountId), "Account should not exist initially.")
        bankingSystem.createAccount(accountId)
        assertTrue(bankingSystem.accountExists(accountId), "Account should exist after creation.")
    }

    // Test for account creation with initial balance zero
    @Test
    fun testAccountCreation() {
        val accountId = "user456"
        val account = bankingSystem.createAccount(accountId)
        assertEquals(0.0, account.balance, "New account should have a balance of zero.")
    }

    // Test for duplicate account creation failure
    @Test
    fun testDuplicateAccountCreation() {
        val accountId = "user789"
        bankingSystem.createAccount(accountId)
        assertThrows(IllegalArgumentException::class.java) {
            bankingSystem.createAccount(accountId)
        }
    }

    // Test for retrieving the initial balance of zero
    @Test
    fun testInitialBalance() {
        val accountId = "user101"
        bankingSystem.createAccount(accountId)
        assertEquals(0.0, bankingSystem.getBalance(accountId), "Initial balance should be zero.")
    }

    // Test for depositing money
    @Test
    fun testDeposit() {
        val accountId = "userDeposit"
        bankingSystem.createAccount(accountId)
        bankingSystem.deposit(accountId, 100.0)
        assertEquals(100.0, bankingSystem.getBalance(accountId), "Balance should be updated after deposit.")
    }

    // Test for invalid deposit (negative amount)
    @Test
    fun testInvalidDeposit() {
        val accountId = "userNegativeDeposit"
        bankingSystem.createAccount(accountId)
        assertThrows(IllegalArgumentException::class.java) {
            bankingSystem.deposit(accountId, -50.0)
        }
    }

    // Test for withdrawing money within balance
    @Test
    fun testWithdrawWithinBalance() {
        val accountId = "userWithdraw"
        bankingSystem.createAccount(accountId)
        bankingSystem.deposit(accountId, 100.0)
        bankingSystem.withdraw(accountId, 50.0)
        assertEquals(50.0, bankingSystem.getBalance(accountId), "Balance should decrease after withdrawal within limits.")
    }

    // Test for withdrawal exceeding balance (should fail)
    @Test
    fun testWithdrawExceedingBalance() {
        val accountId = "userOverdraw"
        bankingSystem.createAccount(accountId)
        bankingSystem.deposit(accountId, 50.0)
        assertThrows(IllegalArgumentException::class.java) {
            bankingSystem.withdraw(accountId, 100.0)
        }
    }

    // Test for session reset (simulating logout)
    @Test
    fun testResetSession() {
        bankingSystem.resetSession()
        // Since `resetSession` has no effect in our backend logic, there's no state change to verify.
        // But in a real application, you would check if session variables or tokens are reset.
        println("Session reset simulated successfully.")
    }
}
