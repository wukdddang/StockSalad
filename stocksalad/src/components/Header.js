import React from 'react';
import logo from '../logo/logo.svg'
import { Menu, Moon } from 'react-feather';


function Header(props) {
  return (
    <header>
      <div className='px-4 flex justify-between items-center'>
        <div className='bg-no-repeat w-16 h-16 drop-shadow-md bg-center' style={{backgroundImage: `url(${logo})`}} />
        <div className='flex gap-5 drop-shadow-md'>
          <Menu />
          <Moon />
        </div>

      </div>
    </header>
  );
}

export default Header;