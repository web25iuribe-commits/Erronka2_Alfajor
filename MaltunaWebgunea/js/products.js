

// Produktuen orrian produktuen zerrenda sortzeko funtzioa

function create_products_list() {
// Produktuen orrian gauden egiaztatu
    if (!document.getElementById('products_list')) return;

    const products = JSON.parse (localStorage.getItem("products"))  || [];

    // DataTable inicializatu

    const dataTable = new simpleDatatables.DataTable('#products_list', {
        data: {
            headings: ['ID', 'Izena', 'Deskribapena', 'Prezioa', 'Stock', 'Ekintzak'],
            data: products.map(product => [
                product.Id_pieza,
                product.Izena,
                product.Deskribapena,
                `${product.Prezioa.toFixed(2)} €`,
                product.Stock,
                `<a href="product-details.html?id=${product.Id_pieza}" class="btn btn-primary btn-sm">Ikusi</a>`
            ])
        },
        perPage: 10,
        perPageSelect: [5, 10, 15, 20],
        labels: {
            placeholder: 'Bilatu...',
            perPage: 'erakutsi orriko',
            noRows: 'Ez da emaitzarik aurkitu',
            info: 'Erakusten {start} - {end} / {rows} (guztira)',
            noResults: 'Ez da emaitzarik aurkitu'
        },
        layout: {
            top: '{select}{search}',
            bottom: '{info}{pager}'
        }
    });
}


// Produktuaren xehetasunak bistaratzeko funtzioa
function show_product_details() {
// Produktuaren xehetasunen orrian gauden egiaz
    if (!document.getElementById('info')) return;

   const  products = JSON.parse(localStorage.getItem("products")) || [];

// Produktuaren IDa URLtik eskuratu
    const urlParams = new URLSearchParams(window.location.search);
    const productId = parseInt(urlParams.get('id'));
    console.log (productId);
    
// Produktua aurkituta
const product = products.find(p => p.Id_pieza == productId);

if (!product) {
    document.getElementById('info').innerHTML = '<h3>Produktua ez da aurkitu</h3>';
    return;
}

// Bialdu errorea ez badu argazkirik
const images = product.images || [];

// Slider
const swiperWrapper = document.querySelector('.swiper-wrapper');
if (swiperWrapper) {
    swiperWrapper.innerHTML = images.map(img =>
        `<div class="swiper-slide">
        </div>`
    ).join('');
}

// Informazioa
document.getElementById('info').innerHTML = `
    <h3>${product.Izena}</h3>
    <ul>
        <li><strong>Prezioa</strong>: ${product.Prezioa.toFixed(2)} €</li>
        <li><strong>Stock</strong>: ${product.Stock} unitate</li>
    </ul>
`;

document.getElementById('description').innerHTML = `
    <h2>Deskribapena</h2>
    <p>${product.Deskribapena}</p>
`;



// Erosketa botoia eguneratu
    const buyButton = document.getElementById('buy_product');
    if (buyButton) {
        if (product.Stock > 0) {
            buyButton.innerHTML = `
                <button class="btn btn-primary me-2" onclick="addToCart(${product.Id_pieza})">
                    Gehitu saskira
                </button>
                <button class="btn btn-success" onclick="buyNow(${product.Id_pieza})">
                    Erosi orain
                </button>
            `;
        } else {
            buyButton.innerHTML = `
                <button class="btn btn-secondary" disabled>
                    Ez dago stockean
                </button>
            `;
        }
    }
}


// Produktuaren xehetasunen orriarekin bateragarritasunerako alias funtzioa
function fill_product_info() {
    show_product_details();
}


