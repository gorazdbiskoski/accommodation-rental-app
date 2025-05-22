import {Host} from "../Host/Host.jsx";

export const Hosts = (props) => {
    const {hosts, countries, onUpdate, onDelete} = props
    return (
        <>
            <div className="row">
                <div className="col-12 mx-auto">
                    <div className="table-responsive shadow-sm rounded-4 overflow-hidden">
                        <table className="table table-striped align-middle mb-0">
                            <thead className="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Surname</th>
                                <th>Country</th>
                                <th className="text-end">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {hosts.map((host) => (
                                <Host key={host.id} host={host} countries={countries} onUpdate={onUpdate} onDelete={onDelete} />
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );


}