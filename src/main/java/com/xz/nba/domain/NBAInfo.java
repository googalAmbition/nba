package com.xz.nba.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.xz.nba.common.dto.NBAJson;

import java.util.Date;

/**
 * @author ctc
 */
@TableName("t_info_nba")
public class NBAInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableId(type = IdType.UUID)
	private String id;

	private String leftName;

	private Integer leftGoal;

	private String leftId;

	private String rightName;

	private Integer rightGoal;

	private String rightId;

	@TableField("date")
	private Date startTime;

	private Integer leftQuarter1;

	private Integer leftQuarter2;

	private Integer leftQuarter3;

	private Integer leftQuarter4;

	private Integer leftQuarter5;

	private Integer leftQuarter6;

	private Integer rightQuarter1;

	private Integer rightQuarter2;

	private Integer rightQuarter3;

	private Integer rightQuarter4;

	private Integer rightQuarter5;

	private Integer rightQuarter6;

	@Override
	public String toString() {
		return "NBAInfo [id=" + id + ", leftName=" + leftName + ", leftGoal=" + leftGoal + ", rightName=" + rightName
				+ ", rightGoal=" + rightGoal + ", startTime=" + startTime + ", leftQuarter1=" + leftQuarter1
				+ ", leftQuarter2=" + leftQuarter2 + ", leftQuarter3=" + leftQuarter3 + ", leftQuarter4=" + leftQuarter4
				+ ", leftQuarter5=" + leftQuarter5 + ", leftQuarter6=" + leftQuarter6 + ", rightQuarter1="
				+ rightQuarter1 + ", rightQuarter2=" + rightQuarter2 + ", rightQuarter3=" + rightQuarter3
				+ ", rightQuarter4=" + rightQuarter4 + ", rightQuarter5=" + rightQuarter5 + ", rightQuarter6="
				+ rightQuarter6 + "]";
	}

	public NBAInfo() {
	}

	public NBAInfo(NBAJson json) {
		this.leftName = json.getLeftName();
		this.leftGoal = json.getLeftGoal();
		this.rightName = json.getRightName();
		this.rightGoal = json.getRightGoal();
		this.startTime = json.getStartTime();
		this.leftId = json.getLeftId();
		this.rightId = json.getRightId();
		this.leftQuarter1 = 0;
		this.leftQuarter2 = 0;
		this.leftQuarter3 = 0;
		this.leftQuarter4 = 0;
		this.leftQuarter5 = 0;
		this.leftQuarter6 = 0;
		this.rightQuarter1 = 0;
		this.rightQuarter2 = 0;
		this.rightQuarter3 = 0;
		this.rightQuarter4 = 0;
		this.rightQuarter5 = 0;
		this.rightQuarter6 = 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLeftName() {
		return leftName;
	}

	public void setLeftName(String leftName) {
		this.leftName = leftName;
	}

	public Integer getLeftGoal() {
		return leftGoal;
	}

	public void setLeftGoal(Integer leftGoal) {
		this.leftGoal = leftGoal;
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

	public Integer getLeftQuarter1() {
		return leftQuarter1;
	}

	public void setLeftQuarter1(Integer leftQuarter1) {
		this.leftQuarter1 = leftQuarter1;
	}

	public Integer getLeftQuarter2() {
		return leftQuarter2;
	}

	public void setLeftQuarter2(Integer leftQuarter2) {
		this.leftQuarter2 = leftQuarter2;
	}

	public Integer getLeftQuarter3() {
		return leftQuarter3;
	}

	public void setLeftQuarter3(Integer leftQuarter3) {
		this.leftQuarter3 = leftQuarter3;
	}

	public Integer getLeftQuarter4() {
		return leftQuarter4;
	}

	public void setLeftQuarter4(Integer leftQuarter4) {
		this.leftQuarter4 = leftQuarter4;
	}

	public Integer getLeftQuarter5() {
		return leftQuarter5;
	}

	public void setLeftQuarter5(Integer leftQuarter5) {
		this.leftQuarter5 = leftQuarter5;
	}

	public Integer getLeftQuarter6() {
		return leftQuarter6;
	}

	public void setLeftQuarter6(Integer leftQuarter6) {
		this.leftQuarter6 = leftQuarter6;
	}

	public Integer getRightQuarter1() {
		return rightQuarter1;
	}

	public void setRightQuarter1(Integer rightQuarter1) {
		this.rightQuarter1 = rightQuarter1;
	}

	public Integer getRightQuarter2() {
		return rightQuarter2;
	}

	public void setRightQuarter2(Integer rightQuarter2) {
		this.rightQuarter2 = rightQuarter2;
	}

	public Integer getRightQuarter3() {
		return rightQuarter3;
	}

	public void setRightQuarter3(Integer rightQuarter3) {
		this.rightQuarter3 = rightQuarter3;
	}

	public Integer getRightQuarter4() {
		return rightQuarter4;
	}

	public void setRightQuarter4(Integer rightQuarter4) {
		this.rightQuarter4 = rightQuarter4;
	}

	public Integer getRightQuarter5() {
		return rightQuarter5;
	}

	public void setRightQuarter5(Integer rightQuarter5) {
		this.rightQuarter5 = rightQuarter5;
	}

	public Integer getRightQuarter6() {
		return rightQuarter6;
	}

	public void setRightQuarter6(Integer rightQuarter6) {
		this.rightQuarter6 = rightQuarter6;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getLeftId() {
		return leftId;
	}

	public void setLeftId(String leftId) {
		this.leftId = leftId;
	}

	public String getRightId() {
		return rightId;
	}

	public void setRightId(String rightId) {
		this.rightId = rightId;
	}

}
