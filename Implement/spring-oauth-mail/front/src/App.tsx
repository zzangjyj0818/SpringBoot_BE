import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import SingUp from 'views/Authentication/SignUp';
import SingIn from 'views/Authentication/SignIn';
import OAuth from 'views/Authentication/OAuth';

const App = () => {

  return (
    <Routes>
      <Route path='/auth' >
        <Route path='sign-up' element={<SingUp/>} />
        <Route path='sign-in' element={<SingIn/>} />
        <Route path='oauth-response/:token/:expirationTime' element={<OAuth/>} />
      </Route>
    </Routes>
  );
}

export default App;
