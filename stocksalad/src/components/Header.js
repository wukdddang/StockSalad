import React from 'react';
import logo from '../logo/logo.svg'
import { Menu, Moon } from 'react-feather';

function Header(props) {
  return (
    <header>
      <div className='flex items-center justify-between px-4'>
        <div className='w-16 h-16 bg-center bg-no-repeat drop-shadow-md' style={{backgroundImage: `url(${logo})`}} />
        <div className='text-3xl font-bold'>StockSalad</div>
        <div className='flex gap-5 drop-shadow-md'>
          <Menu className='cursor-pointer' />
          <Moon className='cursor-pointer' />
        </div>

      </div>
    </header>
  );
}

export default Header;