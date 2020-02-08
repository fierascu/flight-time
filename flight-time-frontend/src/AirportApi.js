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
      timeout: 2000,
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

    const aeroportTable =
      <table>
        <thead>
          <tr>
            <th>City</th>
            <th>Name</th>
            <th>Came</th>
          </tr>
        </thead>
        <tbody>
          {data.map((row, index) =>
            <tr key={index}>
              <td>{row.city}</td>
              <td>{row.name}</td>
              <td>{row.code}</td>
            </tr>
          )}
        </tbody>
      </table>;

    return data === undefined ? null :
      <div>
        <p>Airport <b>{this.props.airportText}</b></p>
        {aeroportTable}
      </div>;
  }
}

export default AirportApi;