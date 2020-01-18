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
            headers: {'Access-Control-Allow-Origin': '*'}
          });

        instance.get('/flight', {
            params: {
              dep: 'MUC',
              arr:'TSR'
            }
          })
          .then(function (response) {
            console.log(response);
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

        const result = data.length === 0 ? <div></div> : data.map((entry, index) => {
            console.log(entry);
            return <li key={index}>{entry}</li>;
        });

        return <div className="container"><ul>{result}</ul></div>;
    }
}

export default Api;