import {Button} from "@mui/material";
import {useState} from "react";
import {EditCountryDialog} from "../CountryDialog/EditCountryDialog.jsx";
import {DeleteCountryDialog} from "../CountryDialog/DeleteCountryDialog.jsx";

export const Country = (props) => {
    const {country, onUpdate, onDelete} = props;

    const [editCountryDialog, setEditCountryDialog] = useState(false);
    const [deleteCountryDialog, setDeleteCountryDialog] = useState(false);

    return (
        <tr key={country.id}>
            <td>{country.name}</td>
            <td>{country.continent}</td>
                <td className="text-end">
                    <Button
                        variant="outlined"
                        color="primary"
                        size="small"
                        style={{marginRight: '8px'}}
                        onClick={() => {setEditCountryDialog(true)}}
                    >
                        Edit
                    </Button>
                    <Button
                        variant="outlined"
                        color="error"
                        size="small"
                        onClick={() => {setDeleteCountryDialog(true)}}
                    >
                        Delete
                    </Button>
                </td>

            <EditCountryDialog
                open={editCountryDialog}
                onClose={() => setEditCountryDialog(false)}
                country={country}
                onEdit={onUpdate}
            />
            <DeleteCountryDialog
                open={deleteCountryDialog}
                onClose={() => setDeleteCountryDialog(false)}
                country={country}
                onDelete={onDelete}
            />

        </tr>
    )
}