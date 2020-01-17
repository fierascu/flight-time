import React, { Component } from 'react';

class Api extends Component {
    state = {
        data: []
    };

    componentDidMount() {
        const url = "http://localhost:8082/flight?dep=MUC&arr=TSR";

        fetch(url)
            .then(result => result.json())
            .then(result => {
                this.setState({
                    data: result
                })
            });
    }

    render() {
        const { data } = this.state;

        const result = data.length === 0 ? <div></div> : data.map((entry, index) => {
            console.log(entry);
            return <li key={index}>{entry}</li>;
        });

        return <div className="container"><ul>{result}</ul></div>;
    }
}

export default Api;