import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle, FormControl, InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";


export const EditHostDialog = ({host, countries, open, onClose, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": host.name,
        "surname": host.surname,
        "country": host.country.id,
    });

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(host.id, formData);
        setFormData(formData);
        onClose();
    };

    return (
        <Dialog open={open} onClose={onClose}>
            <DialogTitle>Edit Host</DialogTitle>
            <DialogContent>
                <TextField
                    margin="dense"
                    label="Name"
                    name="name"
                    value={formData.name}
                    onChange={handleChange}
                    fullWidth
                />
                <TextField
                    margin="dense"
                    label="Surname"
                    name="surname"
                    value={formData.surname}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Country</InputLabel>
                    <Select
                        name="country"
                        value={formData.country}
                        onChange={handleChange}
                        label="Country"
                        variant="outlined">
                        {countries.map((country) => (
                            <MenuItem key={country.id} value={country.id}>{country.name}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
            </DialogContent>
            <DialogActions>
                <Button onClick={onClose}>Cancel</Button>
                <Button onClick={handleSubmit} variant="contained" color="primary">Save</Button>
            </DialogActions>
        </Dialog>
    );
};