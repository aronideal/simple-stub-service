package net.roonten.stub.utils;

import java.io.IOException;
import java.io.OutputStream;

public final class IOUtils {

	public static void output(byte[] b, OutputStream out) throws IOException {
		out.write(b);
	}
}
