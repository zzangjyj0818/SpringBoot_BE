import React, {ChangeEvent, useState} from 'react';
import './App.css';
import InputBox from 'components/InputBox';

const App = () => {

  return (
    <>
      <div className='text-link-lg full-width'>회원가입</div>
      <div className='kakao-sign-in-button'></div>
      <div className='naver-sign-in-button'></div>
    </>
  );
}

export default App;
