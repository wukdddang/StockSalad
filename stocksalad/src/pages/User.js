import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function User() {
  let [loginStatus, setLoginStatus] = useState(false);
  let [userInfo, setUserInfo] = useState({
    id: "",
    pw: "",
    stock: [],
  });
  let navigate = useNavigate();
  useEffect(() => {
    if (!localStorage.getItem("ss_user")) {
      setLoginStatus(false);
    } else {
      setLoginStatus(true);
      setUserInfo(JSON.parse(localStorage.getItem("ss_user")));
    }
  }, [loginStatus]);
  return (
    <div className="flex justify-center drop-shadow-xl">
      {loginStatus ? (
        <div className="w-8/12 px-10 py-6 bg-white rounded-lg">
          <h2 className="pb-8 text-3xl font-bold">
            {JSON.parse(localStorage.getItem("ss_user")).id}님 안녕하세요?
          </h2>
          <div>
            <h4 className="font-bold text-left">현재 관심 주식목록</h4>
            <ul>
              {userInfo.stock.map((item) => (
                <li className="pt-4 text-left">{item}</li>
              ))}
            </ul>
          </div>
        </div>
      ) : (
        <div className="flex flex-col items-center w-10/12 p-4 bg-white border-2 rounded-lg ">
          <h2 className="mb-10 text-3xl font-bold">로그인</h2>
          <div className="w-10/12 mb-4">
            <h4 className="text-left">아이디</h4>
            <input
              className="w-full transition duration-500 border-2 rounded-md outline-none focus:ring"
              onChange={(e) => {
                setUserInfo({ ...userInfo, id: e.target.value });
              }}></input>
          </div>
          <div className="w-10/12">
            <h4 className="text-left">비밀번호</h4>
            <input
              className="w-full transition duration-500 border-2 rounded-md outline-none focus:ring"
              onChange={(e) => {
                setUserInfo({ ...userInfo, pw: e.target.value });
              }}
              type="password"></input>
          </div>
          <div
            onClick={() => {
              if (userInfo.id === "" || userInfo.pw === "") return;
              localStorage.setItem("ss_user", JSON.stringify(userInfo));
              navigate(0);
            }}
            className="block px-6 py-2 mt-5 transition border-2 rounded-lg cursor-pointer hover:bg-blue-500 hover:text-white">
            로그인하기
          </div>
        </div>
      )}
    </div>
  );
}

export default User;
