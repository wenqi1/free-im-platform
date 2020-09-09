package com.learn.freeim.entity;

import java.awt.image.BufferedImage;

public class VerifyImageCode {
	private String verifyCode;
	private BufferedImage image;
	
	
	public VerifyImageCode(BufferedImage image, String verifyCode) {
		super();
		this.verifyCode = verifyCode;
		this.image = image;
	}
	
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
	@Override
	public String toString() {
		return "VerifyImageCode [verifyCode=" + verifyCode + ", image=" + image + "]";
	}
	
	
}
