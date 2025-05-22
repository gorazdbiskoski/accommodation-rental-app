import axiosInstance from "../axios/axios.js";

export const countryRepository = {
    findAll: async () => {
        return await axiosInstance.get("/country")
    },
    findById: async (id) => {
        return await axiosInstance.get(`/country/${id}`)
    },
    save: async (data) => {
        return await axiosInstance.put("/country/save", data)
    },
    update: async (id, data) => {
        return await axiosInstance.post(`/country/update/${id}`, data)
    },
    delete: async (id) => {
        return await axiosInstance.delete(`/country/delete/${id}`)
    }

}