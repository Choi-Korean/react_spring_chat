import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route, Link} from "react-router-dom";
import Header from './Header';
import Login from './Login';
import Signup from './Signup';

export default class HomePage extends Component{
    constructor(props){
        super(props);
        this.state = {
            
        };
    }

    

    renderHomePage(){
        return (
            <>HomePage!</>
        );
    }

    render(){ 
        return (
        <Router>
            <Header />
            <Routes> 
                <Route path='/*' element={this.renderHomePage()}></Route>
                <Route path='/signup' element={<Signup />}/>
                <Route path='/login' element={<Login />}/>
            </Routes>
        </Router>);
    }
}