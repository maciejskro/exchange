package pl.kayzone.exchange.model.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class CurrencyCourse implements Serializable
{

    @Id
    private ObjectId id;
    private String idCode;
    private LocalDateTime date;
    private LocalDateTime validTo;
    private BigDecimal bid;
    private BigDecimal ask;
    private Boolean active;
    private final static long serialVersionUID = 6513736575303961048L;

    /**
     * No args constructor for use in serialization
     *
     */
    public CurrencyCourse() {
    }

    /**
     *
     * @param validTo
     * @param idCode
     * @param active
     * @param ask
     * @param date
     * @param bid
     */
    public CurrencyCourse(String idCode, LocalDateTime date, LocalDateTime validTo, BigDecimal bid, BigDecimal ask, Boolean active) {
        super();
        this.idCode = idCode;
        this.date = date;
        this.validTo = validTo;
        this.bid = bid;
        this.ask = ask;
        this.active = active;
    }

    public CurrencyCourse(String code, String code1, BigDecimal bid, BigDecimal ask) {
        this(code, LocalDateTime.now(),LocalDateTime.now().plusDays(1), bid,ask,true );

    }

    public String getIdCode() {
        return idCode;
    }

    public void setIdCode(String idCode) {
        this.idCode = idCode;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public LocalDateTime getValidTo() {
        return validTo;
    }

    public void setValidTo(LocalDateTime validTo) {
        this.validTo = validTo;
    }


    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("idCode", idCode)
                .append("date", date)
                .append("validTo", validTo)
                .append("bid", bid)
                .append("ask", ask)
                .append("active", active).toString();
    }
}

