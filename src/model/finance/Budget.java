package model.finance;

import exception.BudgetExceededException;
import java.util.ArrayList;
import java.util.List;

public class Budget {
    private double totalLimit;
    private double currentSpending;
    private List<Expense> expenses;

    public Budget(double totalLimit) {
        this.totalLimit = totalLimit;
        this.currentSpending = 0;
        this.expenses = new ArrayList<>();
    }

    // --- OVERLOAD 1: Nesne olarak ekleme ---
    public void addExpense(Expense expense) throws BudgetExceededException {
        if (currentSpending + expense.getAmount() > totalLimit) {
            throw new BudgetExceededException("Limit aşıldı! Harcama yapılamadı: " + expense.getDescription());
        }
        expenses.add(expense);
        currentSpending += expense.getAmount();
    }

    // --- OVERLOAD 2: Parametre ile ekleme ---
    public void addExpense(double amount, String desc) throws BudgetExceededException {
        // Varsayılan olarak OTHER tipinde bir Expense oluşturup diğer metoda yolluyoruz.
        Expense newExpense = new Expense(amount, desc, ExpenseType.OTHER);
        this.addExpense(newExpense);
    }

    public double getRemainingBudget() {
        return totalLimit - currentSpending;
    }

    public double getCurrentSpending() {
        return currentSpending;
    }
}