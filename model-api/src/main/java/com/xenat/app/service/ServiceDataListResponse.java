package com.xenat.app.service;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Service response with array list data and messages.
 *
 * @param <T> the list item data type
 */
public interface ServiceDataListResponse<T extends Serializable> extends ServiceDataResponse<ArrayList<T>> {
}
