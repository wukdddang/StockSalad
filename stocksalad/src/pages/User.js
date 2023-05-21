import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function User(props) {
  let [loginStatus, setLoginStatus] = useState(false);
  let [id, setId] = useState("");
  let [pw, setPw] = useState("");
  let navigate = useNavigate();
  useEffect(() => {
    if (!localStorage.getItem("ss_user")) {
      setLoginStatus(false);
    } else {
      setLoginStatus(true);
    }
  }, [loginStatus]);
  return (
    <div className="flex justify-center drop-shadow-xl">
      {loginStatus ? (
        <div className="w-6/12 p-4 bg-white rounded-lg">
          {JSON.parse(localStorage.getItem("ss_user")).id}님 안녕하세요?
        </div>
      ) : (
        <div className="flex flex-col items-center w-10/12 p-4 bg-white border-2 rounded-lg ">
          <h2 className="mb-10 text-3xl font-bold">로그인</h2>
          <div className="w-10/12 mb-4">
            <h4 className="text-left">아이디</h4>
            <input
              className="w-full transition duration-500 border-2 rounded-md outline-none focus:ring"
              onChange={(e) => {
                setId(e.target.value);
              }}
            ></input>
          </div>
          <div className="w-10/12">
            <h4 className="text-left">비밀번호</h4>
            <input
              className="w-full transition duration-500 border-2 rounded-md outline-none focus:ring"
              onChange={(e) => {
                setPw(e.target.value);
              }}
              type="password"
            ></input>
          </div>
          <div
            onClick={() => {
              if (id === "" || pw === "") return;
              localStorage.setItem(
                "ss_user",
                JSON.stringify({
                  id,
                  pw,
                })
              );
              navigate(0);
            }}
            className="block px-6 py-2 mt-5 transition border-2 rounded-lg cursor-pointer hover:bg-blue-500 hover:text-white"
          >
            로그인하기
          </div>
        </div>
      )}
    </div>
  );
}

export default User;
