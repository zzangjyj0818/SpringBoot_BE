package com.housing.back.common;

public interface ResponseCode {
    // Response Code : 200
    String SUCCESS = "SU";

    String VALIDATION_FAIL = "VF";
    String DUPLICATE_ID = "DI";

    String SIGN_IN_FAIL = "SF";
    String CERTIFICATION_FAIL = "CF";

    String MAIL_FAIL = "MF";
    String DATABASE_ERROR = "DBE";
}
