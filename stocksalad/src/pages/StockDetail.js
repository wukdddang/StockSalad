import React, { useEffect, useState } from "react";
import { Link, useParams } from "react-router-dom";
import ApexCandle from "../components/ApexCandle";

function StockDetail() {
  const user = localStorage.getItem("ss_user");
  let { product } = useParams();
  let [loginStatus, setLoginStatus] = useState(false);
  let [hasStock, setHasStock] = useState(false);

  useEffect(() => {
    if (!user) {
      setLoginStatus(false);
    } else {
      setLoginStatus(true);
      const parsedUser = JSON.parse(user);
      checkStock(parsedUser);
    }
  }, []);

  function checkStock(parsedUser) {
    return setHasStock(parsedUser.stock.includes(product));
  }
  function deleteStock() {
    const parsedUser = JSON.parse(localStorage.getItem("ss_user"));
    parsedUser.stock = parsedUser.stock.filter((item) => item !== product);
    localStorage.setItem("ss_user", JSON.stringify(parsedUser));
    setHasStock(false);
  }
  function addStock() {
    const parsedUser = JSON.parse(localStorage.getItem("ss_user"));
    parsedUser.stock.push(product);
    localStorage.setItem("ss_user", JSON.stringify(parsedUser));
    setHasStock(true);
  }

  return (
    <>
      <div className="w-full h-full px-6 py-4 bg-white rounded-lg">
        <h2 className="mb-6 text-4xl font-bold text-center">{product}</h2>
        <ApexCandle />
      </div>
      {loginStatus ? (
        hasStock ? (
          <button
            className="px-6 py-2 m-5 text-white transition bg-red-500 border-4 border-red-100 rounded-lg cursor-pointer hover:bg-red-600 hover:text-white hover:border-red-200"
            onClick={deleteStock}>
            관심 목록에서 제거하기
          </button>
        ) : (
          <button
            onClick={addStock}
            className="px-6 py-2 m-5 text-white transition bg-blue-500 border-4 border-blue-100 rounded-lg cursor-pointer hover:bg-blue-600 hover:text-white hover:border-blue-200">
            관심 목록에 추가하기
          </button>
        )
      ) : (
        <button className="px-6 py-2 m-5 text-white transition bg-blue-500 border-4 border-blue-100 rounded-lg cursor-pointer bg-blue hover:bg-blue-600 hover:border-blue-200">
          <Link to="/user">관심 목록에 추가하기</Link>
        </button>
      )}
    </>
  );
}

export default StockDetail;
