import React from 'react';
import Header from './components/Header';
import Content from './components/Content';
import './App.css'

function App(props) {
  return (
    <div className='App box-border'>
      <Header/>
      <Content/>
      
    </div>
  );
}

export default App;