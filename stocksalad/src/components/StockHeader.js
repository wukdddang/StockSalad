import React, { useState } from "react";
import { Menu, Moon, User, X } from "react-feather";
import { Link } from "react-router-dom";

function Header(props) {
  return (
    <header>
      <div className="flex items-center justify-between mt-4 mb-10">
        <Link className="text-4xl font-bold max-[440px]:text-2xl" to="/">
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
