package pl.kayzone.exchange.model.entity;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Entity(value = "transactions")
public class Transaction extends BaseEntity implements Serializable {

    @Reference
    private Customers customers;
    private BigDecimal valueTransaction;
    @Reference
    private Set<TransactionCurrency> transactionCurrencyList;
    private LocalDateTime transactionTime;

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public BigDecimal getValueTransaction() {
        return valueTransaction;
    }

    public void setValueTransaction(BigDecimal valueTransaction) {
        this.valueTransaction = valueTransaction;
    }

    public Set<TransactionCurrency> getTransactionCurrencyList() {
        return transactionCurrencyList;
    }

    public void setTransactionCurrencyList(Set<TransactionCurrency> transactionCurrencyList) {
        this.transactionCurrencyList = transactionCurrencyList;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
