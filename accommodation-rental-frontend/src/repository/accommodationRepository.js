import axiosInstance from "../axios/axios.js";

export const accommodationRepository  = {
    findAll: async () => {
        return await axiosInstance.get(`/accommodation`)
    },
    findById: async (id) => {
        return await axiosInstance.get(`/accommodation/${id}`)
    },
    save: async (data) => {
        return await axiosInstance.put(`/accommodation/save`, data)
    },
    update: async (id, data) => {
        return await axiosInstance.post(`/accommodation/update/${id}`, data)
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/accommodation/delete/${id}`)
    }
}