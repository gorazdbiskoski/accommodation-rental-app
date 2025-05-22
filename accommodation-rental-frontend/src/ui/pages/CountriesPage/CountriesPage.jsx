import {Button, CircularProgress} from "@mui/material";
import {useCountries} from "../../../hooks/useCountries.js";
import {Countries} from "../../components/countries/Countries/Countries.jsx";
import {useState} from "react";
import {SaveCountryDialog} from "../../components/countries/CountryDialog/SaveCountryDialog.jsx";

export const CountriesPage = () => {

    const {countries, loading, onSave, onUpdate, onDelete} = useCountries();
    const [countryDialog, setCountryDialog] = useState(false);

    return (
        <>
            <div className="container mt-5">
                {loading && (
                    <div className="d-flex justify-content-center align-items-center h-100">
                        <CircularProgress color="secondary" />
                    </div>
                )}
                {!loading && (
                    <>
                        <div className="d-flex justify-content-between align-items-center mb-4">
                            <h2 className="text-secondary fw-bold">Country Directory</h2>
                                <Button
                                    variant="outlined"
                                    color="primary"
                                    size="small"
                                    onClick={() => {setCountryDialog(true)}}
                                >
                                    Add Country
                                </Button>

                        </div>
                        <Countries countries={countries} onUpdate={onUpdate} onDelete={onDelete} />

                        <SaveCountryDialog
                            open={countryDialog}
                            onClose={() => {setCountryDialog(false)}}
                            onSave={onSave}
                        />
                    </>
                )}
            </div>
        </>
    )
}