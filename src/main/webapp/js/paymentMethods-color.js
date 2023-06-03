const pM_items = document.getElementsByClassName('paymentMethod');
const color_PAYPAL = '#7ab2fa';
const color_VISA = '#fa7a7a';
const color_CREDIT = '#bee0ab';
for(let element of pM_items) {
    let color_toAdd;
    switch (element.innerHTML) {
        case 'Credit Card' :
           color_toAdd = color_CREDIT;
           break;
        case 'Paypal' :
            color_toAdd = color_PAYPAL;
            break;
        case 'VISA Debit' :
            color_toAdd = color_VISA;
            break;
        default :
            color_toAdd = 'gray';
            break;
    }
    element.style.backgroundColor = color_toAdd;
    element.style.color = 'black';
}