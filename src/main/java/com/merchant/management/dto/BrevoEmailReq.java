package com.merchant.management.dto;

import java.util.List;

public class BrevoEmailReq {
	
	private EmailSender sender;
    private List<EmailReceipent> to;
    private String subject;
    private String htmlContent;
    private List<EmailAttachement> attachment;

    public BrevoEmailReq() {}
    
    

    public List<EmailAttachement> getAttachment() {
		return attachment;
	}



	public void setAttachment(List<EmailAttachement> attachment) {
		this.attachment = attachment;
	}



	public EmailSender getSender() {
        return sender;
    }

    public void setSender(EmailSender sender) {
        this.sender = sender;
    }

    public List<EmailReceipent> getTo() {
        return to;
    }

    public void setTo(List<EmailReceipent> to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

}
