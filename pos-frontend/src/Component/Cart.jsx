import React from "react";
import "bootstrap/dist/css/bootstrap.min.css";

const Cart = ({cartItems, updateCartItem, removeCartItem}) => {
  const calculateSubTotal = () => {
    return cartItems.reduce((sum, item) => sum + (item.price * item.quantity || 0),0);
  };
  const calculateTax = (subtotal) => {
    const taxRate = 0.1; //assume
    return subtotal * taxRate;
  };
  const subtotal = calculateSubTotal();
  const tax = calculateTax(subtotal);
  const total = subtotal + tax;
  console.log("Cart Items:", cartItems)
  
  
  return (
    <div className="container mt-4">
   <h2>
  <i className="fas fa-shopping-cart"></i> Your Cart
</h2>

      <div className="row">
        {cartItems.length === 0 ? (
          <p>No items in the cart..please purchase it</p>
        ) : (
          cartItems.map((item) => (
            <div key={item.id} className="col-md-4 mb-4">
              <div className="card">
                <div className="card-body">
                  <h5 className="card-title">{item.name}</h5>
                  <p className="card-text"></p>
                  <p className="card-text"></p>
                  <div className="d-flex align-items-center mb-2">
                    <button
                      onClick={() => updateCartItem(item.id, -1)}
                      className="btn btn-secondary"
                    >
                      -
                    </button>
                    <input
                      type="text"
                      value={item.quantity}
                      readOnly
                      className="form-control mx-2"
                      style={{ width: "60px" }}
                    />
                    <button
                      onClick={() => updateCartItem(item.id, 1)}
                      className="btn btn-secondary"
                    >
                      +
                    </button>
                  </div>
                  <button onClick={() => removeCartItem(item.id)}>
                    Remove
                  </button>
                </div>
              </div>
            </div>
          ))
        )}

      </div>
      <div className="mt-4">
  <div className="card">
    <div className="card-body">
      <h3 className="card-title">Summary</h3>
      <div className="d-flex justify-content-between">
        <p>Subtotal:</p>
        <p>${subtotal.toFixed(2)}</p>
      </div>
      <div className="d-flex justify-content-between">
        <p>Tax:</p>
        <p>${tax.toFixed(2)}</p>
      </div>
      <div className="d-flex justify-content-between">
        <h4>Total:</h4>
        <h4>${total.toFixed(2)}</h4>
      </div>
      <button className="btn btn-success btn-block mt-3">
        Proceed to Payment
      </button>
    </div>
  </div>
</div>

    </div>
  );
};

export default Cart;
