package com.blogapi.blogservice.configuration;

import java.lang.management.ManagementFactory;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.management.JMX;
import javax.management.MBeanServer;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.blogapi.blogservice.configuration.model.DataSourceConfigurationModel;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariPoolMXBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
public class DataSource2Configuration {


	
	@Autowired
	DataSourceConfigurationModel  configData;
	
	private boolean initializedFlag = false;
	static int conRetryAttempt = 0;
	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;
	public static final boolean CONNECTION_LEAK_LOGGING_REQUIRED = true;
	
	public static MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
	public static HikariPoolMXBean poolProxy; 
	
	@PostConstruct
	public void initializeDatabase() {
		init();
	}
	
	public void init() {
		createAuctionDataSource();
	}

	private void createAuctionDataSource() {
		if(!initializedFlag){
			initializedFlag=true;
			try {
				config.setJdbcUrl(configData.getUrl().trim());
		        config.setDriverClassName(configData.getDriverClassName().trim());
		        config.addDataSourceProperty("cachePrepStmts" ,"true");
		        config.addDataSourceProperty("prepStmtCacheSize","250");
		        config.addDataSourceProperty("prepStmtCacheSqlLimit","2048");
		        config.setPoolName("Auction");
		        config.setMaximumPoolSize(configData.getMaxConn());
		        config.setMinimumIdle(configData.getMinIdle());
		        config.setRegisterMbeans(true);
		        ds = new HikariDataSource(config);
				final Connection con = ds.getConnection();
				con.close();
				final ObjectName poolName = new ObjectName("com.zaxxer.hikari:type=Pool ("+ds.getPoolName()+")");
				poolProxy = JMX.newMXBeanProxy(mBeanServer, poolName, HikariPoolMXBean.class);
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	public Connection getMasterDBConnection()  {
		Connection conn=null;
		try {
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

    public void closeConnection(Connection con) {
        if (con != null) {
            try {
                con.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
		}
	}

}
