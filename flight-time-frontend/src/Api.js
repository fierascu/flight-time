import React, { Component } from 'react';
import axios from 'axios';

class Api extends Component {
  state = {
    data: [],
  };

  componentDidMount() {
    this.getDistance();
  }

  componentDidUpdate(prevProps) {
    if (this.props.dep !== prevProps.dep
      || this.props.arr !== prevProps.arr
      || this.props.showAirportData !== prevProps.showAirportData) {

      this.getDistance();
    }
  }

  getDistance = () => {
    if (this.props.dep === '' && this.props.arr === '') {
      return;
    }

    const url = "/api";

    const instance = axios.create({
      baseURL: url,
      timeout: 1000,
      headers: { 'Access-Control-Allow-Origin': '*' }
    });

    let currentComponent = this;

    instance.get('/flight', {
      params: {
        dep: this.props.dep,
        arr: this.props.arr,
        showAirportData: this.props.showAirportData
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
    const dataAirport = this.state.depAirport === undefined ? [] : [data.depAirport, data.arrAirport];

    const aeroportTable = dataAirport === dataAirport.lenght === 0 ? null :
      <table>
        <thead>
          <tr>
            <th>City</th>
            <th>Name</th>
            <th>Gps Code</th>
            <th>Iata Code</th>
          </tr>
        </thead>
        <tbody>
          {dataAirport.map((row, index) =>
            <tr key={index}>
              <td>{row.municipality}</td>
              <td>{row.name}</td>
              <td>{row.gps_code}</td>
              <td>{row.iata_code}</td>
            </tr>
          )}
        </tbody>
      </table>;


    return data === undefined ? null : <div><ul>
      <li key={1}>From <span role="img" aria-label="departure airport">🛫</span> <b>{this.props.dep}</b> to <span role="img" aria-label="arrival airport">🛬</span> <b>{this.props.arr}</b></li>
      <li key={2}>Distance: {data.dist}</li>
      <li key={3}>Duration: {data.duration}</li>
      <li key={4}>Time: {data.durationAsTime}</li>
    </ul>
      {/* {this.props.showAirportData !== 'on' ? null : { aeroportTable }} */}

    </div>;
  }
}

export default Api;