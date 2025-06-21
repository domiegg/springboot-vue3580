import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getSecurityIncidentsOne = (params) => {
    return getRequest('/securityIncidents/getOne', params)
}
export const getSecurityIncidentsList = (params) => {
    return getRequest('/securityIncidents/getByPage', params)
}
export const getSecurityIncidentsCount = (params) => {
    return getRequest('/securityIncidents/count', params)
}
export const addSecurityIncidents = (params) => {
    return postRequest('/securityIncidents/insert', params)
}
export const editSecurityIncidents = (params) => {
    return postRequest('/securityIncidents/update', params)
}
export const addOrEditSecurityIncidents = (params) => {
    return postRequest('/securityIncidents/insertOrUpdate', params)
}
export const deleteSecurityIncidents = (params) => {
    return postRequest('/securityIncidents/delByIds', params)
}