$(document).ready(function () {

    var url1 = "http://localhost:9999/check-out",
        url2 = "http://localhost:9999/shopping-cart"
    if (location.href.match(url1)) {
        updateCartTotal1()
    }
    updateCartTotal()

    $(".btn-num-product-up").click(function () {
        //updateProductTotal();
        updateCartTotal();
    })

    $(".btn-num-product-down").click(function () {
        updateCartTotal();
    })


    $(".rmv-product").click(function () {
        var buttonClicked = event.target
        buttonClicked.parentElement.parentElement.parentElement.remove();
        updateCartTotal()
    })


    // Calculator total 1 product with url: shopping-cart
    function updateProductTotal(i) {
        var priceItem = document.getElementsByClassName("price-item")[i],
            qtyItem = document.getElementsByClassName("qty-item")[i];
        var price = 0, totalItem = 0;
        price = parseFloat(priceItem.innerText.replace('$', ''));
        totalItem = (price * qtyItem.value);
        totalItem = (Math.round(totalItem * 100) / 100).toFixed(2)
        document.getElementsByClassName('total-item')[i].innerText = '$' + totalItem;
    }


    // Calculator total all products with url: shopping-cart
    function updateCartTotal() {
        var cartContainer = document.getElementsByClassName('table-shopping-cart')[0];
        var cartItems = cartContainer.getElementsByClassName('table_row');
        var cartItem, priceItem, qtyItem, total = 0, price = 0, subTotal = 0;
        for (var i = 0; i < cartItems.length; i++) {
            cartItem = cartItems[i];
            priceItem = cartItem.getElementsByClassName('price-item')[0];
            qtyItem = cartItem.getElementsByClassName('qty-item')[0];
            price = parseFloat(priceItem.innerText.replace('$', ''));
            total += (price * qtyItem.value);
            updateProductTotal(i);
        }
        subTotal = (Math.round(total * 100) / 100).toFixed(2)
        document.getElementsByClassName('sub-total')[0].innerText = '$' + subTotal;
    }


    // Calculator total 1 products with url: check-out
    function updateProductTotal1(i) {
        var priceItem = document.getElementsByClassName("price-item")[i],
            qtyItem = document.getElementsByClassName("qty-item1")[i];
        var price = 0, totalItem = 0;
        price = parseFloat(priceItem.innerText.replace('$', ''));
        totalItem = (price * parseInt(qtyItem.innerText));
        totalItem = (Math.round(totalItem * 100) / 100).toFixed(2)
        document.getElementsByClassName('total-item1')[i].innerText = '$' + totalItem;
    }

    // Calculator total all products with url: check-out
    function updateCartTotal1() {
        var cartContainer = document.getElementsByClassName('table-shopping-cart')[0];
        var cartItems = cartContainer.getElementsByClassName('table_row');
        var cartItem, priceItem, qtyItem, total = 0, price = 0, subTotal = 0;
        for (var i = 0; i < cartItems.length; i++) {
            cartItem = cartItems[i];
            priceItem = cartItem.getElementsByClassName('price-item')[0];
            qtyItem = cartItem.getElementsByClassName('qty-item1')[0];
            price = parseFloat(priceItem.innerText.replace('$', ''));
            total += (price * parseFloat(qtyItem.innerText));
            updateProductTotal1(i);
        }
        subTotal = (Math.round(total * 100) / 100).toFixed(2)
        document.getElementsByClassName('sub-total1')[0].innerText = '$' + subTotal;
    }
})