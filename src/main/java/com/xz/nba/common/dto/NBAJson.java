package com.xz.nba.common.dto;

import java.util.Date;

public class NBAJson {
	private String leftId;
	private String leftName;
	private String leftBadge;
	private Integer leftGoal;
	private String rightId;
	private String rightName;
	private Integer rightGoal;
	private Date startTime;
	private String quarter;
	private String quarterTime;

	public String getLeftId() {
		return leftId;
	}

	public void setLeftId(String leftId) {
		this.leftId = leftId;
	}

	public String getLeftName() {
		return leftName;
	}

	public void setLeftName(String leftName) {
		this.leftName = leftName;
	}

	public String getLeftBadge() {
		return leftBadge;
	}

	public void setLeftBadge(String leftBadge) {
		this.leftBadge = leftBadge;
	}

	public Integer getLeftGoal() {
		return leftGoal;
	}

	public void setLeftGoal(Integer leftGoal) {
		this.leftGoal = leftGoal;
	}

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

	public String getRightName() {
		return rightName;
	}

	public void setRightName(String rightName) {
		this.rightName = rightName;
	}

	public Integer getRightGoal() {
		return rightGoal;
	}

	public void setRightGoal(Integer rightGoal) {
		this.rightGoal = rightGoal;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public String getQuarter() {
		return quarter;
	}

	public void setQuarter(String quarter) {
		this.quarter = quarter;
	}

	public String getQuarterTime() {
		return quarterTime;
	}

	public void setQuarterTime(String quarterTime) {
		this.quarterTime = quarterTime;
	}

}
