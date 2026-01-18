package id.co.chubb.core.util;

import org.apache.commons.codec.binary.Base64;

import java.nio.file.Files;
import java.nio.file.Path;

public class Base64Utils {

    public static String convertPdf(Path pdfPath) throws Exception {
        byte[] pdfBytes = Files.readAllBytes(pdfPath);
        Base64 base64 = new Base64();
        return new String(base64.encode(pdfBytes));
    }

    public static String base64Encode(String originalInput) {
        Base64 base64 = new Base64();
        return new String(base64.encode(originalInput.getBytes()));
    }

    public static String base64Decode(String encodeString) {
        Base64 base64 = new Base64();
        return new String(base64.decode(encodeString.getBytes()));
    }
}
