package com.n26.test.mvitolo.service.impl;

import com.n26.test.mvitolo.resources.data.Cache;
import com.n26.test.mvitolo.resources.data.TransactionData;
import com.n26.test.mvitolo.resources.representation.StatisticRepresentation;
import com.n26.test.mvitolo.service.StatisticService;
import org.apache.log4j.Logger;

import javax.inject.Inject;
import java.time.Instant;
import java.util.DoubleSummaryStatistics;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Marco on 09/01/2018.
 */
public class StatisticServiceImpl implements StatisticService {

    final static Logger log = Logger.getLogger(StatisticServiceImpl.class);

    @Inject
    private Cache<TransactionData> cache;

    @Override
    public StatisticRepresentation elaborate() {

        StatisticRepresentation.Builder builder = StatisticRepresentation.builder();
        DoubleSummaryStatistics stats = cache.get().parallelStream()
                .filter(transactionData ->
                        ((Instant.now().toEpochMilli() - transactionData.timestamp()) < 60_000))
                .mapToDouble((x) -> x.amount())
                .summaryStatistics();

        AtomicReference<DoubleSummaryStatistics> statisticsAtomicReference = new AtomicReference<>(stats);
        builder.avg(statisticsAtomicReference.get().getAverage());
        builder.min(
                Double.isInfinite(statisticsAtomicReference.get().getMin()) ? 0.0 : statisticsAtomicReference.get().getMin());
        builder.max(
                Double.isInfinite(statisticsAtomicReference.get().getMin())? 0.0 : statisticsAtomicReference.get().getMax());
        builder.count(statisticsAtomicReference.get().getCount());
        builder.sum(statisticsAtomicReference.get().getSum());

        return builder.build();
    }

}
