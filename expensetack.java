import java.util.ArrayList;
import java.util.Scanner;

// ---------------- EXPENSE CLASS ----------------
class Expense {
    private int expenseId;
    private double amount;
    private String category;
    private String description;

    public Expense(int expenseId, double amount, String category, String description) {
        this.expenseId = expenseId;
        this.amount = amount;
        this.category = category;
        this.description = description;
    }

    public int getExpenseId() {
        return expenseId;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return "Expense ID: " + expenseId +
                ", Amount: ₹" + amount +
                ", Category: " + category +
                ", Description: " + description;
    }
}

// ---------------- EXPENSE MANAGER CLASS ----------------
class ExpenseManager {
    private ArrayList<Expense> expenses;
    private int nextId;

    public ExpenseManager() {
        expenses = new ArrayList<>();
        nextId = 1;
    }

    public void addExpense(double amount, String category, String description) {
        Expense exp = new Expense(nextId, amount, category, description);
        expenses.add(exp);
        System.out.println("✔ Expense added with ID " + nextId);
        nextId++;
    }

    public void deleteExpense(int expenseId) {
        boolean removed = expenses.removeIf(e -> e.getExpenseId() == expenseId);
        if (removed)
            System.out.println("✔ Expense removed");
        else
            System.out.println("❌ Expense ID not found");
    }

    public void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
            return;
        }
        System.out.println("\n---- All Expenses ----");
        for (Expense e : expenses)
            System.out.println(e);
    }

    public void searchByCategory(String category) {
        boolean found = false;
        System.out.println("\n---- Expenses in Category: " + category + " ----");
        for (Expense e : expenses) {
            if (e.getCategory().equalsIgnoreCase(category)) {
                System.out.println(e);
                found = true;
            }
        }
        if (!found)
            System.out.println("No expenses found in this category.");
    }

    public double getTotalExpense() {
        double total = 0;
        for (Expense e : expenses)
            total += e.getAmount();
        return total;
    }
}

// ---------------- MAIN CLASS ----------------
public class expenset {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseManager manager = new ExpenseManager();

        while (true) {
            try {
                System.out.println("\n===== EXPENSE TRACKER MENU =====");
                System.out.println("1. Add Expense");
                System.out.println("2. Delete Expense");
                System.out.println("3. View All Expenses");
                System.out.println("4. Search By Category");
                System.out.println("5. View Total Expense");
                System.out.println("6. Exit");
                System.out.print("Choose an option: ");

                int choice = Integer.parseInt(sc.nextLine());

                switch (choice) {
                    case 1:
                        System.out.print("Enter amount: ");
                        double amount = Double.parseDouble(sc.nextLine());

                        System.out.print("Enter category: ");
                        String category = sc.nextLine();

                        System.out.print("Enter description: ");
                        String desc = sc.nextLine();

                        manager.addExpense(amount, category, desc);
                        break;

                    case 2:
                        System.out.print("Enter Expense ID to delete: ");
                        int id = Integer.parseInt(sc.nextLine());
                        manager.deleteExpense(id);
                        break;

                    case 3:
                        manager.viewAllExpenses();
                        break;

                    case 4:
                        System.out.print("Enter category to search: ");
                        String cat = sc.nextLine();
                        manager.searchByCategory(cat);
                        break;

                    case 5:
                        System.out.println("Total Expense = ₹" + manager.getTotalExpense());
                        break;

                    case 6:
                        System.out.println("Exiting... Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Enter numbers only where required.");
            } catch (Exception e) {
                System.out.println("❌ Error: " + e.getMessage());
            }
        }
    }
}
