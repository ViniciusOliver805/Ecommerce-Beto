'use client'

import Image from 'next/image';
import React, { useState } from "react"

function ProductImages({ product = {} }: { product: any }) {

  const [selectedImage, setSelectedImage] = useState(
    product.Images ? product.Images[0] : {}


  );

  return (
    <div className="flex gap-5">
      <div className="flex flex-cols gap5">
        {product.images.map((image: any) => (
          <div key={image}>
            <Image
              src={image.url}
              alt=""
              width={50}
              height={50}
              className={`object-cover cursor-pointer border-solid p-2 border-gray-300
                     ${
                      selectedImage === image
                      ? 'border-solid border-blue-500 border-2'
                      : ''
                     }`}
              onClick={() => { setSelectedImage(image)}}
            />
          </div>
        ))}
      </div>
      <div>

      </div>
      <Image
        src={selectedImage.url}
        alt=""
        width={400}
        height={400}
        className='object-cover'
      ></Image>

    </div>
  )
}

export default ProductImages