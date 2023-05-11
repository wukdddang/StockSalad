import React from 'react';
import Header from './components/Header';
import Content from './components/Content';
import './App.css'

function App(props) {
  return (
    <div className='box-border App'>
      <Header />
      <Content/>
      
    </div>
  );
}

export default App;