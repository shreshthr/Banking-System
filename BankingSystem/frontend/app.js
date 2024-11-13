let currentAccount = null; // Track the currently logged-in account
let accountBalances = {}; // Object to store balances by account ID

function login() {
    const accountId = document.getElementById("loginId").value;
    if (!accountId) {
        alert("Please enter an Account ID.");
        return;
    }

    if (accountBalances.hasOwnProperty(accountId)) {
        // Account exists, log in successfully
        currentAccount = accountId;
        document.getElementById("accountIdDisplay").innerText = `Account ID: ${currentAccount}`;
        showAccountSection();
        showResult(`Logged in successfully with ID: ${accountId}`, "success");
    } else {
        // Account does not exist, show error message
        showResult(`Account ID ${accountId} not found. Please create a new account.`, "error");
    }
}

function createNewAccount() {
    const accountId = document.getElementById("loginId").value;
    if (!accountId) {
        alert("Please enter an Account ID.");
        return;
    }

    // Create a new account with a zero balance
    if (!accountBalances.hasOwnProperty(accountId)) {
        accountBalances[accountId] = 0; // Initialize balance to zero
        currentAccount = accountId;
        document.getElementById("accountIdDisplay").innerText = `Account created with ID: ${currentAccount}`;
        showAccountSection();
        showResult(`Account created with ID: ${accountId}`, "success");
    } else {
        showResult(`Account ID ${accountId} already exists. Please log in.`, "error");
    }
}

function logout() {
    currentAccount = null;
    document.getElementById("login-section").style.display = "block";
    document.getElementById("account-section").style.display = "none";
    document.getElementById("result").style.display = "none";
    document.getElementById("loginId").value = ""; // Clear the login field
}

function showAccountSection() {
    document.getElementById("login-section").style.display = "none";
    document.getElementById("account-section").style.display = "block";
}

function viewBalance() {
    const balance = accountBalances[currentAccount] || 0; // Default to zero if not set
    showResult(`Current Balance: $${balance.toFixed(2)}`, "success");
}

function deposit() {
    toggleAmountInput("deposit");
}

function withdraw() {
    toggleAmountInput("withdraw");
}

function toggleAmountInput(action) {
    const amountGroup = document.getElementById("amount-group");
    amountGroup.style.display = "block";
    amountGroup.dataset.action = action;
    document.getElementById("amount").value = "";
}

function confirmTransaction() {
    const action = document.getElementById("amount-group").dataset.action;
    const amount = parseFloat(document.getElementById("amount").value);

    if (!amount || isNaN(amount) || amount <= 0) {
        alert("Please enter a valid amount.");
        return;
    }

    if (action === "deposit") {
        accountBalances[currentAccount] += amount;
        showResult(`Deposited $${amount.toFixed(2)} successfully.`, "success");
    } else if (action === "withdraw") {
        if (amount > accountBalances[currentAccount]) {
            showResult("Insufficient balance.", "error");
        } else {
            accountBalances[currentAccount] -= amount;
            showResult(`Withdrew $${amount.toFixed(2)} successfully.`, "success");
        }
    }

    document.getElementById("amount-group").style.display = "none";
}

function showResult(message, type) {
    const result = document.getElementById("result");
    result.innerText = message;
    result.className = `result ${type}`; // Apply success or error styling
    result.style.display = "block";
}
