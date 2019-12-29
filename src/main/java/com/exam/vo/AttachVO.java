package com.exam.vo;

public class AttachVO {
	private String uuid;
	private String uploadpath;
	private String filename;
	private String filetype;
	private int bno;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUploadpath() {
		return uploadpath;
	}

	public void setUploadpath(String uploadpath) {
		this.uploadpath = uploadpath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFiletype() {
		return filetype;
	}

	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AttachVO [uuid=").append(uuid).append(", uploadpath=").append(uploadpath).append(", filename=")
				.append(filename).append(", filetype=").append(filetype).append(", bno=").append(bno).append("]");
		return builder.toString();
	}

}
