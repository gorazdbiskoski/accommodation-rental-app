import { AppBar, Toolbar, Typography, Box, Button } from "@mui/material";
import {Link} from "react-router-dom"

export const Header = () => {

    return (
        <AppBar position="static" color="primary" elevation={4}>
            <Toolbar>
                <Typography variant="h6" component={Link} to="/" style={{ textDecoration: 'none', color: 'inherit', flexGrow: 1 }}>
                    AccommodationsApp
                </Typography>

                <Box>
                    <Button component={Link} to="/accommodations" color="inherit">
                        Accommodations
                    </Button>
                    <Button component={Link} to="/hosts" color="inherit">
                        Hosts
                    </Button>
                    <Button component={Link} to="/countries" color="inherit">
                        Countries
                    </Button>
                </Box>
            </Toolbar>
        </AppBar>
    )
}