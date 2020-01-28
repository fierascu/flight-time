import React from 'react';

const TableHeader = () => { 
    return (
        <thead>
            <tr>
                <th>Flight</th>
                <th>Time</th>
            </tr>
        </thead>
    );
}

const TableBody = props => { 
    const rows = props.characterData.map((row, index) => {
        return (
            <tr key={index}>
                <td>{row.flight}</td>
                <td>{row.time}</td>
            </tr>
        );
    });

    return <tbody>{rows}</tbody>;
}

const Table = (props) => {
    const { characterData } = props;
        return (
            <table>
                <TableHeader />
                <TableBody characterData={characterData}/>
            </table>
        );
}

export default Table;