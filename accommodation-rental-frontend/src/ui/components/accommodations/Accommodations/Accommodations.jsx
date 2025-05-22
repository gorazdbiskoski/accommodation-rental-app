import {Accommodation} from "../Accommodation/Accommodation.jsx";

export const Accommodations = (props) => {
    const {accommodations, hosts, onUpdate, onDelete} = props
    return (
        <>
            <div className="row">
                <div className="col-12 mx-auto">
                    <div className="table-responsive shadow-sm rounded-4 overflow-hidden">
                        <table className="table table-striped align-middle mb-0">
                            <thead className="table-light">
                            <tr>
                                <th>Name</th>
                                <th>Category</th>
                                <th>Host</th>
                                <th>Num Rooms</th>
                                <th>Is Available</th>
                                <th className="text-end">Actions</th>
                            </tr>
                            </thead>
                            <tbody>
                            {accommodations.map((accommodation) => (
                                <Accommodation key={accommodation.id} accommodation={accommodation} hosts={hosts} onUpdate={onUpdate} onDelete={onDelete} />
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </>
    );


}