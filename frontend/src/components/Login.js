import React from 'react';
import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { Button } from '@material-ui/core';
import { useNavigate } from 'react-router-dom';
import { Cookies } from "react-cookie";

function Login(props) {

    const [username, setUsername] = useState();
    const [password, setPassword] = useState();
    const navigate = useNavigate();
    const CSRFToken = new Cookies();

    useEffect(() => {
        // checkLogined();
    }, [])

    const checkLogined = () => {
        fetch("/api/account/islogined")
        .then((res) => {
            if(res.ok){
                navigate("/");
                window.location.reload();
            }
            return res.json();
        })
        // .then(data => {
        //     console.log(data);
        //     setPostData({
        //         id: data.id,
        //         name: data.name,
        //         display_name: data.display_name,
        //         price: data.price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
        //         sale_price: data.sale_price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ","),
        //         image: data.image,
        //         category: data.category,
        //         market: data.market,
        //     });
        // });
        // if(localStorage.getItem('token')){
        //     navigate("/info");
        //     window.location.reload();
        // }
    }

    // json으로 보내니까 DTO로 보내는 방식으로 못받음. 우선 이렇게 formData로
    const handleCreateButtonPressed = () => {
        const uploadData = new FormData();
        uploadData.append('upw', password);
        uploadData.append('email', username);

        var requestOptions = {
            method: "POST",
            headers: {"X-CSRFToken": CSRFToken.get('csrftoken')},   //  "Content-Type": "application/json", 
            body: uploadData,
        };

        fetch("/api/account/login", requestOptions)
            .then((response) => {
                if(response.ok){
                    // console.log(response.url);
                    // console.log(response.headers.user);
                    return response.json()} //.json()}
                else{
                    return;
                }})
            .then((data) => {
                console.log(data);
                if(data != undefined){
                    console.log(data);
                    // console.log(data.json());
                    // props.userHasAuthenticated(true, data.username, data.token);
                    checkLogined();
                }
            }
        );
    }

    const handleUserName = (e) => {
        setUsername(e.target.value)
    };

    const handlePassword = (e) => {
        setPassword(e.target.value)
    };
    

    return (
        <section className="section-login t-flex-1 t-flex t-items-center t-justify-center">
            <div className="card t-w-full t-max-w-screen-md mx-4">
                <div className="card-header">
                    <i className="fas fa-sign-in-alt"></i> 로그인
                </div>
                <div className="card-body">
                    <input type="text" name="username" autofocus="" autocapitalize="none" autocomplete="username" maxlength="150" className="form-control" placeholder="아이디" required="" id="id_username" onChange={(e) => handleUserName(e)} />
                    <input type="password" name="password" autocomplete="current-password" className="form-control" placeholder="비밀번호" required="" id="id_password" onChange={(e) => handlePassword(e)} />
                    <button type="submit" className="btn-outline-primary" onClick={() => handleCreateButtonPressed()}><i className='fas fa-user-plus'></i>로그인</button>
                    {/* <button type="reset" class="btn-outline-primary">취소</button> */}
                </div>
            </div>
        </section>
    );
}
export default Login;