package com.epam.infohandling.service.sorter;

import java.util.Comparator;

public interface Sorter<T> {

    T sortBy(T t, Comparator<T> comparator);

}
