package com.xenat.app.model.list;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Java Class List Detail.
 * @author Trentan Healey
 * @since POC1
 */
public class ListDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	@NotNull
	private String library;
	@NotNull
	private String object;
	@NotNull
	private String srcType;
	@NotNull	
	@ApiModelProperty(dataType = "dateTime")
	private Date srcDateTime;
	@NotNull
	private String srcUser;
	@NotNull
	private String srcVersion;
	@NotNull
	private Integer srcSize;
	@NotNull
	private String srcProgMode;
	@NotNull
	private String objType;
	@NotNull
	@ApiModelProperty(dataType = "dateTime")
	private Date objDateTime;
	@NotNull
	private String objUser;
	@NotNull
	private String objVersion;
	@NotNull
	private Integer objSize;
	@NotNull
	private String objGdaName;

	public String getLibrary() {
		return library;
	}

	public void setLibrary(String library) {
		this.library = library;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getSrcType() {
		return srcType;
	}

	public void setSrcType(String srcType) {
		this.srcType = srcType;
	}

	public Date getSrcDateTime() {
		return srcDateTime;
	}

	public void setSrcDateTime(Date srcDateTime) {
		this.srcDateTime = srcDateTime;
	}

	public String getSrcUser() {
		return srcUser;
	}

	public void setSrcUser(String srcUser) {
		this.srcUser = srcUser;
	}

	public String getSrcVersion() {
		return srcVersion;
	}

	public void setSrcVersion(String srcVersion) {
		this.srcVersion = srcVersion;
	}

	public Integer getSrcSize() {
		return srcSize;
	}

	public void setSrcSize(Integer srcSize) {
		this.srcSize = srcSize;
	}

	public String getSrcProgMode() {
		return srcProgMode;
	}

	public void setSrcProgMode(String srcProgMode) {
		this.srcProgMode = srcProgMode;
	}

	public String getObjType() {
		return objType;
	}

	public void setObjType(String objType) {
		this.objType = objType;
	}

	public Date getObjDateTime() {
		return objDateTime;
	}

	public void setObjDateTime(Date objDateTime) {
		this.objDateTime = objDateTime;
	}

	public String getObjUser() {
		return objUser;
	}

	public void setObjUser(String objUser) {
		this.objUser = objUser;
	}

	public String getObjVersion() {
		return objVersion;
	}

	public void setObjVersion(String objVersion) {
		this.objVersion = objVersion;
	}

	public Integer getObjSize() {
		return objSize;
	}

	public void setObjSize(Integer objSize) {
		this.objSize = objSize;
	}

	public String getObjGdaName() {
		return objGdaName;
	}

	public void setObjGdaName(String objGdaName) {
		this.objGdaName = objGdaName;
	}
}
