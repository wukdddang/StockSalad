import React from 'react';
import Header from './components/Header';
import Content from './components/Content';
import { Routes, Route } from 'react-router-dom';
import './App.css'
import Detail from './pages/Detail';

function App(props) {
  return (
    <div className='box-border App'>
      <Header />
      <Routes>
        <Route
          path="/"
          element={
            <Content/>
          }
        />
        <Route
          path="/detail"
          element={
            <Detail/>
          }
        />
      </Routes>
    </div>
  );
}

export default App;