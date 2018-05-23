package pl.kayzone.exchange.controler;

import org.bson.types.ObjectId;
import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionService  {

    ObjectId createNewTransaction(Currency who);
    boolean createSell(CurrencyCourse cc, BigDecimal quantity);
    boolean createBuy(CurrencyCourse cc, BigDecimal quantity);

    BigDecimal getSoldCurrency(String code, LocalDateTime start, LocalDateTime end);
    BigDecimal getBuyCurrency(String code, LocalDateTime start, LocalDateTime end);
}
