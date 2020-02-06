import React, { Component } from 'react';
import axios from 'axios';

class AirportApi extends Component {
  state = {
    data: [],
  };

  componentDidMount() {
    this.getAirport();
  }

  componentDidUpdate(prevProps) {
    if (this.props.airportText !== prevProps.airportText) {

      this.getAirport();
    }
  }

  getAirport = () => {
    if (this.props.airportText === '') {
      return;
    }

    const url = "http://localhost:8082/";

    const instance = axios.create({
      baseURL: url,
      timeout: 1000,
      headers: { 'Access-Control-Allow-Origin': '*' }
    });

    let currentComponent = this;

    instance.get('/ap', {
      params: {
        wildcard: this.props.airportText
      }
    })
      .then(function (response) {
        console.log(response.data);
        currentComponent.setState({
          data: response.data
        })
      })
      .catch(function (error) {
        console.log(error);
      })
      .finally(function () {
        // always executed
      });
  };


  render() {
    const { data } = this.state;
    console.log(data);

    return data === undefined ? null : 
    <div>
      <p>Airport <b>{this.props.airportText}</b></p>
      
      {/* {data.map((row, index) => {
            <tr key={index}>
                <td>{row.name}</td>
                <td>{row.job}</td>
                <td><button onClick={() => props.removeCharacter(index)}>Delete</button></td>
            </tr>}} */}
       
    
    </div>;
  }
}

export default AirportApi;