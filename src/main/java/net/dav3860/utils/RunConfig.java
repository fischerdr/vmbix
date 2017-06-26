package net.dav3860.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.dav3860.VmBix;

public class RunConfig {

	// Constants
	private static final String INTERVAL = "300";
	private static final String USEUUID = "false";
	private static final String MAXCONNECTIONS = "150";
	private static final String CONNECTTIMEOUT = "30000";
	private static final String READTIMEOUT = "30000";
	private static final String ESCAPECHARS = "false";
	private static final String VMCACHETTL = "15";
	private static final String VMCACHESIZE = "1000";
	private static final String ESXICACHETTL = "15";
	private static final String ESXICACHESIZE = "100";
	private static final String DSCACHETTL = "15";
	private static final String DSCACHESIZE = "100";
	private static final String PERFIDCACHETTL = "5";
	private static final String PERFIDCACHESIZE = "1000";
	private static final String COUNTERCACHETTL = "5";
	private static final String COUNTERCACHESIZE = "1000";
	private static final String HRICACHETTL = "15";
	private static final String HRICACHESIZE = "100";
	private static final String CLCACHETTL = "15";
	private static final String CLCACHESIZE = "100";

	static String sdkUrl;
	static String uname;
	static String passwd;
	static String ipaddr;
	static Integer port;
	static String pidFile;


	static Integer interval;
	static Boolean useUuid;
	static Integer maxConnections;
	static Integer connectTimeout;
	static Integer readTimeout;
	static Boolean escapeChars;
	static Integer vmCacheTtl;
	static Integer vmCacheSize;
	static Integer esxiCacheTtl;
	static Integer esxiCacheSize;
	static Integer dsCacheTtl;
	static Integer dsCacheSize;
	static Integer perfIdCacheTtl;
	static Integer perfIdCacheSize;
	static Integer counterCacheTtl;
	static Integer counterCacheSize;
	static Integer hriCacheTtl;
	static Integer hriCacheSize;
	static Integer clCacheTtl;
	static Integer clCacheSize;

	static final Logger LOG = LoggerFactory.getLogger(VmBix.class);
	private static final Properties prop = null;

