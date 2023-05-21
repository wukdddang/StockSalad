import React from "react";
import StockHeader from "./components/StockHeader";
import MainContent from "./components/MainContent";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import Detail from "./pages/Detail";
import User from "./pages/User";

function App(props) {
  // localStorage에서 회원 아이디 받아오기
  return (
    <div className="box-border App">
      <StockHeader />
      <Routes>
        <Route path="/" element={<MainContent />} />
        <Route path="/detail/:product" element={<Detail />} />
        <Route path="/user" element={<User />} />
      </Routes>
    </div>
  );
}

export default App;
