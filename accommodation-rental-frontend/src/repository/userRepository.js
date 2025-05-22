import axiosInstance from "../axios/axios.js";

export const userRepository = {
    register: async (data) => {
        return await axiosInstance.post("/auth/register", data);
    },
    login: async (data) => {
        return await axiosInstance.post("/auth/login", data);
    },
};