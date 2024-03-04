import React from 'react';
import './App.css';
import { Route, Routes } from 'react-router-dom';
import SingUp from 'views/Authentication/SignUp';

const App = () => {

  return (
    <Routes>
      <Route path='/auth' >
        <Route path='sign-up' element={<SingUp/>} />
      </Route>
    </Routes>
  );
}

export default App;
