package ro.zynk.futureup.services;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;
import ro.zynk.futureup.controllers.responses.ListTransactionsResponse;
import ro.zynk.futureup.controllers.responses.ListWalletResponse;
import ro.zynk.futureup.controllers.responses.TransactionResponse;
import ro.zynk.futureup.controllers.responses.WalletResponse;
import ro.zynk.futureup.domain.dtos.Coin;
import ro.zynk.futureup.domain.dtos.Transaction;
import ro.zynk.futureup.domain.dtos.Wallet;
import ro.zynk.futureup.domain.repositories.CoinRepository;
import ro.zynk.futureup.domain.repositories.TransactionRepository;
import ro.zynk.futureup.domain.repositories.WalletRepository;
import ro.zynk.futureup.exceptions.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionService  {
    private final TransactionRepository transactionRepository;
    private final CoinRepository coinRepository;

    public TransactionService(TransactionRepository transactionRepository,CoinRepository coinRepository) {
        this.transactionRepository = transactionRepository;
        this.coinRepository=coinRepository;
    }
    public ListTransactionsResponse getTransactions() {
        List<Transaction> transactions = transactionRepository.findAll();

        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction t : transactions) {
            transactionResponses.add(new TransactionResponse(t));
        }

        return new ListTransactionsResponse(transactionResponses);
    }

    public ListTransactionsResponse getTransactionsWithValueGreaterThan(Double value) {
        List<Transaction> transactions = transactionRepository.findAllByTotalValueGreaterThan(value);
        List<TransactionResponse> transactionResponses = new ArrayList<>();
        for (Transaction t : transactions) {
            transactionResponses.add(new TransactionResponse(t));
        }
        return new ListTransactionsResponse(transactionResponses);
    }

    public TransactionResponse saveNewTransaction(TransactionResponse transactionResponse) {
        Optional<Coin> coinOpt=coinRepository.findById(transactionResponse.getCoinId());
        if (coinOpt.isEmpty()) {
            throw new NotFoundException("Wallet not found!");
        }
        Coin coin = coinOpt.get();
        Transaction transaction = new Transaction(transactionResponse.getDate(),coin,transactionResponse.getAmount(),transactionResponse.getTotalValue());
        transaction.setTotalValue(coin.getValue()*transactionResponse.getAmount());
        transaction = transactionRepository.save(transaction);
        return new TransactionResponse(transaction);
    }



}
