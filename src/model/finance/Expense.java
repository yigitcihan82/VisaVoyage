package model.finance;

public class Expense {
    private double amount;
    private String description;
    private ExpenseType type;

    public Expense(double amount, String description, ExpenseType type) {
        this.amount = amount;
        this.description = description;
        this.type = type;
    }

    public double getAmount() { return amount; }
    public String getDescription() { return description; }
    public ExpenseType getType() { return type; }
}