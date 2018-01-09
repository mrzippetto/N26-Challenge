package com.n26.test.mvitolo.service;

import com.n26.test.mvitolo.resources.data.TransactionData;
import com.sun.media.sound.InvalidDataException;

/**
 * Created by Marco on 09/01/2018.
 */
public interface TransactionService {

    /**
     * Create a new transaction
     * @param transactionData
     * @throws InvalidDataException
     */
    void create(TransactionData transactionData) throws InvalidDataException;
}
