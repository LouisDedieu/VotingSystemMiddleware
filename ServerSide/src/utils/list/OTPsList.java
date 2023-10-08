package utils.list;

import model.user.OTP;

import java.util.HashMap;
import java.util.Map;

public class OTPsList {
    private static Map<String, OTP> otps;

    public OTPsList() {
        this.otps = new HashMap<>();
    }

    public void addOTP(OTP otp) {
        otps.put(otp.getStudentNumber(), otp);
    }

    public OTP getOTP(String studentNumber) {
        return otps.get(studentNumber);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, OTP> entry : otps.entrySet()) {
            stringBuilder.append(entry.getKey()).append(" ").append(entry.getValue().getOtpValue()).append("\n");
        }
        return stringBuilder.toString();
    }
}
