import java.util.Scanner;

class User {
    private String userId;
    private String pin;
    private double balance;

    public User(String userId, String pin, double balance) {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds");
        }
    }

    public void transfer(User recipient, double amount) {
        if (amount <= balance) {
            balance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful");
        } else {
            System.out.println("Insufficient funds for transfer");
        }
    }
}

class ATM {
    private User currentUser;

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter user id: ");
        String userId = scanner.nextLine();

        System.out.print("Enter pin: ");
        String pin = scanner.nextLine();

        
        User user = findUser(userId, pin);

        if (user != null) {
            currentUser = user;
            displayMenu();
        } else {
            System.out.println("Invalid user id or pin");
        }
    }

    private void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Implement Transactions History
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    currentUser.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    currentUser.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient's user id: ");
                    String recipientId = scanner.next();
                    User recipient = findUserById(recipientId);
                    if (recipient != null) {
                        System.out.print("Enter transfer amount: ");
                        double transferAmount = scanner.nextDouble();
                        currentUser.transfer(recipient, transferAmount);
                    } else {
                        System.out.println("Recipient not found");
                    }
                    break;
                case 5:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }

        } while (choice != 5);
    }

    private User findUser(String userId, String pin) {
        
        if ("12345".equals(userId) && "6789".equals(pin)) {
            return new User(userId, pin, 1000.0);  
        }
        return null;
    }

    private User findUserById(String userId) {
        
        if ("54321".equals(userId)) {
            return new User(userId, "0000", 500.0);  
        }
        return null;
    }
}

