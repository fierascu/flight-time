import React, { Component } from 'react';
import axios from 'axios';

class Api extends Component {
  state = {
    data: [],
  };

  componentDidMount() {
    this.getDistance();
  }

  getDistance = async () => {
    if (this.props.dep === '' && this.props.arr === '') {
      return;
    }

    const url = "http://localhost:8082";
    const config = {
      method: 'get',
      url: url + '/flight',
      timeout: 1000,
      headers: { 'Access-Control-Allow-Origin': '*' }
    }

    let res = await axios(config, {
      dep: this.props.dep,
      arr: this.props.arr
    });

    let { data } = res.data;

    console.log('data:' + data);
    this.setState({ data: data });
  };

  render() {
    const { data } = this.state;

    return <div><ul>
      <li key={1}>From <b>{this.props.dep}</b> to <b>{this.props.arr}</b></li>
      <li key={2}>Distance: {data.dist}</li>
      <li key={3}>Duration: {data.duration}</li>
      <li key={4}>Time: {data.durationAsTime}</li>
    </ul></div>;


  }
}

export default Api;