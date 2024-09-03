package com.javaex.vo;

public class AttachVo {
	
	// 필드
	private int no;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String filePath;
	
	
	// 생성자
	public AttachVo() {
		super();
	}

	public AttachVo(String orgName, String saveName, long fileSize, String filePath) {
		super();
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}

	public AttachVo(int no, String orgName, String saveName, long fileSize, String filePath) {
		super();
		this.no = no;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.filePath = filePath;
	}

	// 메소드 gs
	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getOrgName() {
		return orgName;
	}


	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public String getSaveName() {
		return saveName;
	}


	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	
	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	// 메소드 일반
	@Override
	public String toString() {
		return "AttachVo [no=" + no + ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize
				+ ", filePath=" + filePath + "]";
	}
	

}
