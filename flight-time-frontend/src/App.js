import React, { Component } from 'react';
import Table from './Table';
import Form from './Form';

class App extends Component {
    state = {
        characters: []
    };

    removeCharacter = index => {
        const { characters } = this.state;
    
        this.setState({
            characters: characters.filter((character, i) => { 
                return i !== index;
            })
        });
    }

    handleSubmit = character => {
        this.setState({characters: [this.state.characters, character]});
    }

    render() {
        const { characters } = this.state;
        
        return (
            <div className="container">
            <h3>Flight Time</h3>
            <p>Easy to use with clean interface application for finding airports</p>
            <h3>Search</h3>
                <Form handleSubmit={this.handleSubmit} />

                <h1>Results</h1>                
                <Table characterData={characters} />
            </div>
        );
    }
}

export default App;