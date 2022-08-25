import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, Outlet} from "react-router-dom";
import Header from './Header';
import Login from './Login';
import Signup from './Signup';

export default class HomePage extends Component{
    constructor(props){
        super(props);
        // this.isLogined();

        this.state = {
            user : localStorage.getItem('user'),
            authenticated: false,
        };

        // this.isLogined = this.isLogined.bind(this);
        this.AuthLayout = this.AuthLayout.bind(this);
    }

    async componentDidMount() {
        fetch("/api/account/islogined")
        .then((res) => {
            if(res.ok){
                return res.json();
            }
        }).then((user) => {
            if(user != undefined){
                console.log(user);
                localStorage.setItem('user', user.email);
                this.setState({
                    user: user.email,
                    authenticated: true
                });
            }
            console.log(this.state);
        })
    }

    // 해당 태그에 묶인 건 인증되어야 진입 가능
    AuthLayout(){
        if (this.state.user !== null) {
            return this.state.authenticated ? <Outlet /> : null; // or loading indicator, etc...
        }
        return <Navigate to={"/login"} replace />;
    };
    

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
                <Route path='/login' element={<Login />}/>
                <Route element={<this.AuthLayout />}>
                    <Route path="/signup" element={<Signup />} />
                </Route>
            </Routes>
        </Router>);
    }
}