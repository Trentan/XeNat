package com.xenat.app.service;

import com.github.bordertech.restfriends.envelope.DataEnvelope;

import java.io.Serializable;

/**
 * Service response with data and messages.
 *
 * @param <T> the response data type
 */
public interface ServiceDataResponse<T extends Serializable> extends ServiceMessagesResponse, DataEnvelope<T> {

}
