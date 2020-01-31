import React, { Component } from 'react';
import Form from './Form';
import Api from './Api';

class App extends Component {
    state = {
        dep: '',
        arr: ''
    };

    handleSubmit = character => {
        console.log(character)
        this.setState(
            {
                arr: character.arr,
                dep: character.dep,
            }
            );
    }

    render() {
     const { arr, dep } = this.state;
        return (
            <div className="container">
            <h3>Flight Time</h3>
            <p>Easy to use with clean interface application for finding airports</p>
            <h3>Search</h3>
                <Form handleSubmit={this.handleSubmit} />

                <h1>Results</h1>
                <Api arr={arr} dep={dep}/>
            </div>
        );
    }
}

export default App;