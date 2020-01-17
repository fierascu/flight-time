import React, {Component} from 'react';

class Form extends Component {
    constructor(props) {
        super(props);
        
        this.initialState = {
            dep: '',
            arr: ''
        };

        this.state = this.initialState;
    }

    handleChange = event => {
        const { name, value } = event.target;

        this.setState({
            [name] : value
        });
    }

    onFormSubmit = (event) => {
        event.preventDefault();
        
        this.props.handleSubmit(this.state);
        this.setState(this.initialState);
    }

    render() {
        const { dep, arr } = this.state; 

        return (
            <form onSubmit={this.onFormSubmit}>
                <label for="dep">Departure airport</label>
                <input 
                    type="text" 
                    name="dep" 
                    id="dep"
                    value={dep} 
                    onChange={this.handleChange} />
                <label for="arr">Arrival airport</label>
                <input 
                    type="text" 
                    name="arr" 
                    id="arr"
                    value={arr} 
                    onChange={this.handleChange} />
                <button type="submit">
                    Search
                </button>
            </form>
        );
    }
}

export default Form;
