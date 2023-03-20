package com.r2comms.r2copy.rclone;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class Cmd {

	private StringBuffer buffer;
	private Process process;
	private BufferedReader bufferedReader;
	private StringBuffer readBuffer;
	
	
	public String inputCommand(String cmd) {
		buffer = new StringBuffer();
		
		buffer.append("cmd.exe ");
		buffer.append("/c ");
		buffer.append(cmd);
		
		return buffer.toString();
	}
	
	// @Async
	public String execCommand(String cmd) {
		log.info(cmd);
		
		try {
			process = Runtime.getRuntime().exec(cmd);
			bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = null;
			
			readBuffer = new StringBuffer() ;
			
			
			while ((line = bufferedReader.readLine()) != null) {
				
				readBuffer.append(line);
				readBuffer.append("\n");
				
			}
			
			// log.info(readBuffer.toString());
			return readBuffer.toString();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}
}
