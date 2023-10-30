package com.supermarket.DTO;
import com.supermarket.utils.Date;
public class Statistic {
    private int id;
    private Date date;
    private int  amount;
    private int  expenses;
    private boolean deleted;
    public Statistic() {
    }
    public Statistic(int id, Date date, int amount, int expenses, boolean deleted) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.expenses = expenses;
        this.deleted = deleted;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getExpenses() {
        return expenses;
    }

    public void setExpenses(int expenses) {
        this.expenses = expenses;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    public String toString() {
        return id + " | " +
            date + " | " +
            amount + " | " +
            expenses ;
    }
}
