import axios, { AxiosResponse } from "axios";
import { IdCheckRequestDto, emailCertificationRequestDto } from "./request/auth";
import { ResponseDto } from "./response";
import { IdCheckResponseDto, emailCertificationResponseDto } from "./response/auth";

const responseHandler = <T> (response: AxiosResponse<any, any>) => {
    const responseBody: T = response.data;
    return responseBody;
};

const errorHanlder = (err: any) => {
    if(!err.response ||  !err.response.data) return null;
    const responseBody: ResponseDto = err.response.data;
    return responseBody;
}

const DOMAIN = 'http://localhost:4040';

const API_DOMAIN = `${DOMAIN}/api/v1`;

const ID_CHECK_URL = () => `${API_DOMAIN}/auth/id-check`;
const EAMIL_CERTIFICATION_URL= () => `${API_DOMAIN}/auth/email-certification`;

export const idCheckRequest = async (requestBody: IdCheckRequestDto) => {
    const result = await axios.post(ID_CHECK_URL(), requestBody)
        .then(responseHandler<IdCheckResponseDto>)
        .catch(errorHanlder)
    return result;
};

export const emailCertificationRequest = async (requestBody : emailCertificationRequestDto) => {
    const result = await axios.post(EAMIL_CERTIFICATION_URL(), requestBody)
        .then(responseHandler<emailCertificationResponseDto>)
        .catch(errorHanlder)
    return result;
}