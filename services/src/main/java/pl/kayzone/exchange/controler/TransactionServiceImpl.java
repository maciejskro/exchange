package pl.kayzone.exchange.controler;

import org.bson.types.ObjectId;
import pl.kayzone.exchange.model.entity.Currency;
import pl.kayzone.exchange.model.entity.CurrencyCourse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public ObjectId createNewTransaction(Currency who) {
        return null;
    }

    @Override
    public boolean createSell(CurrencyCourse cc, BigDecimal quantity) {
        return false;
    }

    @Override
    public boolean createBuy(CurrencyCourse cc, BigDecimal quantity) {
        return false;
    }

    @Override
    public BigDecimal getSoldCurrency(String code, LocalDateTime start, LocalDateTime end) {
        return null;
    }

    @Override
    public BigDecimal getBuyCurrency(String code, LocalDateTime start, LocalDateTime end) {
        return null;
    }
}
