import { useState } from "react";
import Cart from "./Component/Cart";
import ProductList from "./Component/ProductList";
import { Routes,Route, useNavigate } from 'react-router-dom';

function App() {
  const[cartItems,setCartItems]=useState([]);
  const navigate=useNavigate();

  const addToCart=(product)=>{
      setCartItems((prevItems)=>{
                const existingItem=prevItems.find(item => item.id === product.id);
                if(existingItem){
                    return prevItems.map(item=>
                           item.id===product.id
                            ? 
                           {...item,quanity:item.quantity+1} : item
                    );
                }
                else {
                  return [...prevItems,{...product,quantity:1}];
                }
                
      })
      navigate("/cart");
  }


  const updateCartItem=(id,change)=>{
       setCartItems((prevItems)=>{
             return prevItems.map(item=>{
                return  item.id===id ? {...item,quantity:Math.max(1,item.quantity+change)} : item
                })
       })
       

  }
  const removeCartItem=(id)=>{
    setCartItems((prevItems)=>{
       return prevItems.filter(item=> item.id !==id)
    })
}
  return (
    <div className="App">
      
      <Routes>
        <Route path="/" element={<ProductList addToCart={addToCart} />}/>
        <Route path="/cart" element={<Cart cartItems={cartItems} updateCartItem={updateCartItem} removeCartItem={removeCartItem} />} />
      </Routes>
    
    </div>
  );
}

export default App;
