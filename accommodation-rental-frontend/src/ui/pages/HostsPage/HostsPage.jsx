import {Button, CircularProgress} from "@mui/material";
import {useState} from "react";
import {useHosts} from "../../../hooks/useHosts.js";
import {useCountries} from "../../../hooks/useCountries.js"
import {Hosts} from "../../components/hosts/Hosts/Hosts.jsx";
import {SaveHostDialog} from "../../components/hosts/HostDialog/SaveHostDialog.jsx";
import {SaveCountryDialog} from "../../components/countries/CountryDialog/SaveCountryDialog.jsx"

export const HostsPage = () => {

    const {hosts, loading, onSave, onUpdate, onDelete} = useHosts()
    const {countries} = useCountries();
    const [hostDialog, setHostDialog] = useState(false);

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
                            <h2 className="text-secondary fw-bold">Host Directory</h2>
                            <Button
                                variant="outlined"
                                color="primary"
                                size="small"
                                onClick={() => {setHostDialog(true)}}
                            >
                                Add Host
                            </Button>

                        </div>
                        <Hosts hosts={hosts} countries={countries} onUpdate={onUpdate} onDelete={onDelete} />

                        <SaveHostDialog
                            countries={countries}
                            open={hostDialog}
                            onClose={() => {setHostDialog(false)}}
                            onSave={onSave}
                        />
                    </>
                )}
            </div>
        </>
    )
}