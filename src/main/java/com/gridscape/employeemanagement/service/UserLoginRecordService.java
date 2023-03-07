package com.gridscape.employeemanagement.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gridscape.employeemanagement.model.UserLoginRecord;
import com.gridscape.employeemanagement.repository.UserLoginRecordrepository;

@Service
@Transactional
public class UserLoginRecordService {

	@Autowired
	private UserLoginRecordrepository loginRecordrepository;

	public void saveRecord(Long mili) {
		Date date = new Date(mili);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		String dateString = format.format(date);
		try {
			Date finalDate = format.parse(dateString);
			System.out.println(finalDate.toInstant().toEpochMilli());
			this.loginRecordrepository.insertLoginCountPerMinute(finalDate.toInstant().toEpochMilli());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public List<UserLoginRecord> getrecord() {
		return this.loginRecordrepository.findAll();
	}

	public List<List<Long>> getAllData() {

		return this.loginRecordrepository.getData();
	}
}