// Saskeitara gehitzeko funtzioa
function addToCart(productId) {
    console.log("addToCart");
    const cart = JSON.parse(localStorage.getItem("cart")) || [];
     const products = JSON.parse (localStorage.getItem("products"))  || [];


    const quantity = parseInt(document.getElementById('canti').value) || 1;
    const product = products.find(p => p.Id_pieza === productId);
   
    
    if (!product) {
        showToast('Produktua ez da aurkitu', 'error');
        return;
    }
   
    if (product.Stock < quantity) {
        showToast(`Ez dago nahikoa stock. Stock eskuragarria: ${product.Stock}`, 'error');
        return;
    }
   
    const existingItem = cart.find(item => item.id == productId);

    if (existingItem) {
        if (existingItem.quantity + quantity > product.Stock) {
            showToast(`Ezin da gehitu. Gehienezko kantitatea: ${product.Stock}`, 'error');
            return;
        }
        existingItem.quantity += quantity;
    } else {
        cart.push({
            id: product.Id_pieza,
            name: product.Izena,
            price: product.Prezioa,
            quantity: quantity,
        });
    }
   
    localStorage.setItem('cart', JSON.stringify(cart));
    showToast(`${quantity} ${product.Izena} saskira gehitu da`, 'success');
    updateCartCount();
}


// Orain erosteko funtzioa
function buyNow(productId) {

    const products = JSON.parse(localStorage.getItem("products")) || [];
    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    const quantity = parseInt(document.getElementById('canti').value) || 1;
    const product = products.find(p => p.Id_pieza == productId);
   
    if (!product) {
        showToast('Produktua ez da aurkitu', 'error');
        return;
    }
   
    if (product.Stock < quantity) {
        showToast(`Ez dago nahikoa stock. Stock eskuragarria: ${product.Stock}`, 'error');
        return;
    }
   
// Saskia hustu eta artikulu hau gehitu
    cart = [{
        id: product.Id_pieza,
        name: product.Izena,
        price: product.Prezioa,
        quantity: quantity,
    }];
   
    localStorage.setItem('cart', JSON.stringify(cart));
    showToast('Saskira gehitu da, ordainketa orrira joaten...', 'success');
   
    setTimeout(() => {
        window.location.href = 'payment_1.html';
    }, 1000);
}


// Saskiaren kontagailuaren bistaratzea eguneratu
function updateCartCount() {
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
    const cartCount = cart.reduce((total, item) => total + item.quantity, 0);
// Beharrezkoa bada, hemen eguneratu dezakezu saskiaren badge-a
}


// Toast motako jakinarazpen funtzioa
function showToast(message, type = 'info') {
    if (typeof Toastify !== 'undefined') {
        Toastify({
            text: message,
            duration: 3000,
            gravity: "top",
            position: "right",
            backgroundColor: type === 'success' ? "#28a745" : type === 'error' ? "#dc3545" : "#17a2b8",
            stopOnFocus: true
        }).showToast();
    } else {
        alert(message);
    }
}


// Ordainketa-orriko funtzioak
function fill_payment_cards1() {
    const fillCards = document.getElementById('fill_cards');
    if (!fillCards) return;
   
    const cart = JSON.parse(localStorage.getItem('cart')) || [];
   
    if (cart.length === 0) {
        fillCards.innerHTML = '<p class="text-center text-muted">Saskia hutsik dago</p>';
        updatePaymentSummary();
        return;
    }
   
    const products = JSON.parse(localStorage.getItem("products")) || [];
    let cardsHTML = '';
    cart.forEach((item, index) => {
        cardsHTML += `
            <div class="row main align-items-center">
                <div class="col-2"><img class="img-fluid" src="assets/img/products/${item.image}" alt="${item.name}"></div>
                <div class="col">
                    <div class="row text-muted">${item.name}</div>
                    <div class="row">${item.price.toFixed(2)} €</div>
                </div>
                <div class="col">
                    <input type="number" class="form-control" value="${item.quantity}" min="1" max="${products.find(p => p.Id_pieza === item.id)?.Stock || 999}" onchange="updateQuantity(${index}, this.value)">
                </div>
                <div class="col">
                    ${(item.price * item.quantity).toFixed(2)} €
                </div>
                <div class="col">
                    <span class="close" onclick="removeFromCart(${index})">&#10005;</span>
                </div>
            </div>
            <hr>
        `;
    });
   
    fillCards.innerHTML = cardsHTML;
    updatePaymentSummary();
}


