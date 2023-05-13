import React, { useState } from "react";
import logo from "../logo/logo.svg";
import { Menu, Moon, User, X } from "react-feather";
import { Link } from "react-router-dom";

function Header(props) {
  return (
    <header>
      <div className="flex items-center justify-between">
        <div
          className="bg-center bg-no-repeat min-[320px]:w-12 min-[320px]:h-12 md:h-12 md:w-12 lg:h-16 lg:w-16 drop-shadow-md"
          style={{ backgroundImage: `url(${logo})` }}
        />
        <Link className="text-3xl font-bold max-[440px]:text-2xl" to="/">
          StockSalad
        </Link>
        <div className="flex gap-5 drop-shadow-md">
          <Link to="/user">
            <User className="cursor-pointer" />
          </Link>
          <Moon className="cursor-pointer" />
        </div>
      </div>
    </header>
  );
}

export default Header;
