package com.nts.mrb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

public class AuditClass {
	@Column(name = "operation")
    private String operation;
      
    @Column(name = "timestamp")
    private long timestamp;
	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	@PrePersist
    public void onPrePersist() {
        audit("INSERT");
    }
      
    @PreUpdate
    public void onPreUpdate() {
        audit("UPDATE");
    }
      
    @PreRemove
    public void onPreRemove() {
        audit("DELETE");
    }
      
    private void audit(String operation) {
        setOperation(operation);
        setTimestamp((new Date()).getTime());
    }

}
