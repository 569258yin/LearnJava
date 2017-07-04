package com.kevinyin.lio;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * ?????????????log4j
 * @author HexPOS
 *
 */
public class DebugLog {
	public static Logger logger;
	static {
		PropertyConfigurator.configure("log4j.properties");
		logger = Logger.getLogger("iSPOSBO");
		System.setErr(new PrintStream(new CustomOutputStream()));
	}
}

class CustomOutputStream extends OutputStream {
	StringBuffer toWrite = new StringBuffer();
	@Override
	public final void write(int b) throws IOException {
		if(b != 10 && b != 13) {
			toWrite = toWrite.append((char) b);
		} else if ( b == 10 || b == 13) {
			if (toWrite.length() > 0) {
				DebugLog.logger.error("!! " + toWrite.toString());
				toWrite.setLength(0);
			}
		}
	}
	
	@Override
	public final void write(byte[] b) throws IOException {
		DebugLog.logger.error("!! " + new String(b));
	}	
}