package com.yedam.app.attend.gps.service;

import lombok.Data;

@Data
public class GpsVO {
	private int  gpsId;
	private String gpsName;
	private double longtitueX;
	private double lattitueY;
	private String xxx;
	private String yyy;
	private double distance; //mailboxinfo 참고
	private int state;
}
