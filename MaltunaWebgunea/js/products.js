
async function loadProducts() {
  try {
    const response = await fetch('produktuak.json');
    const products = await response.json();
    return products;
  } catch (error) {
    console.error('Errorea produktuak kargatzean:', error);
    showToast('Errorea produktuak kargatzean', 'error');
    return [];
  }
}

// ID bidez produktua lortu
async function getProductById(id) {
  const products = await loadProducts();
  return products.find(product => product.id === parseInt(id));
}

// Xehetasunen orrian produktuaren informazioa betetzeko
async function fill_product_info() {
  const urlParams = new URLSearchParams(window.location.search);
  const productId = urlParams.get('id');

  if (!productId) {
    showToast('Produktua ez da aurkitu', 'error');
    window.location.href = 'inner-page-products.html';
    return;
  }

  const product = await getProductById(productId);

  if (!product) {
    showToast('Produktua ez da aurkitu', 'error');
    window.location.href = 'inner-page-products.html';
    return;
  }

}

  // Produktuaren informazioa eguneratu
   const infoDiv = document.getElementById('info');
  const descriptionDiv = document.getElementById('description');
  const buyDiv = document.getElementById('buy_product');

