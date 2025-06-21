import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getSecurityCheckOne = (params) => {
    return getRequest('/securityCheck/getOne', params)
}
export const getSecurityCheckList = (params) => {
    return getRequest('/securityCheck/getByPage', params)
}
export const getSecurityCheckCount = (params) => {
    return getRequest('/securityCheck/count', params)
}
export const addSecurityCheck = (params) => {
    return postRequest('/securityCheck/insert', params)
}
export const editSecurityCheck = (params) => {
    return postRequest('/securityCheck/update', params)
}
export const addOrEditSecurityCheck = (params) => {
    return postRequest('/securityCheck/insertOrUpdate', params)
}
export const deleteSecurityCheck = (params) => {
    return postRequest('/securityCheck/delByIds', params)
}
export const getUserList = (params) => {
    return getRequest('/superUser/getUserList', params)
}