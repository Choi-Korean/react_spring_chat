import React, { Component } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate, Outlet, Link} from "react-router-dom";
import Header from './Header';
import Login from './Login';
import Signup from './Signup';

export default class HomePage extends Component{
    constructor(props){
        super(props);
        this.state = {
            user : localStorage.getItem('user'),
            authenticated: false,
        };
        this.AuthLayout = this.AuthLayout.bind(this);
    }

    async componentDidMount() {
        fetch("/api/account/islogined")
        .then((res) => {
            if(res.ok){
                return res.json();
            }
        }).then((user) => {
            if(user !== undefined){
                localStorage.setItem('user', user.email);
                this.setState({
                    user: user.email,
                    authenticated: true
                });
            }else{
                window.localStorage.removeItem('user');
            }
            console.log(this.state);
        })
    }

    // 해당 태그에 묶인 건 인증되어야 진입 가능
    AuthLayout(){
        // 이전 페이지 정보 담아주기
        if (this.state.user !== null) {
            return this.state.authenticated ? <Outlet /> : null; // or loading indicator, etc...
        }
        localStorage.setItem('from', window.location.pathname);
        return <Navigate to="/login" replace />;
    };
    

    renderHomePage(){
        return (
            <>HomePage!</>
        );
    }

    render(){ 
        return (
        <Router>
            <Header user={this.state.user}
                    authenticated={this.state.authenticated}/>
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