package com.revature.models;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "message")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sender_id")
	// @JsonManagedReference(value="sent_messages")
	private MarketPlaceUser sender;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "receiver_id")
	// @JsonManagedReference(value="received_messages")
	private MarketPlaceUser receiver;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent_message_id")
	private Message parent;

	@Column(length = 50)
	private String subject;
	@Column(length = 750)
	private String content;
	@CreationTimestamp
	private Timestamp created;

	public Message(int id, MarketPlaceUser sender, MarketPlaceUser receiver, Message parent, String subject,
			String content, Timestamp created) {
		super();
		this.id = id;
		this.sender = sender;
		this.receiver = receiver;
		this.parent = parent;
		this.subject = subject;
		this.content = content;
		this.created = created;
	}

	public Message() {
		super();
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public MarketPlaceUser getSender() {
		return sender;
	}

	/**
	 * 
	 * @param sender
	 */
	public void setSender(MarketPlaceUser sender) {
		this.sender = sender;
	}

	/**
	 * 
	 * @return
	 */
	public MarketPlaceUser getReceiver() {
		return receiver;
	}

	/**
	 * 
	 * @param receiver
	 */
	public void setReceiver(MarketPlaceUser receiver) {
		this.receiver = receiver;
	}

	/**
	 * 
	 * @return
	 */
	public Message getParent() {
		return parent;
	}

	/**
	 * 
	 * @param parent
	 */
	public void setParent(Message parent) {
		this.parent = parent;
	}

	/**
	 * 
	 * @return
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * 
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 
	 * @return
	 */
	public Timestamp getCreated() {
		return created;
	}

	/**
	 * 
	 * @param created
	 */
	public void setCreated(Timestamp created) {
		this.created = created;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + ((created == null) ? 0 : created.hashCode());
		result = prime * result + id;
		result = prime * result + ((parent == null) ? 0 : parent.hashCode());
		result = prime * result + ((receiver == null) ? 0 : receiver.hashCode());
		result = prime * result + ((sender == null) ? 0 : sender.hashCode());
		result = prime * result + ((subject == null) ? 0 : subject.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (created == null) {
			if (other.created != null)
				return false;
		} else if (!created.equals(other.created))
			return false;
		if (id != other.id)
			return false;
		if (parent == null) {
			if (other.parent != null)
				return false;
		} else if (!parent.equals(other.parent))
			return false;
		if (receiver == null) {
			if (other.receiver != null)
				return false;
		} else if (!receiver.equals(other.receiver))
			return false;
		if (sender == null) {
			if (other.sender != null)
				return false;
		} else if (!sender.equals(other.sender))
			return false;
		if (subject == null) {
			if (other.subject != null)
				return false;
		} else if (!subject.equals(other.subject))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", parent=" + parent
				+ ", subject=" + subject + ", content=" + content + ", created=" + created + "]";
	}

}
