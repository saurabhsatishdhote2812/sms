import React, { useState } from 'react';
import './LoginPage.css';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';



const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate();

  const handleLogin =  async (e)  => {
    e.preventDefault();
    console.log('Logging in with', username, password);
    try {
        const response = await axios.post('http://localhost:8080/sms/login', {
            username: username,
            password: password
        }, {
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (response.data === 'PASS') {
        navigate('/subjects'); // Redirect on success
      } else {
        alert('Login failed. Please check your credentials!');
      }
    } catch (error) {
        console.error('Login Failed:', error.response ? error.response.data : error.message);
    }


  };

  return (
    <div className="login-container">
      <form className="login-form"  onSubmit={handleLogin}>
        <h2 className="login-title">Student Login</h2>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="login-input"
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="login-input"
          required
        />
        <button type="submit" className="login-button">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;
