import React, { useEffect, useRef, useState } from "react";
import { ArrowDown, ArrowUp, Search } from "react-feather";
import Stock from "./Stock";
import { Routes, Route, Link, useNavigate, Outlet } from "react-router-dom";

function Content(props) {
  let navigate = useNavigate();
  let [text, setText] = useState("");
  let [show, setShow] = useState(true);
  let [stocks, setStocks] = useState([
    {
      title: "spy",
      stat: "rise",
      price: "412.59",
      percent: "1.25",
      country: 'USA'
    },
    {
      title: "qqq",
      stat: "down",
      price: "312.59",
      percent: "2.15",
      country: 'USA'
    },
    {
      title: "kospi",
      stat: "rise",
      price: "2491",
      percent: "1.25",
      country: 'KOR'
    },
  ]);

  let canClick = useRef(true);

  const handleInputChange = (e) => {
    const inputValue = e.target.value;
    setText(inputValue);
    setShow(inputValue !== ""); // 입력 값이 있는 경우에만 show를 true로 설정
  };

  const handleDisappear = (e) => {
    setTimeout(() => {
      setShow(false);
    }, 100);
  };
  const handleAppear = (e) => {
    setShow(true);
  };

  const handleLinkClick = () => {
    canClick.current = false;
  };

  return (
    <>
      <div className="flex items-center w-full h-20 mt-8">
        <div className="relative w-full drop-shadow-md ">
          <input
            onChange={handleInputChange}
            onBlur={handleDisappear}
            onFocus={handleAppear}
            value={text}
            className="w-full py-2 pl-12 transition duration-300 rounded-lg outline-none focus:ring focus:border-blue-100"
            placeholder="여기에 적어~"
          ></input>
          <Search className="absolute left-4 bottom-2 " />
          {text === "" ? (
            <></>
          ) : show ? (
            <div className="absolute w-full bg-white border-t-2 border-b-4 border-l-4 border-r-4 rounded-lg drop-shadow-xl">
              {stocks.map((stock) => {
                return (
                  <Link
                    to={`/detail/${stock.title}`}
                    key={stock.title}
                    className="flex justify-center py-2"
                  >
                    <h2 className="flex-1 font-bold">{stock.title}</h2>
                    {stock.stat === "rise" ? (
                      <>
                        <ArrowUp className="flex-1 text-red-500" />
                        <div className="flex-1">{stock.price}</div>
                        <div className="flex-1 text-red-400">+{stock.percent}</div>
                      </>
                    ) : (
                      <>
                        <ArrowDown className="flex-1 text-blue-500" />
                        <div className="flex-1">{stock.price}</div>
                        <div className="flex-1 text-blue-400">-{stock.percent}</div>
                      </>
                    )}
                  </Link>
                );
              })}
            </div>
          ) : (
            <></>
          )}
        </div>
      </div>
      {stocks.map((stock) => (
        <Stock props={stock} key={stock.title} />
      ))}
    </>
  );
}

export default Content;
