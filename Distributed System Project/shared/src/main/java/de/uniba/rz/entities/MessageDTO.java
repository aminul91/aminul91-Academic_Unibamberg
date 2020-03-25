package de.uniba.rz.entities;

import java.io.Serializable;

public class MessageDTO implements Serializable{

	private static final long serialVersionUID = -7104046608541593309L;

	private int messageType;

	private int optionalTicketId;

	private MessageBody messageBody;

	public MessageDTO() {
	}

	public MessageDTO(int messageType, MessageBody messageBody, int optionalTicketId) {
		this.messageType = messageType;
		this.messageBody = messageBody;
		this.optionalTicketId = optionalTicketId;
	}

	public int getMessageType() {
		return messageType;
	}

	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(MessageBody messageBody) {
		this.messageBody = messageBody;
	}

	public int getOptionalTicketId() {
		return optionalTicketId;
	}

	public void setOptionalTicketId(int optionalTicketId) {
		this.optionalTicketId = optionalTicketId;
	}

	@Override
	public String toString() {
		return "MessageDTO [messageType=" + messageType + ", optionalTicketId=" + optionalTicketId + ", messageBody="
				+ messageBody + "]";
	}

}
