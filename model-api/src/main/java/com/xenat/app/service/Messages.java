package com.xenat.app.service;

import java.io.Serializable;

/**
 * Extra message details in the response.
 */
public class Messages implements Serializable {
	public String id;
	public MessagesSeverityType severity;
	public String code;
	public String detail;
	public MessageSource source;
//	public List<HelpUrl> helpUrls; //	helpUrl
//	public List<HelpText> helpTexts;//	helpText

	public Messages(String ptid, String moduleName, String msg_cod, String msg_fld, String msg_txt, String msg_typ) {
		this.id = ptid;
		this.severity = MessagesSeverityType.fromValue(msg_typ);
		this.code = msg_cod;
		this.detail = msg_txt;
		this.source = new MessageSource(moduleName, msg_fld);
	}

	public Messages(String msg_txt, String msg_typ) {
		this.severity = MessagesSeverityType.fromValue(msg_typ);
		this.detail = msg_txt;
	}

	/**
	 * Java class for PortType.
	 */
	public enum MessagesSourceLocationType {
		REQUEST("REQUEST"), // – Indicates the message relates to a property within the request object. The pointer property should be populated in this case.
		QUERY("QUERY"), // – Indicates the message relates to a query parameter. The parameter property should be populated in this case.
		ID("ID"); // – Indicates the message relates to the identifier of the REST resource. The parameter property may optionally be populated in this case.
		private final String value;

		MessagesSourceLocationType(final String v) {
			value = v;
		}

		public static MessagesSourceLocationType fromValue(final String v) {
			for (MessagesSourceLocationType c : MessagesSourceLocationType.values()) {
				if (c.value.equals(v)) {
					return c;
				}
			}
			throw new IllegalArgumentException(v);
		}

		public String value() {
			return value;
		}
	}

	public enum MessagesSeverityType {

		INFO("INFO"),
		SUCC("SUCC"),
		WARN("WARN");
		private final String value;

		MessagesSeverityType(final String v) {
			value = v;
		}

		public static MessagesSeverityType fromValue(final String v) {
			switch (v) {
				case "I":
					return INFO;
				case "S":
					return SUCC;
				case "W":
					return WARN;
				default:
					throw new IllegalArgumentException(v);
			}
		}

		public String value() {
			return value;
		}

	}

	public class MessageSource {
		public String pointer;
		public String parameter;
		public MessagesSourceLocationType location;

		public MessageSource(String moduleName, String msg_fld) {
			this.location = MessagesSourceLocationType.REQUEST; //TODO this will have to be identified properly not hard coded?
			this.parameter = msg_fld;
			this.pointer = moduleName;
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MessagesSeverityType getSeverity() {
		return severity;
	}

	public void setSeverity(MessagesSeverityType severity) {
		this.severity = severity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public MessageSource getSource() {
		return source;
	}

	public void setSource(MessageSource source) {
		this.source = source;
	}
}
