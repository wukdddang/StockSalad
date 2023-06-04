import React from "react";
import StockHeader from "./components/StockHeader";
import MainContent from "./components/MainContent";
import MainFooter from "./components/MainFooter";
import { Routes, Route } from "react-router-dom";
import "./App.css";
import StockDetail from "./pages/StockDetail";
import User from "./pages/User";

function App() {
  // localStorage에서 회원 아이디 받아오기
  return (
    <div className="box-border App">
      <StockHeader />
      <Routes>
        <Route path="/" element={<MainContent />} />
        <Route path="/detail/:product" element={<StockDetail />} />
        <Route path="/user" element={<User />} />
      </Routes>
      {/* <MainFooter /> */}
    </div>
  );
}

export default App;
