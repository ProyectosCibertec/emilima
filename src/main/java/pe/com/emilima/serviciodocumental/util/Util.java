package pe.com.emilima.serviciodocumental.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

import static java.util.stream.Collectors.joining;

public class Util {
	public static final String FILE_UPLOAD_DIRECTORY = System.getProperty("user.home") + File.separator + "file-uploads" + File.separator;
	
	public static String readInputStream(InputStream stream) {
		return new BufferedReader(new InputStreamReader(stream)).lines().collect(joining("\n"));
	}
}
