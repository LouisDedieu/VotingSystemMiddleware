package model;

public class OTP {
    private String studentNumber;
    private String otpValue;
    private boolean isUsed;

    public OTP(String studentNumber, String otpValue) {
        this.studentNumber = studentNumber;
        this.otpValue = otpValue;
        this.isUsed = false;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getOtpValue() {
        return otpValue;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void markAsUsed() {
        this.isUsed = true;
    }
}
