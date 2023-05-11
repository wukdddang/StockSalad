import React from 'react';
import { Search } from 'react-feather';
import Stock from './Stock';

function Content(props) {
  return (
    <>
      <div className='mt-8 w-full h-20 flex items-center'>
        <div className='w-full relative drop-shadow-md '>
          <input className='rounded-lg outline-none transition duration-300 w-full py-2 pl-12  focus:ring focus:border-blue-100' placeholder='여기에 적어~'>
          </input>
          {/* 포커스 되면 돋보기 사라지게 만들자 */}
          {/* class 속성 추가해서 사라지게 끔 */}
          <Search className='absolute left-4 bottom-2'/>
          <Stock />
        </div>
      </div>
      <div className='mt-4 bg-gray-300 w-full h-48 flex'>
        너거가 선택한 주식이 나타난다. 
        배열 받아서 뿌려주기 하면 됨 ㅇㅋ?
      </div>
    </>
  );
}

export default Content;