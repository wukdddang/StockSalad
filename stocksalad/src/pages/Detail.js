import React from "react";
import { useParams } from "react-router-dom";
import StockDetail from "../components/StockDetail";

function Detail(props) {
  let { product } = useParams();
  console.log(product);
  return (
    <div className="w-full p-10 bg-white rounded-lg">
      <h2 className="text-3xl font-bold text-left">{product}</h2>
      <StockDetail />
    </div>
  );
}

export default Detail;
