import {Link} from "react-router-dom"

export const Header = () => {

    return (
        <nav className="navbar navbar-expand-lg navbar-dark bg-dark">
            <div className="container-fluid">
                <Link className="navbar-brand" to="/">AccommodationsApp</Link>
                <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                        <li className="nav-item">
                            <Link to="/accommodations" className="nav-link">Accommodations</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/hosts" className="nav-link">Hosts</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/countries" className="nav-link">Countries</Link>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    )
}