package pl.kayzone.exchange.model.entity;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Reference;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Transaction extends BaseEntity implements Serializable {


    @Reference
    private Customers customers;
    private BigDecimal valueTransaction;
    @Embedded
    private List<TransactionCurrency> transactionCurrencyList;

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

    public List<TransactionCurrency> getTransactionCurrencyList() {
        return transactionCurrencyList;
    }

    public void setTransactionCurrencyList(List<TransactionCurrency> transactionCurrencyList) {
        this.transactionCurrencyList = transactionCurrencyList;
    }
}