function updateQuantity(index, newQuantity) {
    const quantity = parseInt(newQuantity);
    if (quantity <= 0) {
        removeFromCart(index);
        return;
    }
    const products = JSON.parse(localStorage.getItem("products")) || [];
    let cart = JSON.parse(localStorage.getItem("cart")) || [];

    const product = products.find(p => p.Id_pieza == cart[index].Id_pieza);
    if (product && quantity > product.Stock) {
        showToast(`Ezin da gehitu. Gehienezko kantitatea: ${product.Stock}`, 'error');
        fill_payment_cards1();
        return;
    }
   
    cart[index].quantity = quantity;
    localStorage.setItem('cart', JSON.stringify(cart));
    fill_payment_cards1();
}


function removeFromCart(index) {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    cart.splice(index, 1);

    localStorage.setItem('cart', JSON.stringify(cart));
    fill_payment_cards1();
    showToast('Produktua saskitik kendu da', 'success');
}


function updatePaymentSummary() {
    const itemsElement = document.getElementById('items');
    const priceElement = document.getElementById('price'); 
    const totalPriceElement = document.getElementById('totalPrice');
   
    const cart = JSON.parse(localStorage.getItem('cart')) || [];

    const totalItems = cart.reduce((sum, item) => sum + item.quantity, 0);
    const subtotal = cart.reduce((sum, item) => sum + (item.price * item.quantity), 0);
    const shipping = 5.00;
    const total = subtotal + shipping;
   
    if (itemsElement) itemsElement.textContent = `${totalItems} items`;
    if (priceElement) priceElement.textContent = `${subtotal.toFixed(2)}€`;
    if (totalPriceElement) totalPriceElement.textContent = `${total.toFixed(2)}€`;
}


function go2print() {
    let cart = JSON.parse(localStorage.getItem('cart')) || [];

    if (cart.length === 0) {
        showToast('Saskia hutsik dago', 'error');
        return;
    }
   
// Inprimatze-orrirako eskaeraren datuak gorde
    const orderData = {
        username: localStorage.getItem('username') || 'Bezero',
        items: cart,
        subtotal: cart.reduce((sum, item) => sum + (item.price * item.quantity), 0),
        shipping: 5.00,
        total: cart.reduce((sum, item) => sum + (item.price * item.quantity), 0) + 5.00,
        date: new Date().toLocaleDateString('eu-ES')
    };
   
    localStorage.setItem('lastOrder', JSON.stringify(orderData));
   
// Eskaera arrakastatsu baten ondoren saskia hustu
    localStorage.removeItem('cart');
    cart = [];
   
    window.location.href = 'print.html';
}


// Inprimatze-orriko funtzioa
function bete_print() {
    const userElement = document.getElementById('user');
    const orderData = JSON.parse(localStorage.getItem('lastOrder'));
   
    if (userElement && orderData) {
        userElement.textContent = orderData.username;
    }
}


// Saioaren kudeaketa
function bukatuSaioa() {
    localStorage.removeItem('username');
    localStorage.removeItem('products');
    localStorage.removeItem('cart');
    localStorage.removeItem('lastOrder');
}


// Autentifikazioa egiaztatu
function checkAuthentication() {
    const username = localStorage.getItem('username');
    const protectedPages = ['inner-page-products.html', 'product-details.html', 'payment_1.html', 'print.html'];
    const currentPage = window.location.pathname.split('/').pop();
   
    if (protectedPages.includes(currentPage) && !username) {
        window.location.href = 'login.html';
        return false;
    }
   
    return true;
}


// DOM kargatzean hasieratu
document.addEventListener('DOMContentLoaded', function() {
    const products = JSON.parse(localStorage.getItem("products")) || [];
    const cart = JSON.parse(localStorage.getItem("cart")) || [];

    checkAuthentication();
    create_products_list();
    show_product_details();
    updateCartCount();
   
// Ordainketa-orria hasieratu bertan bagaude
    if (window.location.pathname.includes('payment_1.html')) {
        fill_payment_cards1();
    }
});





