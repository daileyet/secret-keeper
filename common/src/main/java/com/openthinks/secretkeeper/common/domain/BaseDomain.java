package com.openthinks.secretkeeper.common.domain;

import java.io.Serializable;
import java.util.UUID;

import com.openthinks.secretkeeper.common.Synchronized;
import com.openthinks.secretkeeper.common.Unique;
import com.openthinks.secretkeeper.common.domain.support.DomainState;

public abstract class BaseDomain implements Serializable, Unique, Synchronized {
	private static final long serialVersionUID = 6632400199239945L;
	protected String uniqueID;
	protected DomainState state = DomainState.NEW;

	public void generateUniqueID() {
		this.uniqueID = UUID.randomUUID().toString();
	}

	@Override
	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	@Override
	public boolean isSynchro() {
		return state == null ? false : state.isSynchro();
	}

	public DomainState getState() {
		return state;
	}

	public void setState(DomainState state) {
		this.state = state;
	}

}
