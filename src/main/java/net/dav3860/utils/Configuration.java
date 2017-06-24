package net.dav3860.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configuration {

	static Integer clCacheSize;
	public static final String CLCACHESIZE = "100";
	static Integer clCacheTtl;
	public static final String CLCACHETTL = "15";
	static Integer connectTimeout;
	public static final String CONNECTTIMEOUT = "30000";
	static Integer counterCacheSize;
	public static final String COUNTERCACHESIZE = "1000";
	static Integer counterCacheTtl;
	public static final String COUNTERCACHETTL = "5";
	static Integer dsCacheSize;
	public static final String DSCACHESIZE = "100";
	static Integer dsCacheTtl;
	public static final String DSCACHETTL = "15";
	static Boolean escapeChars;
	public static final String ESCAPECHARS = "false";
	static Integer esxiCacheSize;
	public static final String ESXICACHESIZE = "100";
	static Integer esxiCacheTtl;
	public static final String ESXICACHETTL = "15";
	static Integer hriCacheSize;
	public static final String HRICACHESIZE = "100";
	static Integer hriCacheTtl;
	public static final String HRICACHETTL = "15";
	static Integer interval;
	public static final String INTERVAL = "300";
	static String ipaddr;
	// Logging
	static final Logger LOG = LoggerFactory.getLogger(Configuration.class);
	static Integer maxConnections;
	public static final String MAXCONNECTIONS = "150";
	static String passwd;
	static Integer perfIdCacheSize;
	public static final String PERFIDCACHESIZE = "1000";
	static Integer perfIdCacheTtl;
	public static final String PERFIDCACHETTL = "5";
	static String pidFile;
	static Integer port;
	private static Properties prop;
	static Integer readTimeout;
	public static final String READTIMEOUT = "30000";
	static String sdkUrl;
	static String uname;
	static Boolean useUuid;
	public static final String USEUUID = "false";
	static Integer vmCacheSize;
	public static final String VMCACHESIZE = "1000";
	static Integer vmCacheTtl;
	public static final String VMCACHETTL = "15";

	/**
	 * @return the clCacheSize
	 */
	public static Integer getClCacheSize() {
		return  Integer.parseInt(prop.getProperty("clcachesize", CLCACHESIZE));
	}

	/**
	 * @return the clCacheTtl
	 */
	public static Integer getClCacheTtl() {
		return  Integer.parseInt(prop.getProperty("clcachettl", CLCACHETTL));
	}

	/**
	 * @return the connectTimeout
	 */
	public static Integer getConnectTimeout() {
		return Integer.parseInt(prop.getProperty("connecttimeout", CONNECTTIMEOUT));
	}

	/**
	 * @return the counterCacheSize
	 */
	public static Integer getCounterCacheSize() {
		return  Integer.parseInt(prop.getProperty("countercachesize", COUNTERCACHESIZE));
	}

	/**
	 * @return the counterCacheTtl
	 */
	public static Integer getCounterCacheTtl() {
		return Integer.parseInt(prop.getProperty("countercachettl", COUNTERCACHETTL));
	}

	/**
	 * @return the dsCacheSize
	 */
	public static Integer getDsCacheSize() {
		return  Integer.parseInt(prop.getProperty("dscachesize", DSCACHESIZE));
	}

	/**
	 * @return the dsCacheTtl
	 */
	public static Integer getDsCacheTtl() {
		return Integer.parseInt(prop.getProperty("dscachettl", DSCACHETTL));
	}

	/**
	 * @return the escapeChars
	 */
	public static Boolean getEscapeChars() {
		return Boolean.parseBoolean(prop.getProperty("escapechars", ESCAPECHARS));
	}

	/**
	 * @return the esxiCacheSize
	 */
	public static Integer getEsxiCacheSize() {
		return  Integer.parseInt(prop.getProperty("esxicachesize", ESXICACHESIZE));
	}

	/**
	 * @return the esxiCacheTtl
	 */
	public static Integer getEsxiCacheTtl() {
		return Integer.parseInt(prop.getProperty("esxicachettl", ESXICACHETTL));
	}

	/**
	 * @return the hriCacheSize
	 */
	public static Integer getHriCacheSize() {
		return Integer.parseInt(prop.getProperty("hricachesize", HRICACHESIZE));
	}

	/**
	 * @return the hriCacheTtl
	 */
	public static Integer getHriCacheTtl() {
		return Integer.parseInt(prop.getProperty("hricachettl", HRICACHETTL));
	}

	/**
	 * @return the interval
	 */
	public static Integer getInterval() {
		return Integer.parseInt(prop.getProperty("interval", INTERVAL));

	}

	/**
	 * @return the ipaddr
	 */
	public static String getIpaddr() {
		return ipaddr;
	}

	/**
	 * @return the maxConnections
	 */
	public static Integer getMaxConnections() {
		return Integer.parseInt(prop.getProperty("maxconnections", MAXCONNECTIONS));
	}

	/**
	 * @return the passwd
	 */
	public static String getPasswd() {
		return passwd;
	}

	/**
	 * @return the perfIdCacheSize
	 */
	public static Integer getPerfIdCacheSize() {
		return  Integer.parseInt(prop.getProperty("perfidcachesize", PERFIDCACHESIZE));
	}

	/**
	 * @return the perfIdCacheTtl
	 */
	public static Integer getPerfIdCacheTtl() {
		return Integer.parseInt(prop.getProperty("perfidcachettl", PERFIDCACHETTL));
	}

	/**
	 * @return the pidFile
	 */
	public static String getPidFile() {
		return pidFile;
	}

	/**
	 * @return the port
	 */
	public static Integer getPort() {
		return port;
	}

	/**
	 * @return the readTimeout
	 */
	public static Integer getReadTimeout() {
		return Integer.parseInt(prop.getProperty("readtimeout", READTIMEOUT));
	}

	/**
	 * @return the sdkUrl
	 */
	public static String getSdkUrl() {
		return sdkUrl;
	}

	/**
	 * @return the uname
	 */
	public static String getUname() {
		return uname;
	}

	/**
	 * @return the useUuid
	 */
	public static Boolean getUseUuid() {
		return Boolean.parseBoolean(prop.getProperty("useuuid", USEUUID));
	}

	/**
	 * @return the vmCacheSize
	 */
	public static Integer getVmCacheSize() {
		return  Integer.parseInt(prop.getProperty("vmcachesize", VMCACHESIZE));
	}

	/**
	 * @return the vmCacheTtl
	 */
	public static Integer getVmCacheTtl() {
		return Integer.parseInt(prop.getProperty("vmcachettl", VMCACHETTL));
	}

	public Configuration(String configFile) {

		Properties prop = new Properties();
		try {
			InputStream is = new FileInputStream(configFile);
			prop.load(is);
			if (uname == null) {
				uname = prop.getProperty("username");
			}
			if (passwd == null) {
				passwd = prop.getProperty("password");
			}
			if (sdkUrl == null) {
				sdkUrl = prop.getProperty("serviceurl");
			}
			if (ipaddr == null) {
				ipaddr = prop.getProperty("bindaddress");
			}
			if (port == null && prop.getProperty("listenport") != null) {
				port = Integer.parseInt(prop.getProperty("listenport"));
			}
			if (pidFile == null) {
				pidFile = prop.getProperty("pidfile");
			}

		} catch (IOException e) {
			LOG.info("There was a problem with the configuration parameters.");
		}
	}
}
