import {useCallback, useEffect, useState} from "react";
import {countryRepository} from "../repository/countryRepository.js";

const initialState = {
    countries: [],
    loading: true,
}
export const useCountries = () => {
    const [state, setState] = useState(initialState)

    const fetchCountries = useCallback(() => {
        countryRepository
            .findAll()
            .then(response => {
                setState({
                    countries: response.data,
                    loading: false,
                })
            })
            .catch(error => console.log(error))
    }, [])

    const onSave = useCallback((data) => {
        countryRepository
            .save(data)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    const onUpdate = useCallback((id, data) => {
        countryRepository
            .update(id, data)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    const onDelete = useCallback((id) => {
        countryRepository
            .delete(id)
            .then(() => {
                fetchCountries()
            })
            .catch(error => console.log(error))
    }, [fetchCountries])

    useEffect(() => {
        fetchCountries()
    }, [fetchCountries]);

    return {...state, onSave: onSave, onUpdate: onUpdate, onDelete: onDelete};
}