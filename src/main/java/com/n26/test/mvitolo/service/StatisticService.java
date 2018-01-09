package com.n26.test.mvitolo.service;

import com.n26.test.mvitolo.exception.ElaborateStatisticException;
import com.n26.test.mvitolo.resources.representation.StatisticRepresentation;

/**
 * Created by Marco on 07/01/2018.
 */
public interface StatisticService {

    /**
     * Returns the statistic based on the transactions which happened in the last 60 seconds.
     * @return @StatisticRepresentation object
     * @throws ElaborateStatisticException
     */
    StatisticRepresentation elaborate();
}
