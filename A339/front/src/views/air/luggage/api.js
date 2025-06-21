import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getLuggageOne = (params) => {
    return getRequest('/luggage/getOne', params)
}
export const getLuggageList = (params) => {
    return getRequest('/luggage/getByPage', params)
}
export const getLuggageCount = (params) => {
    return getRequest('/luggage/count', params)
}
export const addLuggage = (params) => {
    return postRequest('/luggage/insert', params)
}
export const editLuggage = (params) => {
    return postRequest('/luggage/update', params)
}
export const addOrEditLuggage = (params) => {
    return postRequest('/luggage/insertOrUpdate', params)
}
export const deleteLuggage = (params) => {
    return postRequest('/luggage/delByIds', params)
}
export const getUserList = (params) => {
    return getRequest('/superUser/getUserList', params)
}