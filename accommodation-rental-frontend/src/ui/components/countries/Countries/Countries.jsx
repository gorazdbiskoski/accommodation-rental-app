import {Country} from "../Country/Country.jsx";

export const Countries = (props) => {
    const {countries, onUpdate, onDelete} = props
    return (
        <>
            <div className="row">
                <div className="col-12 mx-auto">
                    <div className="table-responsive shadow-sm rounded-4 overflow-hidden">
                        <table className="table table-striped align-middle mb-0">
                            <thead className="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Continent</th>
                                <th className="text-end">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {countries.map((country) => (
                                <Country key={country.id} country={country} onUpdate={onUpdate} onDelete={onDelete} />
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );


}