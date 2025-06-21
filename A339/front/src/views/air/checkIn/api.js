import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getCheckInOne = (params) => {
    return getRequest('/checkIn/getOne', params)
}
export const getCheckInList = (params) => {
    return getRequest('/checkIn/getByPage', params)
}
export const getCheckInCount = (params) => {
    return getRequest('/checkIn/count', params)
}
export const addCheckIn = (params) => {
    return postRequest('/checkIn/insert', params)
}
export const editCheckIn = (params) => {
    return postRequest('/checkIn/update', params)
}
export const addOrEditCheckIn = (params) => {
    return postRequest('/checkIn/insertOrUpdate', params)
}
export const deleteCheckIn = (params) => {
    return postRequest('/checkIn/delByIds', params)
}
export const getUserList = (params) => {
    return getRequest('/superUser/getUserList', params)
}
export const getFlightList = (params) => {
    return getRequest('/flight/getAll', params)
}