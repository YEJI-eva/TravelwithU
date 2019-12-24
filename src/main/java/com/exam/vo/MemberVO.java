package com.exam.vo;

import java.sql.Timestamp;

public class MemberVO {
	private String id;// 게시글 번호
	private String passwd; // 로그인 아이디 또는 작성자 이름
	private String name;
	private String email;
	private String address;
	private String tel;
	private String mtel;
	private Timestamp regDate;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getMtel() {
		return mtel;
	}

	public void setMtel(String mtel) {
		this.mtel = mtel;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MemberVO [id=").append(id).append(", passwd=").append(passwd).append(", name=").append(name)
				.append(", email=").append(email).append(", address=").append(address).append(", tel=").append(tel)
				.append(", mtel=").append(mtel).append(", regDate=").append(regDate).append("]");
		return builder.toString();
	}

}
