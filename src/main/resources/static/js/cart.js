var removeCartItemBtn = document.getElementsByClassName('rmv-product');
console.log(removeCartItemBtn)
var button, buttonClicked;
for (var i = 0; i < removeCartItemBtn.length; i++) {
    button = removeCartItemBtn[i];
    button.addEventListener('click', function (event) {
        buttonClicked = event.target
        buttonClicked.parentElement.parentElement.parentElement.remove();
        updateCartTotal();
    })
}

function updateCartTotal() {
    var cartContainer = document.getElementsByClassName('table-shopping-cart')[0];
    var cartItems = cartContainer.getElementsByClassName('table_row');
    var cartItem, priceItem, qtyItem, total = 0, price = 0;
    for (var i = 0; i < cartItems.length; i++) {
        cartItem = cartItems[i];
        priceItem = cartItem.getElementsByClassName('price-item')[0];
        qtyItem = cartItem.getElementsByClassName('qty-item')[0];
        console.log(qtyItem.value);
        price = parseFloat(priceItem.innerText.replace('$', ''));
        console.log(price)
        total += (price * qtyItem.value);
    }
    total = Math.round(total * 100) / 100;
    console.log(total);
    document.getElementsByClassName('total')[0].innerText = '$' + total;
    console.log("test");

}


