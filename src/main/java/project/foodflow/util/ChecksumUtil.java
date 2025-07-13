package project.foodflow.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Slf4j
@Component
public class ChecksumUtil {

    @Value("${app.secret.key:793ddabd7c83070cd1ac72877edd9d29}")
    private String secretKey;

    /**
     * Generate SHA256 checksum from providerUserId + password + secretKey
     * @param providerUserId User ID from provider (phone, email, etc.)
     * @param password User password
     * @return SHA256 checksum
     */
    public String generateChecksum(String providerUserId, String password) {
        try {
            String data = providerUserId + password + secretKey;
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
            
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not available", e);
        }
    }

    /**
     * Validate checksum
     * @param providerUserId User ID from provider
     * @param password User password
     * @param checksum Provided checksum to validate
     * @return true if checksum is valid
     */
    public boolean validateChecksum(String providerUserId, String password, String checksum) {
        String expectedChecksum = generateChecksum(providerUserId, password);
        log.debug("checksum generated: {}", expectedChecksum);
        log.debug("checksum provided: {}", checksum);
        return expectedChecksum.equals(checksum);
    }
} 