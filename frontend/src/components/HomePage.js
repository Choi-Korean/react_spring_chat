import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route} from "react-router-dom";
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
            <div className="h-16 flex items-center bg-gray-500">
                <a href="/signup">회원가입</a>
                <br></br>
                <a href="/login">로그인</a>
            </div>
        );
    }

    render(){ 
        return (<Router>
            <Routes> 
                <Route path='/*' element={this.renderHomePage()}></Route>
                <Route path='/signup' element={<Signup />}/>
                <Route path='/login' element={<Login />}/>
            </Routes>
        </Router>);
    }
}