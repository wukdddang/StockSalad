import React from "react";
import { useParams } from "react-router-dom";
import ApexCandle from "../components/ApexCandle";
import axios from "axios";

function StockDetail() {
  let { product } = useParams();

  let data = {
    startDate: "2023-05-28",
    endDate: "2023-05-31"
  };

  axios.post('http://secmobile.asuscomm.com:30604/news', data, {
      headers: {
          'Content-Type': 'application/json'
      }
  })
  // .then((response) => {
  //     console.log(response.data);
  // })
  // .catch((error) => {
  //     console.error(error);
  // });

  return (
    <div className="w-full h-full px-6 py-4 bg-white rounded-lg">
      <h2 className="mb-6 text-3xl font-bold text-left">{product}</h2>
      <ApexCandle />
    </div>
  );
}

export default StockDetail;
