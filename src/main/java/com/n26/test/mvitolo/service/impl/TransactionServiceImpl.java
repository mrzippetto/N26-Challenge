package com.n26.test.mvitolo.service.impl;

import com.n26.test.mvitolo.resources.data.Cache;
import com.n26.test.mvitolo.resources.data.TransactionData;
import com.n26.test.mvitolo.service.TransactionService;
import com.sun.media.sound.InvalidDataException;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.time.Instant;

/**
 * Created by Marco on 07/01/2018.
 */
public class TransactionServiceImpl implements TransactionService {

    final static Logger log = Logger.getLogger(TransactionServiceImpl.class);

    @Inject
    private Cache<TransactionData> cache;

    @Override
    public void create( TransactionData transactionData ) throws InvalidDataException {
        long epochMilli = Instant.now().toEpochMilli();
        log.info("Save new transaction: " + transactionData);
        log.info("NOW: " + Instant.now().toEpochMilli());
        if( ( epochMilli - transactionData.timestamp()) > 60_000  ){
            log.warn("An error has occurred! Transaction is older than 60 seconds");
            throw new InvalidDataException("Transaction is older than 60 seconds");
        }
        cache.set( transactionData );
    }

}
