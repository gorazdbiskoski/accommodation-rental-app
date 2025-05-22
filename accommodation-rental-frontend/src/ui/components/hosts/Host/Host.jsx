import {Button} from "@mui/material";
import {useState} from "react";
import {EditHostDialog} from "../HostDialog/EditHostDialog.jsx";
import {DeleteHostDialog} from "../HostDialog/DeleteHostDialog.jsx";

export const Host = (props) => {
    const {host, countries, onUpdate, onDelete} = props;

    const [editHostDialog, setEditHostDialog] = useState(false);
    const [deleteHostDialog, setDeleteHostDialog] = useState(false);

    return (
        <tr key={host.id}>
            <td>{host.name}</td>
            <td>{host.surname}</td>
            <td>{host.country.name}</td>
            <td className="text-end">
                <Button
                    variant="outlined"
                    color="primary"
                    size="small"
                    style={{marginRight: '8px'}}
                    onClick={() => {setEditHostDialog(true)}}
                >
                    Edit
                </Button>
                <Button
                    variant="outlined"
                    color="error"
                    size="small"
                    onClick={() => {setDeleteHostDialog(true)}}
                >
                    Delete
                </Button>
            </td>

            <EditHostDialog
                open={editHostDialog}
                onClose={() => setEditHostDialog(false)}
                host={host}
                countries={countries}
                onEdit={onUpdate}
            />
            <DeleteHostDialog
                open={deleteHostDialog}
                onClose={() => setDeleteHostDialog(false)}
                host={host}
                onDelete={onDelete}
            />

        </tr>
    )
}