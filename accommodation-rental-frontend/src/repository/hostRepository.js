import axiosInstance from "../axios/axios.js";

export const hostRepository = {
    findAll: async () => {
        return await axiosInstance.get(`/host`)
    },
    findById: async (id) => {
        return await axiosInstance.get(`/host/${id}`)
    },
    save: async (data) => {
        return await axiosInstance.put(`/host/save`, data)
    },
    update: async (id, data) => {
        return await axiosInstance.post(`/host/update/${id}`, data)
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/host/delete/${id}`)
    }
}