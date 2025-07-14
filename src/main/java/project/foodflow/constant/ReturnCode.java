package project.foodflow.constant;

public enum ReturnCode {
    SUCCESS(200, "success"),
    BAD_REQUEST(400, "fail"),
    UNAUTHORIZED(401, "unauthorized"),
    FORBIDDEN(403, "forbidden"),
    NOT_FOUND(404, "not_found"),
    ERROR(500, "error");

    private final int code;
    private final String status;

    ReturnCode(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
} 