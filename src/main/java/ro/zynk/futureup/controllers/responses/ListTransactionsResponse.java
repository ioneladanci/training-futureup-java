package ro.zynk.futureup.controllers.responses;

import java.util.List;

public class ListTransactionsResponse extends BaseResponse{
    private List<TransactionResponse> transactionResponses;

    public ListTransactionsResponse(List<TransactionResponse> coinTransactionResponses) {
        this.transactionResponses = coinTransactionResponses;
    }

    public List<TransactionResponse> getTransactionResponses() {
        return transactionResponses;
    }

    public void setWalletResponses(List<TransactionResponse> transactionResponses) {
        this.transactionResponses = transactionResponses;
    }
}
