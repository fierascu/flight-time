import React, { Component } from 'react';
import Form from './Form';
import AirportForm from './AirportForm';
import Api from './Api';
import AirportApi from './AirportApi';
import Tabs from './Tabs';

class App extends Component {
    state = {
        dep: '',
        arr: '',
        showAirportData: false,
        airportText: ''
    };

    handleSubmit = character => {
        console.log(character)
        this.setState({
            arr: character.arr,
            dep: character.dep,
            showAirportData: character.showAirportData,
            airportText: character.airportText
        });
    }

    render() {
        const { arr, dep, showAirportData, airportText } = this.state;
        return (
            <div className="container">

                <Tabs>
                    <div label="✈️ Flight">
                        <div className="container">
                            <h3>Flight Time</h3>
                            <p>Easy to use with clean interface application for calculating flight time between two airports</p>
                            <h3>Search</h3>
                            <Form handleSubmit={this.handleSubmit} />
                            <h1>Results</h1>
                            <Api arr={arr} dep={dep} showAirportData={showAirportData} />
                        </div>
                    </div>
                    <div label="🚩 Airport">
                        <div className="container">
                            <h3>Airport</h3>
                            <p>Easy to use with clean interface application for searching an airports</p>
                            <h3>Search</h3>
                            <AirportForm handleSubmit={this.handleSubmit} />
                            <h1>Results</h1>
                            <AirportApi airportText={airportText} />
                        </div>
                    </div>
                </Tabs>
            </div>


        );
    }
}

export default App;