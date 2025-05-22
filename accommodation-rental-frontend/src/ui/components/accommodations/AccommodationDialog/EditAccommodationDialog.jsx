import React, {useState} from 'react';
import {
    Button,
    Dialog,
    DialogActions,
    DialogContent,
    DialogTitle, FormControl, InputLabel, MenuItem, Select,
    TextField
} from "@mui/material";
import {useCategories} from "../../../../hooks/useCategories.js";


export const EditAccommodationDialog = ({accommodation, hosts , open, onClose, onEdit}) => {
    const [formData, setFormData] = useState({
        "name": accommodation.name,
        "category": accommodation.category,
        "host": accommodation.host.id,
        "numRooms": accommodation.numRooms,
        "isAvailable": accommodation.isAvailable,
    });

    const categories = useCategories();

    const handleChange = (event) => {
        const {name, value} = event.target;
        setFormData({...formData, [name]: value});
    };

    const handleSubmit = () => {
        onEdit(accommodation.id, formData);
        setFormData(formData);
        onClose();
        console.log(formData)
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
                    label="NumRooms"
                    name="numRooms"
                    type="number"
                    value={formData.numRooms}
                    onChange={handleChange}
                    fullWidth
                />
                <FormControl fullWidth margin="dense">
                    <InputLabel>Category</InputLabel>
                    <Select
                        name="category"
                        value={formData.category}
                        onChange={handleChange}
                        label="Category"
                        variant="outlined">
                        {categories.map((category) => (
                            <MenuItem key={category} value={category}>{category}</MenuItem>
                        ))}
                    </Select>
                </FormControl>
                <FormControl fullWidth margin="dense">
                    <InputLabel>Host</InputLabel>
                    <Select
                        name="host"
                        value={formData.host}
                        onChange={handleChange}
                        label="Host"
                        variant="outlined">
                        {hosts.map((host) => (
                            <MenuItem key={host.id} value={host.id}>{host.name}</MenuItem>
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