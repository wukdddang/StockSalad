import React from "react";
import { useParams } from "react-router-dom";
import ApexCandle from "../components/ApexCandle";

function StockDetail() {
  let { product } = useParams();
  return (
    <div className="w-full h-full px-6 py-4 bg-white rounded-lg">
      <h2 className="mb-6 text-3xl font-bold text-left">{product}</h2>
      <ApexCandle />
    </div>
  );
}

export default StockDetail;
