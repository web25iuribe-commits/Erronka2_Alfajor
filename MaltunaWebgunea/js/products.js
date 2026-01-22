
async function loadProducts() {
  try {
    const response = await fetch('produktuak.json');
    const products = await response.json();
    return products;
  } catch (error) {
    console.error('Error al cargar productos:', error);
    showToast('Error al cargar los productos', 'error');
    return [];
  }
}
// Produktoa id-arekin lortzeko
async function getProductById(id) {
  const products = await loadProducts();
  return products.find(product => product.id === parseInt(id));
}

// Produktuaren informazioa betetzeko
async function fill_product_info() {
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get('id');

  if (!productId) {
    showToast('Producto no encontrado', 'error');
    window.location.href = 'inner-page-products.html';
    return;
  }

  const product = await getProductById(productId);

  if (!product) {
    showToast('Producto no encontrado', 'error');
    window.location.href = 'inner-page-products.html';
    return;
  }
}

  // Produktuaren informazioa aktualizatzeko
  const infoDiv = document.getElementById('info');
  const descriptionDiv = document.getElementById('description');
  const buyDiv = document.getElementById('buy_product');
