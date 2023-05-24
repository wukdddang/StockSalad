import React, { useEffect, useRef, useState } from "react";
import { Search, TrendingDown, TrendingUp } from "react-feather";
import StockBlock from "./StockBlock";
import { Routes, Route, Link, useNavigate, Outlet } from "react-router-dom";
import axios from "axios";

function MainContent(props) {
  let [text, setText] = useState("");
  let [show, setShow] = useState(true);
  const inputRef = useRef(null);

  let [reqStocks, setReqStocks] = useState([]);
  let [stocks, setStocks] = useState([
    {
      title: "spy",
      stat: "rise",
      price: "$ 412.59",
      percent: "1.25",
      country: "USA",
    },
    {
      title: "qqq",
      stat: "down",
      price: "$ 312.59",
      percent: "2.15",
      country: "USA",
    },
    {
      title: "kospi",
      stat: "rise",
      price: "₩ 2,491",
      percent: "1.02",
      country: "KOR",
    },
  ]);

  let canClick = useRef(true);

  const handleInputChange = async (e) => {
    const inputValue = e.target.value;
    setText(inputValue);
    setShow(inputValue !== ""); // 입력 값이 있는 경우에만 show를 true로 설정
  };

  const handleESC = (e) => {
    if (e.key === "Escape") {
      setTimeout(() => {
        setShow(false);
        inputRef.current.blur();
      }, 100);
    }
  };

  const handleDisappear = (e) => {
    setTimeout(() => {
      setShow(false);
    }, 100);
  };
  const handleAppear = (e) => {
    setShow(true);
  };

  return (
    <>
      <div className="flex items-center w-full h-20">
        <div className="relative w-full drop-shadow-md ">
          <input
            ref={inputRef}
            onChange={handleInputChange}
            onBlur={handleDisappear}
            onFocus={handleAppear}
            value={text}
            onKeyDown={handleESC}
            className="w-full py-2 pl-12 transition duration-300 bg-white rounded-lg outline-none focus:ring focus:border-blue-100"
            placeholder="원하는 주식명을 검색해주세요"
          ></input>
          <Search className="absolute left-4 bottom-2 " />
          {text === "" ? (
            // 입력이 비었다면 일단 빈창을 보여준다. -> 다른 방법이 있을까?
            <></>
          ) : show ? (
            <div className="absolute w-full bg-white border-4 rounded-lg drop-shadow-xl">
              {stocks.map((stock) => {
                return (
                  <Link
                    to={`/detail/${stock.title}`}
                    key={stock.title}
                    className="flex justify-center py-2"
                    stock={stock}
                  >
                    <h2 className="flex-1 font-bold">{stock.title}</h2>
                    {stock.stat === "rise" ? (
                      <>
                        <TrendingUp className="flex-1 text-red-500" />
                        <div className="flex-1">{stock.price}</div>
                        <div className="flex-1 text-red-400">
                          +{stock.percent}%
                        </div>
                      </>
                    ) : (
                      <>
                        <TrendingDown className="flex-1 text-blue-500" />
                        <div className="flex-1">{stock.price}</div>
                        <div className="flex-1 text-blue-400">
                          -{stock.percent}%
                        </div>
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
      {stocks.map((stock, idx) => (
        <div
          key={stock.title}
          className="flex w-full h-16 bg-white border-4 rounded-lg"
        >
          hi!
        </div>
      ))}
    </>
  );
}

export default MainContent;
