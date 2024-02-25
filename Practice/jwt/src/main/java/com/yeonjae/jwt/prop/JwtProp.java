package com.yeonjae.jwt.prop;

// application.properties에 정의해놓은 값을 가져와서
// 사용함.

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("com.yeonjae.jwt")
public class JwtProp {
    private String secretKey;
}
