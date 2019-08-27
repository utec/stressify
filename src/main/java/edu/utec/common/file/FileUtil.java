package edu.utec.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

	public static String readFileToString(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}

	public static String getPathFromWhereApplicationIsRunning() throws Exception {
		String path = null;
		try {
			path = new File(".").getCanonicalPath();
		} catch (IOException ex) {
			throw new Exception("Error when try to obtain general path of current application.", ex);
		}
		return path;
	}

	public static void inputStreamToFile(InputStream source, File dest) throws IOException {

		OutputStream outputStream = null;

		try {

			// write the inputStream to a FileOutputStream
			outputStream = new FileOutputStream(dest);

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = source.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (source != null) {
				try {
					source.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (outputStream != null) {
				try {
					// outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
		}

	}

}
