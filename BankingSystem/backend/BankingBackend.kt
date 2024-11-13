package banking

data class Account(val id: String, var balance: Double = 0.0)

class BankingSystem {
    private val accounts = mutableMapOf<String, Account>()

    // Check if an account exists by ID
    fun accountExists(id: String): Boolean {
        return accounts.containsKey(id)
    }

    // Create a new account with an initial balance of zero
    fun createAccount(id: String): Account {
        if (accounts.containsKey(id)) {
            throw IllegalArgumentException("Account already exists.")
        }
        val account = Account(id)
        accounts[id] = account
        println("Account created with ID: $id")
        return account
    }

    // Retrieve the account balance
    fun getBalance(id: String): Double {
        val account = accounts[id] ?: throw IllegalArgumentException("Account not found.")
        return account.balance
    }

    // Deposit funds into the account
    fun deposit(id: String, amount: Double) {
        val account = accounts[id] ?: throw IllegalArgumentException("Account not found.")
        if (amount <= 0) {
            throw IllegalArgumentException("Deposit amount must be positive.")
        }
        account.balance += amount
        println("Deposited $amount into account ID: $id")
    }

    // Withdraw funds from the account if balance allows
    fun withdraw(id: String, amount: Double) {
        val account = accounts[id] ?: throw IllegalArgumentException("Account not found.")
        if (amount <= 0) {
            throw IllegalArgumentException("Withdrawal amount must be positive.")
        }
        if (amount > account.balance) {
            throw IllegalArgumentException("Insufficient balance.")
        }
        account.balance -= amount
        println("Withdrew $amount from account ID: $id")
    }

    // Reset the session (clear the state for demonstration purposes)
    fun resetSession() {
        println("Session reset. Logging out...")
    }
}
