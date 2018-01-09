package com.n26.test.mvitolo.resources.data;

import javax.annotation.Nonnull;
import java.util.List;

/**
 * Created by Marco on 09/01/2018.
 */
public interface Cache<DataType> {

    public List<DataType> get();

    public void set(@Nonnull DataType transactionRepresentation);
}
