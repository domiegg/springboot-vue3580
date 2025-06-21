import { getRequest, postRequest, putRequest, postBodyRequest, getNoAuthRequest, postNoAuthRequest } from '@/libs/axios';

export const getNaturalResourcesOne = (params) => {
    return getRequest('/naturalResources/getOne', params)
}
export const getNaturalResourcesList = (params) => {
    return getRequest('/naturalResources/getByPage', params)
}
export const getNaturalResourcesCount = (params) => {
    return getRequest('/naturalResources/count', params)
}
export const addNaturalResources = (params) => {
    return postRequest('/naturalResources/insert', params)
}
export const editNaturalResources = (params) => {
    return postRequest('/naturalResources/update', params)
}
export const addOrEditNaturalResources = (params) => {
    return postRequest('/naturalResources/insertOrUpdate', params)
}
export const deleteNaturalResources = (params) => {
    return postRequest('/naturalResources/delByIds', params)
}