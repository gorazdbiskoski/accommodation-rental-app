import {Box, Button, Stack, Typography} from "@mui/material";

export const HomePage = () => {
    return (
        <Box
            sx={{
                minHeight: '100vh',
                minWidth: '1200px',
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
                alignItems: 'center',
                textAlign: 'center',
                bgcolor: '#f9f9f9',
                px: 2
            }}
        >
            <Typography variant="h3" fontWeight="bold" gutterBottom>
                Welcome to Accommodation Rental
            </Typography>
            <Typography variant="h6" color="text.secondary" maxWidth="600px" mb={4}>
                Find out the perfect accommodation for you.
            </Typography>
            <Stack direction={{ xs: 'column', sm: 'row' }} spacing={2}>
                <Button variant="contained" color="primary" size="large" href="/accommodations">
                    Browse Accommodations
                </Button>
                <Button variant="outlined" color="primary" size="large" href="/login">
                    Login
                </Button>
            </Stack>
        </Box>
    );
};