import React, { Component } from 'react';
import axios from 'axios';

class Api extends Component {
  state = {
    data: []
  };

  componentDidMount() {
    const url = "http://localhost:8082/";

    const instance = axios.create({
      baseURL: url,
      timeout: 1000,
      headers: { 'Access-Control-Allow-Origin': '*' }
    });

    let currentComponent = this;

    instance.get('/flight', {
      params: {
        dep: 'MUC',
        arr: 'TSR'
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


  }

  render() {
    const { data } = this.state;


    return <div className="container"><ul>
    <li key={1}>dist: {data.dist}</li>
      <li key={2}>duration: {data.duration}</li>
      <li key={3}>durationAsTime: {data.durationAsTime}</li>
      </ul></div>;
  }
}

export default Api;