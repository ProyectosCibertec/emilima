package com.emilima.serviciodocumental.util.edt;

public enum DaoType {
	MYSQL(1);
	
	private int DaoName;

	private DaoType(int daoName) {
		DaoName = daoName;
	}

	public int getDaoName() {
		return DaoName;
	}

	public void setDaoName(int daoName) {
		DaoName = daoName;
	}
}
