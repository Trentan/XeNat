package com.xenat.app.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Extra message details in the response.
 */
public class MessagesMeta implements Serializable {
	
	private List<Messages> messages;

	public MessagesMeta(List<Messages> msgs) {
		if (msgs != null) {
			this.messages = msgs;
		}
	}

	/**
	 * @return the type messages
	 */
	public List<String> getTypeMessages(Messages.MessagesSeverityType type) {
		if (this.messages == null) {
			this.messages = new ArrayList<>();
		}
		List<String> msgs = new ArrayList<>();
		for(Messages msg : this.messages)
		{
			if(msg.getSeverity().equals(type)) {
				msgs.add(msg.getDetail());
			}
		}
		return msgs;
	}

	/**
	 * @return true if has type messages
	 */
	public boolean hasTypeMessages(Messages.MessagesSeverityType type) {
		if(messages != null && !messages.isEmpty()) {
			for(Messages msg : this.messages) {
				if (msg.getSeverity().equals(type)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * @return true if has warning messages
	 */
	public boolean hasInfoMessages() {
		return hasTypeMessages(Messages.MessagesSeverityType.INFO);
	}

	/**
	 * @return true if has warning messages
	 */
	public boolean hasWarningMessages() {
		return hasTypeMessages(Messages.MessagesSeverityType.WARN);
	}

	/**
	 * @return true if has success messages
	 */
	public boolean hasSuccessMessages() {
		return hasTypeMessages(Messages.MessagesSeverityType.SUCC);
	}

	public List<Messages> getMessages() {
		return messages;
	}

	public void setMessages(List<Messages> messages) {
		this.messages = messages;
	}

	/**
	 * @param message add info message
	 */
	public void addMessage(final Messages message) {
		if (message != null) {
			this.getMessages().add(message);
		}
	}

	/**
	 * @return true if has any messages
	 */
	public boolean hasAnyMessages() {
		return hasInfoMessages() || hasWarningMessages() || hasSuccessMessages();
	}

}
