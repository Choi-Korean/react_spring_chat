import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

export default function Header() {

    useEffect(() => {
    },[]);


    const handleLogoutButtonPressed = () => {
        fetch("/api/account/logout") // , requestOptions)
            .then((response) => response)
            .then((data) => {
                console.log(data);
                // window.localStorage.removeItem('token');
                // setToken("");
            }
        );
    }

    // const handleUserName = (e) => {
    //     setUsername(e.target.value)
    // };

    // const handlePassword = (e) => {
    //     setPassword(e.target.value)
    // };

    // const setStateFromChild = (e) => {
    //     if(e > 0){
    //         setCartCount(cartCount + 1);
    //     }else{
    //         setCartCount(cartCount - 1);
    //     }
    // };

    return (
        <div className="h-16 flex items-center bg-gray-500">
                <Link to="/signup">회원가입</Link>
                <br></br>
                <Link to="/login">로그인</Link>
                <button className='header_optionLineTwo' onClick={() => handleLogoutButtonPressed()}>로그아웃</button>
        </div>

    )
}