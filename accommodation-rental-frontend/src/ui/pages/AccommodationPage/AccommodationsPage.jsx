import {Button, CircularProgress} from "@mui/material";
import {useState} from "react";
import {useHosts} from "../../../hooks/useHosts.js";
import {SaveAccommodationDialog} from "../../components/accommodations/AccommodationDialog/SaveAccommodationDialog.jsx"
import {useAccommodations} from "../../../hooks/useAccommodations.js";
import {Accommodations} from "../../components/accommodations/Accommodations/Accommodations.jsx";

export const AccommodationsPage = () => {

    const {accommodations, loading, onSave, onUpdate, onDelete} = useAccommodations()
    const {hosts} = useHosts();
    const [accommodationDialog, setAccommodationDialog] = useState(false);

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
                                onClick={() => {setAccommodationDialog(true)}}
                            >
                                Add Accommodation
                            </Button>

                        </div>
                        <Accommodations accommodations={accommodations} hosts={hosts} onUpdate={onUpdate} onDelete={onDelete} />

                        <SaveAccommodationDialog
                            hosts={hosts}
                            open={accommodationDialog}
                            onClose={() => {setAccommodationDialog(false)}}
                            onSave={onSave}
                        />
                    </>
                )}
            </div>
        </>
    )
}