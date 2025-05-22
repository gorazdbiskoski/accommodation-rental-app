import {useCallback, useEffect, useState} from "react";
import {hostRepository} from "../repository/hostRepository.js";

const initialState = {
    hosts: [],
    loading: true,
}

export const useHosts = () => {
    const [state, setState] = useState(initialState)

    const fetchHosts = useCallback(() => {
        hostRepository
            .findAll()
            .then(response => {
                setState({
                    hosts: response.data,
                    loading: false
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onSave = useCallback((data) => {
        hostRepository
            .save(data)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    const onUpdate = useCallback((id, data) => {
        hostRepository
            .update(id, data)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    const onDelete = useCallback((id) => {
        hostRepository
            .delete(id)
            .then(() => {
                fetchHosts()
            })
            .catch(error => console.log(error))
    }, [fetchHosts])

    useEffect(() => {
        fetchHosts()
    }, [fetchHosts]);

    return {...state, onSave: onSave, onUpdate: onUpdate, onDelete: onDelete}
}