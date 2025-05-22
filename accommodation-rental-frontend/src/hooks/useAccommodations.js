import {useCallback, useEffect, useState} from "react";
import {accommodationRepository} from "../repository/accommodationRepository.js";

const initialState = {
    accommodations: [],
    loading: true
}

export const useAccommodations = () => {
    const [state, setState] = useState(initialState)

    const fetchAccommodations = useCallback(() => {
        accommodationRepository
            .findAll()
            .then(response => {
                setState({
                    accommodations: response.data,
                    loading: false,
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onSave = useCallback((data) => {
        accommodationRepository
            .save(data)
            .then(() => {
                fetchAccommodations()
            })
            .catch(error => console.log(error))
    }, [fetchAccommodations])

    const onUpdate = useCallback((id, data) => {
        accommodationRepository
            .update(id, data)
            .then(() => {
                fetchAccommodations()
            })
            .catch(error => console.log(error))
    }, [fetchAccommodations])

    const onDelete = useCallback((id) => {
        accommodationRepository
            .delete(id)
            .then(() => {
                fetchAccommodations()
            })
            .catch(error => console.log(error))
    }, [fetchAccommodations])

    useEffect(() => {
        fetchAccommodations()
    }, [fetchAccommodations]);

    return {...state, onSave: onSave, onUpdate: onUpdate, onDelete: onDelete};
}