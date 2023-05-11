import React, { useState } from 'react';
import { Search } from 'react-feather';
import Stock from './Stock';

function Content(props) {
  
  let [stocks, setStocks] = useState([
    {
      product: 'spy',
      stat: 'rise',
      price: '412.59',
      percent: '1.25'
    },
    {
      product: 'qqq',
      stat: 'down',
      price: '312.59',
      percent: '2.15'
    },
    {
      product: 'kospi',
      stat: 'rise',
      price: '2491',
      percent: '1.25'
    },
  ])
  
  return (
    <>
      <div className='flex items-center w-full h-20 mt-8'>
        <div className='relative w-full drop-shadow-md '>
          <input className='w-full py-2 pl-12 transition duration-300 rounded-lg outline-none focus:ring focus:border-blue-100' placeholder='여기에 적어~'>
          </input>
          {/* 포커스 되면 돋보기 사라지게 만들자 */}
          {/* class 속성 추가해서 사라지게 끔 */}
          <Search className='absolute left-4 bottom-2'/>
        </div>
      </div>
      {stocks.map(stock => (
        <Stock props={stock} />
      ))}

    </>
  );
}

export default Content;