import React, { Component } from 'react';
import App from "../App";
import {SERVER_URL} from '../constants.js'
import ReactTable from "react-table";

class Manuscripts extends Component {
    constructor(props) {
        super(props);
        this.state = { manuscripts: [] };
    }
    componentDidMount() {
        fetch(SERVER_URL + 'manuscripts')
            .then((response) => response.json())
            .then((responseData) => {
                this.setState({
                    manuscripts: responseData._embedded.manuscripts,
                });
            })
            .catch(err => console.error(err));
    }

    render() {
        const tableRows = this.state.manuscripts.map((manuscript, index) =>
            <tr key={index}>
                <td>{index+1}</td>
                <td>{manuscript.title}</td>
                <td>{manuscript.manuscriptStatus}</td>
                <td>{manuscript.editor}</td>
            </tr>);
        return (
            <div className={App}>
                <table>
                    <thead>
                    <tr>
                        <td>N</td>
                        <td>Title</td>
                        <td>Status</td>
                    </tr>
                    </thead>
                    <tbody>{tableRows}</tbody>
                </table>

            </div>
        );
    }
}

export default Manuscripts;