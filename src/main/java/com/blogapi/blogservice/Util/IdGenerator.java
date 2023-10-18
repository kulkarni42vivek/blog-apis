package com.blogapi.blogservice.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.blogapi.blogservice.configuration.DataSource2Configuration;
import com.blogapi.blogservice.configuration.QueryMaster;

@Component
public class IdGenerator {
	
	@Autowired
	DataSource2Configuration masterdb;
	
	public String getSequenctNext(String sequence ,Logger log) {
		String otp = null;
		Connection conn = null;
		QueryMaster queryMaster = new QueryMaster();
		StringBuilder query = new StringBuilder();
		List<Object> params  = new ArrayList<>();
		try {
			conn = masterdb.getMasterDBConnection();
			query.append("SELECT NEXTVAL('?')");
			params.add(sequence);
			ResultSet rs = queryMaster.select(query.toString(), null, conn, log);
			while (rs.next()) {
				otp = rs.getString("nextval");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (Objects.nonNull(conn)) {
				masterdb.closeConnection(conn);
			}
		}
		return otp;
	}
}
