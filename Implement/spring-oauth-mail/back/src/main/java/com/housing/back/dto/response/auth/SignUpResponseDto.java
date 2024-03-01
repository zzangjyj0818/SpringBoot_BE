package com.housing.back.dto.response.auth;

import com.housing.back.common.ResponseCode;
import com.housing.back.common.ResponseMessage;
import com.housing.back.dto.response.ResponseDto;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {
    private SignUpResponseDto(){
        super();
    }

    public static ResponseEntity<SignUpResponseDto> success() {
        SignUpResponseDto responseBody = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> duplicateId() {
        ResponseDto responseBody = new ResponseDto(
                ResponseCode.DUPLICATE_ID, ResponseMessage.DUPLICATE_ID
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(
                ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL
        );
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
