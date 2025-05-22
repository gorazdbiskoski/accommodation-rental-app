import {Button} from "@mui/material";
import {useState} from "react";
import {EditAccommodationDialog} from "../AccommodationDialog/EditAccommodationDialog.jsx";
import {DeleteAccommodationDialog} from "../AccommodationDialog/DeleteAccommodationDialog.jsx";

export const Accommodation = (props) => {
    const {accommodation, hosts, onUpdate, onDelete} = props;

    const [editAccommodationDialog, setEditAccommodationDialog] = useState(false);
    const [deleteAccommodationDialog, setDeleteAccommodationDialog] = useState(false);

    return (
        <tr key={accommodation.id}>
            <td>{accommodation.name}</td>
            <td>{accommodation.category}</td>
            <td>{accommodation.host.name}</td>
            <td>{accommodation.numRooms}</td>
            <td>{accommodation.isAvailable}</td>
            <td className="text-end">
                <Button
                    variant="outlined"
                    color="primary"
                    size="small"
                    style={{marginRight: '8px'}}
                    onClick={() => {setEditAccommodationDialog(true)}}
                >
                    Edit
                </Button>
                <Button
                    variant="outlined"
                    color="error"
                    size="small"
                    onClick={() => {setDeleteAccommodationDialog(true)}}
                >
                    Delete
                </Button>
            </td>

            <EditAccommodationDialog
                open={editAccommodationDialog}
                onClose={() => setEditAccommodationDialog(false)}
                accommodation={accommodation}
                hosts={hosts}
                onEdit={onUpdate}
            />
            <DeleteAccommodationDialog
                open={deleteAccommodationDialog}
                onClose={() => setDeleteAccommodationDialog(false)}
                accommodation={accommodation}
                onDelete={onDelete}
            />

        </tr>
    )
}