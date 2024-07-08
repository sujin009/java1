package com.webjjang.login.vo;

public class LoginVO {

		private String id;
		private String name;
		private String photo;
		private String pw;
		private int gradeNo;
		private String gradeName;
		private Long newMsgCnt;
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getPhoto() {
			return photo;
		}
		public void setPhoto(String photo) {
			this.photo = photo;
		}
		public String getPw() {
			return pw;
		}
		public void setPw(String pw) {
			this.pw = pw;
		}
		public int getGradeNo() {
			return gradeNo;
		}
		public void setGradeNo(int gradeNo) {
			this.gradeNo = gradeNo;
		}
		public String getGradeName() {
			return gradeName;
		}
		public void setGradeName(String gradeName) {
			this.gradeName = gradeName;
		}
		public Long getNewMsgCnt() {
			return newMsgCnt;
		}
		public void setNewMsgCnt(Long newMsgCnt) {
			this.newMsgCnt = newMsgCnt;
		}
		@Override
		public String toString() {
			return "LoginVO [id=" + id + ", name=" + name + ", photo=" + photo + ", pw=" + pw + ", gradeNo=" + gradeNo
					+ ", gradeName=" + gradeName + ", newMsgCnt=" + newMsgCnt + "]";
		}
		
		
}
