import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

export default function Header({user, authenticated}) {

    const handleLogoutButtonPressed = () => {
        fetch("/api/account/logout") // , requestOptions)
            .then((response) => response)
            .then((data) => {
                window.localStorage.removeItem('user');
                window.location.reload();
            }
        );
    }

    return (
        <div className="h-16 flex items-center bg-gray-500">
            {authenticated
            ?
            <div>
                {user}님 환영합니다.
                <button className='header_optionLineTwo' onClick={() => handleLogoutButtonPressed()}>로그아웃</button>
            </div>
            :
            <div>
                <Link to="/signup">회원가입</Link>
                <br></br>
                <Link to="/login">로그인</Link>
            </div>
            }
        </div>

    )
}