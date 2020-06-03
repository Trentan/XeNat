package com.xenat.app.model.list;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Java Class List Criteria.
 * @author Trentan Healey
 * @since POC1
 */
public class ListCriteria implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private String moduleFrom;
	@NotNull
	private String moduleTo;
	@NotNull
	@ApiModelProperty(dataType = "dateTime")
	private Date sourceFromTs;
	@NotNull
	@ApiModelProperty(dataType = "dateTime")
	private Date sourceToTs;
	@NotNull
	private List<String> library;
	@NotNull
	private List<String> userId;

	public String getModuleFrom() {
		return moduleFrom;
	}

	public void setModuleFrom(String moduleFrom) {
		this.moduleFrom = moduleFrom;
	}

	public String getModuleTo() {
		return moduleTo;
	}

	public void setModuleTo(String moduleTo) {
		this.moduleTo = moduleTo;
	}

	public Date getSourceFromTs() {
		return sourceFromTs;
	}

	public void setSourceFromTs(Date sourceFromTs) {
		this.sourceFromTs = sourceFromTs;
	}

	public Date getSourceToTs() {
		return sourceToTs;
	}

	public void setSourceToTs(Date sourceToTs) {
		this.sourceToTs = sourceToTs;
	}

	public List<String> getLibrary() {
		return library;
	}

	public void setLibrary(List<String> library) {
		this.library = library;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}
}
