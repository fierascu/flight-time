import React, { Component } from 'react';
import Form from './Form';
import Api from './Api';

class App extends Component {
    state = {
        dep: '',
        arr: '',
        showAirportData: false
    };

    handleSubmit = character => {
        console.log(character)
        this.setState({
            arr: character.arr,
            dep: character.dep,
            showAirportData: character.showAirportData
        });
    }

    render() {
        const { arr, dep, showAirportData } = this.state;
        return (
            <div className="container">
                <h3>Flight Time</h3>
                <p>Easy to use with clean interface application for calculating flight time between two airports</p>
                <h3>Search</h3>
                <Form handleSubmit={this.handleSubmit} />
                <h1>Results</h1>
                <Api arr={arr} dep={dep} showAirportData={showAirportData} />
            </div>
        );
    }
}

export default App;