package com.n26.test.mvitolo.resources.data;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marco on 07/01/2018.
 */
public class CacheImpl implements Cache<TransactionData> {

    List<TransactionData> cache = new ArrayList<>();

    @Override
    public List<TransactionData> get() {
        return cache;
    }

    @Override
    public void set(@Nonnull TransactionData transactionRepresentation) {
        cache.add(transactionRepresentation);
    }
}