	public RunConfig(String[] cmdLineArgs) {
		// create the parser
		CommandLineParser parser = new GnuParser();
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(buildCmdOpts(), cmdLineArgs);
			Option[] myOpts = line.getOptions();
			System.out.print(myOpts.length);
			for (int i = 0; i > myOpts.length; i++) {
				LOG.debug(myOpts[i].toString());
			}
			if (line.hasOption("help")) {
				// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Main", buildCmdOpts());
				methods();
			}
			if (!line.hasOption("configfile") || (!line.hasOption("serviceurl"))) {
				// automatically generate the help statement
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Main", buildCmdOpts());
			}
			LOG.debug(line.getOptionValue("configfile"));
			if (line.hasOption("configfile")) {
				Properties prop = new Properties();
				InputStream is = new FileInputStream(line.getOptionValue("configfile"));
				prop.load(is);
			}
			if (uname == null) {
				uname = prop.getProperty("username", line.getOptionValue("username"));
			}
			if (passwd == null) {
				passwd = prop.getProperty("password", line.getOptionValue("password"));
			}
			if (sdkUrl == null) {
				sdkUrl = prop.getProperty("serviceurl", line.getOptionValue("serviceurl"));
			}
			if (ipaddr == null) {
				ipaddr = prop.getProperty("bindaddress", line.getOptionValue("bindaddress"));
			}
			if (port == null && prop.getProperty("listenport") != null) {
				port = Integer.parseInt(prop.getProperty("listenport"));
			}
			if (pidFile == null) {
				pidFile = prop.getProperty("pidfile", line.getOptionValue("pidfile"));
			}

			// Common parameters
			interval = Integer.parseInt(prop.getProperty("interval", INTERVAL));
			maxConnections = Integer.parseInt(prop.getProperty("maxconnections", MAXCONNECTIONS));
			connectTimeout = Integer.parseInt(prop.getProperty("connecttimeout", CONNECTTIMEOUT));
			readTimeout = Integer.parseInt(prop.getProperty("readtimeout", READTIMEOUT));
			useUuid = Boolean.parseBoolean(prop.getProperty("useuuid", USEUUID));
			escapeChars = Boolean.parseBoolean(prop.getProperty("escapechars", ESCAPECHARS));

			// Caching parameters
			vmCacheTtl = Integer.parseInt(prop.getProperty("vmcachettl", VMCACHETTL));
			esxiCacheTtl = Integer.parseInt(prop.getProperty("esxicachettl", ESXICACHETTL));
			dsCacheTtl = Integer.parseInt(prop.getProperty("dscachettl", DSCACHETTL));
			perfIdCacheTtl = Integer.parseInt(prop.getProperty("perfidcachettl", PERFIDCACHETTL));
			counterCacheTtl = Integer.parseInt(prop.getProperty("countercachettl", COUNTERCACHETTL));
			hriCacheTtl = Integer.parseInt(prop.getProperty("hricachettl", HRICACHETTL));
			clCacheTtl = Integer.parseInt(prop.getProperty("clcachettl", CLCACHETTL));

			vmCacheSize = Integer.parseInt(prop.getProperty("vmcachesize", VMCACHESIZE));
			esxiCacheSize = Integer.parseInt(prop.getProperty("esxicachesize", ESXICACHESIZE));
			dsCacheSize = Integer.parseInt(prop.getProperty("dscachesize", DSCACHESIZE));
			perfIdCacheSize = Integer.parseInt(prop.getProperty("perfidcachesize", PERFIDCACHESIZE));
			counterCacheSize = Integer.parseInt(prop.getProperty("countercachesize", COUNTERCACHESIZE));
			hriCacheSize = Integer.parseInt(prop.getProperty("hricachesize", HRICACHESIZE));
			clCacheSize = Integer.parseInt(prop.getProperty("clcachesize", CLCACHESIZE));

		} catch (ParseException exp) {
			// oops, something went wrong
			System.err.println("Parsing failed.  Reason: " + exp.getMessage());
		} catch (Exception exp) {
			// oops, something went wrong
			System.err.println("Failed.  Reason: " + exp.getMessage());
		}

	}

	private static Options buildCmdOpts() {
		Option help = new Option("help", "print this message");
		Option version = new Option("version", "print the version information and exit");
		Options options = new Options();
		options.addOption(help);
		options.addOption(version);
		options.addOption("c", "config", false, "Location and name of config file");
		options.addOption("u", "username", false, "{-u|--username} \u001B[4musername\u001B[0m");
		options.addOption("p", "password", false, "{-p|--password} \u001B[4mpassword\u001B[0m");
		options.addOption("s", "serviceurl", false, "{-s|--serviceurl} \u001B[4mhttp[s]://serveraddr/sdk\u001B[0m");
		options.addOption("b", false, "Bind address");
		options.addOption("P", "port", false, "{-P|--port} \u001B[4mlistenPort\u001B[0m");
		options.addOption("f", "pid", false, "[-f|--pid pidfile]");

		return options;
	}

	static void methods() {
		System.out.println("Available Zabbix Items methods :                                           \n"
				+ "vmbix.ping                                                  \n"
				+ "vmbix.version                                               \n"
				+ "vmbix.stats[threads]                                        \n"
				+ "vmbix.stats[queue]                                          \n"
				+ "vmbix.stats[requests]                                       \n"
				+ "vmbix.stats[cachesize,(vm|esxi|ds|perf|counter|hri|cluster)]\n"
				+ "vmbix.stats[hitrate,(vm|esxi|ds|perf|counter|hri|cluster)]  \n"
				+ "about                                                       \n"
				+ "cluster.discovery                                           \n"
				+ "cluster.cpu[name,free]                                      \n"
				+ "cluster.cpu[name,total]                                     \n"
				+ "cluster.cpu[name,usage]                                     \n"
				+ "cluster.cpu.num[name,threads]                               \n"
				+ "cluster.cpu.num[nane,cores]                                 \n"
				+ "cluster.mem[name,free]                                      \n"
				+ "cluster.mem[name,total]                                     \n"
				+ "cluster.mem[name,usage]                                     \n"
				+ "cluster.hosts[name,online]                                  \n"
				+ "cluster.hosts[name,maint]                                   \n"
				+ "cluster.hosts[name,total]                                   \n"
				+ "datacenter.discovery                                        \n"
				+ "datacenter.status[name,(overall|config)]                    \n"
				+ "datastore.discovery                                         \n"
				+ "datastore.local[(uuid|name)]                                \n"
				+ "datastore.size[(uuid|name),free]                            \n"
				+ "datastore.size[(uuid|name),total]                           \n"
				+ "datastore.size[(uuid|name),provisioned]                     \n"
				+ "datastore.size[(uuid|name),uncommitted]                     \n"
				+ "esx.connection[(uuid|name)]                                 \n"
				+ "esx.uptime[(uuid|name)]                                     \n"
				+ "esx.cpu.load[(uuid|name),cores]                             \n"
				+ "esx.cpu.load[(uuid|name),total]                             \n"
				+ "esx.cpu.load[(uuid|name),used]                              \n"
				+ "esx.discovery                                               \n"
				+ "esx.maintenance[(uuid|name)]                                \n"
				+ "esx.memory[(uuid|name),total]                               \n"
				+ "esx.memory[(uuid|name),used]                                \n"
				+ "esx.path[(uuid|name),active]                                \n"
				+ "esx.path[(uuid|name),dead]                                  \n"
				+ "esx.path[(uuid|name),disabled]                              \n"
				+ "esx.path[(uuid|name),standby]                               \n"
				+ "esx.status[(uuid|name)]                                     \n"
				+ "esx.vms.count[(uuid|name)]                                  \n"
				+ "esx.vms.memory[(uuid|name),active]                          \n"
				+ "esx.vms.memory[(uuid|name),ballooned]                       \n"
				+ "esx.vms.memory[(uuid|name),compressed]                      \n"
				+ "esx.vms.memory[(uuid|name),consumed]                        \n"
				+ "esx.vms.memory[(uuid|name),overheadConsumed]                \n"
				+ "esx.vms.memory[(uuid|name),private]                         \n"
				+ "esx.vms.memory[(uuid|name),shared]                          \n"
				+ "esx.vms.memory[(uuid|name),swapped]                         \n"
				+ "esx.counter[(uuid|name),counter,[instance,interval]]        \n"
				+ "esx.counter.discovery[(uuid|name),counter,[interval]]       \n"
				+ "esx.counter.list[(uuid|name)]                               \n"
				+ "event.latest[*]                                             \n"
				+ "vm.consolidation[(uuid|name),needed]                        \n"
				+ "vm.cpu.load[(uuid|name),cores]                              \n"
				+ "vm.cpu.load[(uuid|name),total]                              \n"
				+ "vm.cpu.load[(uuid|name),used]                               \n"
				+ "vm.discovery[*]                                             \n"
				+ "vm.discovery.full[*]                                        \n"
				+ "vm.folder[(uuid|name)]                                      \n"
				+ "vm.uptime[(uuid|name)]                                      \n"
				+ "vm.name[(uuid|name)]                                        \n"
				+ "vm.annotation[(uuid|name)]                                  \n"
				+ "vm.guest.disk.discovery[(uuid|name)]                        \n"
				+ "vm.guest.disk.capacity[(uuid|name),disk]                    \n"
				+ "vm.guest.disk.free[(uuid|name),disk]                        \n"
				+ "vm.guest.ip[(uuid|name)]                                    \n"
				+ "vm.guest.(uuid|name)[(uuid|name)]                           \n"
				+ "vm.guest.os[(uuid|name)]                                    \n"
				+ "vm.guest.os.short[(uuid|name)]                              \n"
				+ "vm.guest.tools.mounted[(uuid|name)]                         \n"
				+ "vm.guest.tools.running[(uuid|name)]                         \n"
				+ "vm.guest.tools.version[(uuid|name)]                         \n"
				+ "vm.host[(uuid|name)]                                        \n"
				+ "vm.memory[(uuid|name),active]                               \n"
				+ "vm.memory[(uuid|name),ballooned]                            \n"
				+ "vm.memory[(uuid|name),compressed]                           \n"
				+ "vm.memory[(uuid|name),consumed]                             \n"
				+ "vm.memory[(uuid|name),overheadConsumed]                     \n"
				+ "vm.memory[(uuid|name),private]                              \n"
				+ "vm.memory[(uuid|name),shared]                               \n"
				+ "vm.memory[(uuid|name),swapped]                              \n"
				+ "vm.memory[(uuid|name),total]                                \n"
				+ "vm.counter[(uuid|name),counter,[instance,interval]]         \n"
				+ "vm.counter.discovery[(uuid|name),counter,[interval]]        \n"
				+ "vm.counter.list[(uuid|name)]                                \n"
				+ "vm.powerstate[(uuid|name)]                                  \n"
				+ "vm.status[(uuid|name)]                                      \n"
				+ "vm.storage.committed[(uuid|name)]                           \n"
				+ "vm.storage.uncommitted[(uuid|name)]                         \n"
				+ "vm.storage.unshared[(uuid|name)]                            \n"
				+ "vm.snapshot[(uuid|name)]                                    \n");
	}
	/**
	 * @return the pidFile
	 */
	public  String getPidFile() {
		return pidFile;
	}

	public Integer getVmCacheSize() {
		return vmCacheSize;
	}

	/**
	 * @return the vmCacheTtl
	 */
	public  Integer getVmCacheTtl() {
		return vmCacheTtl;
	}

	/**
	 * @return the sdkUrl
	 */
	public  String getSdkUrl() {
		return sdkUrl;
	}

	/**
	 * @return the uname
	 */
	public  String getUname() {
		return uname;
	}

	/**
	 * @return the passwd
	 */
	public  String getPasswd() {
		return passwd;
	}

	/**
	 * @return the ipaddr
	 */
	public  String getIpaddr() {
		return ipaddr;
	}

	/**
	 * @return the port
	 */
	public  Integer getPort() {
		return port;
	}

	/**
	 * @return the useUuid
	 */
	public  Boolean getUseUuid() {
		return useUuid;
	}

	/**
	 * @return the maxConnections
	 */
	public  Integer getMaxConnections() {
		return maxConnections;
	}

	/**
	 * @return the connectTimeout
	 */
	public  Integer getConnectTimeout() {
		return connectTimeout;
	}

	/**
	 * @return the readTimeout
	 */
	public  Integer getReadTimeout() {
		return readTimeout;
	}

	/**
	 * @return the esxiCacheTtl
	 */
	public  Integer getEsxiCacheTtl() {
		return esxiCacheTtl;
	}

	/**
	 * @return the esxiCacheSize
	 */
	public  Integer getEsxiCacheSize() {
		return esxiCacheSize;
	}

	/**
	 * @return the dsCacheTtl
	 */
	public  Integer getDsCacheTtl() {
		return dsCacheTtl;
	}

	/**
	 * @return the dsCacheSize
	 */
	public  Integer getDsCacheSize() {
		return dsCacheSize;
	}

	/**
	 * @return the perfIdCacheTtl
	 */
	public  Integer getPerfIdCacheTtl() {
		return perfIdCacheTtl;
	}

	/**
	 * @return the perfIdCacheSize
	 */
	public  Integer getPerfIdCacheSize() {
		return perfIdCacheSize;
	}

	/**
	 * @return the counterCacheTtl
	 */
	public  Integer getCounterCacheTtl() {
		return counterCacheTtl;
	}

	/**
	 * @return the counterCacheSize
	 */
	public  Integer getCounterCacheSize() {
		return counterCacheSize;
	}

	/**
	 * @return the hriCacheTtl
	 */
	public  Integer getHriCacheTtl() {
		return hriCacheTtl;
	}

	/**
	 * @return the hriCacheSize
	 */
	public  Integer getHriCacheSize() {
		return hriCacheSize;
	}

	/**
	 * @return the clCacheTtl
	 */
	public  Integer getClCacheTtl() {
		return clCacheTtl;
	}

	/**
	 * @return the clCacheSize
	 */
	public  Integer getClCacheSize() {
		return clCacheSize;
	}

	/**
	 * @return the interval
	 */
	public static Integer getInterval() {
		return interval;
	}

	/**
	 * @return the escapeChars
	 */
	public static Boolean getEscapeChars() {
		return escapeChars;
	}


}
