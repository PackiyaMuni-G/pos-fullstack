import React, { useEffect, useState } from 'react'
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const ProductList = ({ addToCart }) => {
    const [products, setProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [activeFilter, setActiveFilter] = useState('all');
    // const [quantities, setQuantities] = useState({}); 
  
    useEffect(() => {
      fetchProducts();
    }, []);
  
    const fetchProducts = async () => {
      try {
        const response = await axios.get('http://localhost:8080/products');
        setProducts(response.data);
        setFilteredProducts(response.data)
      } catch (error) {
        console.error('Error fetching products:', error);
      }
    };
    // const handleQuantityChange = (id, change) => {
    //     setQuantities((prevQuantities) => {
    //         const currentQuantity = prevQuantities[id] || 0; // Get current quantity or default to 0
    //         const newQuantity = Math.max(0, currentQuantity + change); // Prevent negative quantities
    //         return { ...prevQuantities, [id]: newQuantity }; // Update the quantity for the specific product
    //     });
    // };
    const handleFilter = (filter) => {
        console.log("filter " +filter);
        
        setActiveFilter(filter);
        console.log("Active Filter:", filter);
        if (filter === 'all') {
          setFilteredProducts(products); // Show all products
        } else {
          const filtered = products.filter((product) =>
            product.name.toLowerCase().includes(filter) // Check if name contains the filter
          );
          console.log("Filtered Products:", filtered);
          setFilteredProducts(filtered); // Show filtered products
        }
      };
  
    return (
      <div className="container mt-4">
        <div className="row">
            <h1>Point of Sale</h1>
            <div className="btn-group mb-4" role="group">
        <button
          type="button"
          className={`btn ${activeFilter === 'all' ? 'btn-primary' : 'btn-secondary'}`}
          onClick={() => handleFilter('all')}
        >
          All
        </button>
        <button
          type="button"
          className={`btn ${activeFilter === 'dal' ? 'btn-primary' : 'btn-secondary'}`}
          onClick={() => handleFilter('dal')}
        >
          Dal
        </button>
        <button
          type="button"
          className={`btn ${activeFilter === 'oil' ? 'btn-primary' : 'btn-secondary'}`}
          onClick={() => handleFilter('oil')}
        >
          Oil
        </button>
      </div>
          {filteredProducts.map((product) => (
            <div key={product.id} className="col-md-4 mb-4">
              <div className="card">
                <div className="card-body">
                  <h5 className="card-title">{product.name}</h5>
                  <p className="card-text">description:{product.description}</p>
                  <p className="card-text">Price: ${product.price}</p>
                  <p className="card-text">Stock: {product.stockQuantity}</p>
                  {/* <div className="d-flex align-items-center mb-2">
                                    <button
                                        onClick={() => handleQuantityChange(product.id, -1)}
                                        className="btn btn-secondary"
                                    >
                                        -
                                    </button>
                                    <input
                                        type="text"
                                        value={quantities[product.id] || 0}
                                        readOnly
                                        className="form-control mx-2"
                                        style={{ width: '60px' }}
                                    />
                                    <button
                                        onClick={() => handleQuantityChange(product.id, 1)}
                                        className="btn btn-secondary"
                                    >
                                        +
                                    </button>
                                </div> */}
                  <button
                    onClick={() => addToCart(product)}
                    className="btn btn-primary"
                  >
                    Add to Cart
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  };
  

export default ProductList;