import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getFlightOne = (params) => {
    return getRequest('/flight/getOne', params)
}
export const getFlightList = (params) => {
    return getRequest('/flight/getByPage', params)
}
export const getFlightCount = (params) => {
    return getRequest('/flight/count', params)
}
export const addFlight = (params) => {
    return postRequest('/flight/insert', params)
}
export const editFlight = (params) => {
    return postRequest('/flight/update', params)
}
export const addOrEditFlight = (params) => {
    return postRequest('/flight/insertOrUpdate', params)
}
export const deleteFlight = (params) => {
    return postRequest('/flight/delByIds', params)
}