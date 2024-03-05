import axios, { AxiosResponse } from "axios";
import { CheckCertificationRequestDto, IdCheckRequestDto, SignInRequestDto, SignUpRequestDto, emailCertificationRequestDto } from "./request/auth";
import { ResponseDto } from "./response";
import { CheckCertificationResponseDto, IdCheckResponseDto, SignInResponseDto, SignUpResponseDto, emailCertificationResponseDto } from "./response/auth";

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
const CHECK_CERTIFICATION_URL = () => `${API_DOMAIN}/auth/check-certification`;
const SIGN_UP_URL = () => `${API_DOMAIN}/auth/sign-up`;
const SIGN_IN_URL = () => `${API_DOMAIN}/auth/sign-in`;

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

export const checkCertificationRequest = async (requestBody : CheckCertificationRequestDto) => {
        const result = await axios.post(CHECK_CERTIFICATION_URL(), requestBody)
        .then(responseHandler<CheckCertificationResponseDto>)
        .catch(errorHanlder)
    return result;
}

export const signUpRequest = async (requestBody : SignUpRequestDto) => {
        const result = await axios.post(SIGN_UP_URL(), requestBody)
        .then(responseHandler<SignUpResponseDto>)
        .catch(errorHanlder)
    return result;
}

export const signInRequest = async (requestBody : SignInRequestDto) => {
        const result = await axios.post(SIGN_IN_URL(), requestBody)
        .then(responseHandler<SignInResponseDto>)
        .catch(errorHanlder)
    return result;
}