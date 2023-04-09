package ro.zynk.futureup.controllers.responses;

import ro.zynk.futureup.domain.dtos.Transaction;

import java.util.Date;

public class TransactionResponse extends BaseResponse{

    private Date date;
    private Long coinId;
    private Double amount;
    private Double totalValue;

    public TransactionResponse(Date date, Long coinId, Double amount, Double totalValue) {
        this.date = date;
        this.coinId = coinId;
        this.amount = amount;
        this.totalValue = totalValue;
    }

    public TransactionResponse(Transaction transaction) {
        this.date=transaction.getDate();
        this.coinId=transaction.getCoin().getId();
        this.amount=transaction.getAmount();
        this.totalValue=transaction.getTotalValue();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCoinId() {
        return coinId;
    }

    public void setCoinId(Long coinId) {
        this.coinId = coinId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }
}
