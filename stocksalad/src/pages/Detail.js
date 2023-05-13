import React from 'react';
import { useParams } from 'react-router-dom';

function Detail(props) {
  let { product } = useParams();
  console.log(product);
  return (
    <div className='w-full p-10 bg-white rounded-lg'>
      <h2 className='text-3xl font-bold text-left'>{product}</h2>
    </div>
  );
}

export default Detail;