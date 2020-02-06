import React, { Component } from 'react';

class AirportForm extends Component {
    constructor(props) {
        super(props);

        this.initialState = {
            airportText: ''
        };

        this.state = this.initialState;
    }

    handleChange = event => {
        const { name, value } = event.target;

        this.setState({
            [name]: value
        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();

        this.props.handleSubmit(this.state);
        this.setState(this.initialState);
    }

    render() {
        const { airportText } = this.state;

        return (
            <form onSubmit={this.onFormSubmit}>
                <label htmlFor="airportText">Departure airport</label>
                <input
                    type="text"
                    name="airportText"
                    id="airportText"
                    value={airportText}
                    onChange={this.handleChange} />
                <button type="submit">
                    Search
                </button>
            </form>
        );
    }
}

export default AirportForm;
